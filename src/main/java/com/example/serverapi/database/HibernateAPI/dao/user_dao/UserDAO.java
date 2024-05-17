package com.example.serverapi.database.HibernateAPI.dao.user_dao;

import com.example.serverapi.model.User;

import java.util.Map;

public interface UserDAO {
    User createUser(User user);
    User getUserById(String id);
    User updateUser(String id, Map<String,Object> fieldUpdates);
    void deleteUser(String id);

}
