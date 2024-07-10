package com.example.serverapi.database.repository;

import com.example.serverapi.model.User;
import com.example.serverapi.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);
    @Query("SELECT new com.example.serverapi.dto.UserDTO(" +
            "u.userId," +
            "u.firstName, " +
             "u.lastName,"+
            "u.email, " +
            "u.address, " +
            "u.phoneNumber, " +
            "u.isSeller, " +
            "u.document," +
            " u.role," +
            "u.password"+
            ") " +
            "from User u WHERE u.userId = :userId")
    UserDTO findByUserId(UUID userId);
}
