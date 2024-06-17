package com.example.serverapi.database.service;


import com.example.serverapi.database.repository.UserRepository;
import com.example.serverapi.dto.UserDTO;
import com.example.serverapi.exceptions.userExceptions.UserConversionException;
import com.example.serverapi.exceptions.userExceptions.UserPersistenceException;
import com.example.serverapi.model.Listing;
import com.example.serverapi.model.User;

import com.example.serverapi.utils.Converter.DtoAssembler;
import com.example.serverapi.utils.Converter.UserConverter;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final DtoAssembler dtoAssembler;
    private UserRepository userRepository;

    private UserConverter userConverter;

    private UserService(UserRepository userRepository, UserConverter userConverter, DtoAssembler dtoAssembler) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.dtoAssembler = dtoAssembler;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User createOrUpdateUser(UserDTO userDTO) {
        try{
            User user = userConverter.convertToEntity(userDTO);
            return userRepository.save(user);

        } catch(ConstraintViolationException e){
            throw new UserPersistenceException("Validation error while saving user", e);

        } catch(DataIntegrityViolationException e){
            throw new UserPersistenceException("Data integrity violation while saving user", e);

        } catch(EntityNotFoundException e){
            throw new UserPersistenceException("Entity not found while saving user", e);

        } catch (NullPointerException e) {
            throw new NullPointerException("Null value: "+ e);

        } catch (Exception e) {
            throw new UserPersistenceException("Unexpected error while saving user", e);
        }
    }

    public Optional<User> getUserById(UUID id) {
        return userRepository.findById(id);
    }

    public UserDTO getUserDTOById(UUID id) {
        UserDTO userDTO = null;
        try{
            Optional<User> user = userRepository.findById(id);

            if(user.isPresent()) {
                userDTO = dtoAssembler.getUserDTO(user.get());

                if(user.get().getListings() != null){
                    userDTO.setListingsDTO(
                            user.get()
                                    .getListings()
                                    .stream()
                                    .map(listing -> dtoAssembler.getListingDTO(listing))
                                    .collect(Collectors.toList()));
                }

            }
        }catch (EntityNotFoundException e) {
            throw new UserPersistenceException("Entity not found", e);
        }
        return userDTO;
    }

    public List<Listing> getListingById(UUID id){
       return userRepository.findById(id).get().getListings();
    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
}
