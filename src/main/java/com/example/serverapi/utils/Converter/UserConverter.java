package com.example.serverapi.utils.converter;

import com.example.serverapi.dto.UserDTO;
import com.example.serverapi.exceptions.dtoExceptions.ConversionException;
import com.example.serverapi.model.Role;
import com.example.serverapi.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {


    public User convertToEntity(UserDTO userDTO) {
        User user = null;
        try {
            user = new User();

            if (userDTO.getUserId() != null) {
                user.setUserId(userDTO.getUserId());
            }

            user.setName(userDTO.getName());
            user.setUsername(userDTO.getUsername());
            user.setPassword(userDTO.getPassword());
            user.setEmail(userDTO.getMail());
            user.setPhoneNumber(userDTO.getPhoneNumber());
            user.setAddress(userDTO.getAddress());
            user.setDocument(userDTO.getDocument());
            user.setRole(Role.valueOf(userDTO.getRole()));


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
            userDTO.setName(user.getName());
            


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
