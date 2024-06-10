package com.example.serverapi.controller;
import com.example.serverapi.database.service.UserService;
import com.example.serverapi.dto.UserDTO;
import com.example.serverapi.exceptions.errorResponse.ErrorResponse;
import com.example.serverapi.exceptions.userExceptions.UserConversionException;
import com.example.serverapi.exceptions.userExceptions.UserPersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.serverapi.model.User;

import java.util.Optional;
import java.util.UUID;

@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }


    @GetMapping("/get-user")
    public ResponseEntity<UserDTO> getUser(@RequestParam UUID id){
        try{
            UserDTO userDTO = userService.getUserDTOById(id);
            return ResponseEntity.status(HttpStatus.OK).body(userDTO);
        }
        catch(UserPersistenceException e){
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(UserConversionException e){
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO){
        try{
            User user = userService.createOrUpdateUser(userDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario creado correctamente: "+user.toString());

        } catch(UserConversionException e){
            logger.error("Error converting UserDTO to User entity: {} ",e.getMessage());
            ErrorResponse errorResponse = new ErrorResponse("Conversion error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);

        } catch(UserPersistenceException e){
            logger.error("Error persisnting User entity:{} ",e.getMessage());
            ErrorResponse errorResponse = new ErrorResponse("Persistence error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);

        }catch(Exception e){
            logger.error("Unexpected error:{} ",e.getMessage());
            ErrorResponse errorResponse = new ErrorResponse("Unexpected error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse +" DTO: "+ userDTO.toString());
        }
    }

    @PutMapping("/update-user")
    public ResponseEntity<?> updateUser(@RequestBody UserDTO userDTO){
        try{
            User user = userService.createOrUpdateUser(userDTO);
            return ResponseEntity.status(HttpStatus.OK).body("Usuario actualizado correctamente: "+user.toString());

        } catch(UserConversionException e){
            logger.error("Error converting UserDTO to User entity: {} ",e.getMessage());
            ErrorResponse errorResponse = new ErrorResponse("Conversion error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);

        } catch(UserPersistenceException e){
            logger.error("Error persisnting User entity:{} ",e.getMessage());
            ErrorResponse errorResponse = new ErrorResponse("Persistence error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);

        }catch(Exception e){
            logger.error("Unexpected error:{} ",e.getMessage());
            ErrorResponse errorResponse = new ErrorResponse("Unexpected error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse +" DTO: "+ userDTO.toString());
        }
    }


    @DeleteMapping("/delete-user")
    public ResponseEntity<String> deleteUser(@RequestParam String userId){
        try{
            userService.deleteUser(UUID.fromString(userId));
            return ResponseEntity.ok("Usuario eliminado correctamente");
        }
        catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
