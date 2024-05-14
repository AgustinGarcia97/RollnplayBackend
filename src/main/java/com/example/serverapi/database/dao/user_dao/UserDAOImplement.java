package com.example.serverapi.database.dao.user_dao;

import com.example.serverapi.database.util.HibernateUtil;
import com.example.serverapi.exceptions.UserDaoException;
import com.example.serverapi.model.Listing;
import com.example.serverapi.model.User;
import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserDAOImplement implements UserDAO {

    public User createUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            String id = (String) session.save(user);
            session.getTransaction().commit();
            user.setUserId(id);
        }
        catch(HibernateException e){
            e.printStackTrace();
            throw new UserDaoException("Error al actualizar el usuario:", e);
        }
        catch(RuntimeException e){
            e.printStackTrace();
            throw new UserDaoException("Error al actualizar el usuario:", e);
        }
        finally {
            session.close();
            return user;
        }
    }

    public User getUserById(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        User user = null;
        try{
            session.beginTransaction();
            user = session.get(User.class, id);
            session.getTransaction().commit();

        }
        catch(HibernateException e){
            e.printStackTrace();
            throw new UserDaoException("Error al actualizar el usuario:", e);
        }
        catch(RuntimeException e){
            e.printStackTrace();
            throw new UserDaoException("Error al actualizar el usuario:", e);
        }
        finally {
            session.close();
            return user;
        }
    }

    public User updateUser(String id, Map<String,Object> fieldUpdates)  {
        Session session = HibernateUtil.getSessionFactory().openSession();
        User user = null;
        try{
            session.beginTransaction();
            user = session.get(User.class, id);

            for(Map.Entry<String, Object> entry : fieldUpdates.entrySet()) {
                String fieldName = entry.getKey();
                Object fieldValue = entry.getValue();

                switch (fieldName) {
                    case "name":
                        user.setName(fieldValue.toString());
                        break;
                    case "username":
                        user.setUsername(fieldValue.toString());
                        break;
                    case "email":
                        user.setEmail(fieldValue.toString());
                        break;
                    case "password":
                        user.setPassword(fieldValue.toString());
                        break;
                    case "address":
                        user.setAddress(fieldValue.toString());
                        break;
                    case "phoneNumber":
                        user.setPhoneNumber(fieldValue.toString());
                        break;
                    default:
                        System.out.println("Nombre de campo no valido: "+fieldName);
                        break;
                }

            }
            session.update(user);
            session.getTransaction().commit();


        }
        catch(HibernateException e){
            if(session.getTransaction() != null){
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            throw new UserDaoException("Error al actualizar el usuario:", e);
        }
        catch(RuntimeException e){
            if(session.getTransaction() != null){
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            throw new UserDaoException("Error al actualizar el usuario:", e);
        }
        finally{
            session.close();
            return user;
        }

    }


    public void deleteUser(String id) {
        User userToDelete = getUserById(id);
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.delete(userToDelete);
        }
        catch(HibernateException e){
            if(session.getTransaction() != null){
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            throw new UserDaoException("Error al actualizar el usuario:", e);
        }
        catch(RuntimeException e){
            if(session.getTransaction() != null){
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            throw new UserDaoException("Error al actualizar el usuario:", e);
        }
        finally {
            session.close();
        }
    }

}
