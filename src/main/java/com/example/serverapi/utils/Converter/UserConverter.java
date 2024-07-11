package com.example.serverapi.utils.Converter;

import com.example.serverapi.dto.UserDTO;
import com.example.serverapi.exceptions.dtoExceptions.ConversionException;
import com.example.serverapi.model.Role;
import com.example.serverapi.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {




    public User convertToEntity(UserDTO userDTO) {
        User user = null;
        try {
            user = new User();

            if (userDTO.getUserId() != null) {
                user.setUserId(userDTO.getUserId());
                System.out.println(userDTO.getPassword());
                user.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));

            }
            else{
                user.setPassword(userDTO.getPassword());
            }
            if(userDTO.getRole() != null) {
                user.setRole(Role.valueOf(userDTO.getRole()));
            }
            if(userDTO.getUsername() != null) {
                user.setEmail(userDTO.getUsername());
            }
            if(userDTO.getFirstName()  != null){
                user.setFirstName(userDTO.getFirstName());
            }
            if(userDTO.getLastName() != null) {
                user.setLastName(userDTO.getLastName());
            }
            if(userDTO.getPhoneNumber() != null){
                user.setPhoneNumber(userDTO.getPhoneNumber());
            }

            if(userDTO.getAddress() != null) {
                user.setAddress(userDTO.getAddress());
            }

            if(userDTO.getDocument() != null){
                user.setDocument(userDTO.getDocument());
            }

        } catch (IllegalArgumentException e) {
            System.out.println("values cannot be null:" + e.getMessage());

        } catch (ConversionException e) {
            System.out.println("conversor error:" + e.getMessage());

        } catch (Exception e) {
            System.out.println("error:" + e.getMessage());

        } finally {
            return user;
        }
    }


    public User convertToBasicEntity(UserDTO userDTO) {
        try{

        }
        catch(IllegalArgumentException e){
            System.out.println("values cannot be null:"+e.getMessage());
        }
        catch(ConversionException e){
            System.out.println("conversor error:"+e.getMessage());

        }
        catch(Exception e){
            System.out.println("error:"+e.getMessage());
        }
        return null;
    }


    public UserDTO convertToFullDTO(User user) {
        UserDTO userDTO = new UserDTO();
        try{
            userDTO.setUserId(user.getUserId());
            userDTO.setUsername(user.getUsername());
            userDTO.setPassword(user.getPassword());
            userDTO.setMail(user.getEmail());
            userDTO.setAddress(user.getAddress());
            userDTO.setPhoneNumber(user.getPhoneNumber());
            userDTO.setRole(user.getRole().toString());
            userDTO.setDocument(user.getDocument());
            userDTO.setFirstName(user.getFirstName());
            userDTO.setLastName(user.getLastName());
            


        }
        catch(IllegalArgumentException e){
            System.out.println("values cannot be null:"+e.getMessage());
        }
        catch(ConversionException e){
            System.out.println("conversor error:"+e.getMessage());

        }
        catch(Exception e){
            System.out.println("error:"+e.getMessage());
        }
        return userDTO;
    }

    public UserDTO convertToBasicDTO(User user) {
        try{

        }
        catch(IllegalArgumentException e){
            System.out.println("values cannot be null:"+e.getMessage());
        }
        catch(ConversionException e){
            System.out.println("conversor error:"+e.getMessage());

        }
        catch(Exception e){
            System.out.println("error:"+e.getMessage());
        }
        return null;
    }


}
