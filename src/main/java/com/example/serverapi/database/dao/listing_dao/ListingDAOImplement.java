package com.example.serverapi.database.dao.listing_dao;

import com.example.serverapi.database.util.HibernateUtil;
import com.example.serverapi.exceptions.UserDaoException;
import com.example.serverapi.model.Listing;
import com.example.serverapi.model.Product;
import com.example.serverapi.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ListingDAOImplement implements ListingDAO {

    public Listing createListing(Listing l) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Listing listing = null;
        try {
            session.beginTransaction();
            String id = (String) session.save(l);
            System.out.println(id);
            session.getTransaction().commit();
            listing.setListingId(id);
        }
        catch(HibernateException e){
            if(session.getTransaction() != null){
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            throw new UserDaoException("Error al crear la publicacion:", e);
        }
        catch(RuntimeException e){
            if(session.getTransaction() != null){
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            throw new UserDaoException("Error al crear la publicacion:", e);
        }
        finally {
            session.close();
            return listing;
        }
    }


    public Listing updateListing(String id, Map<String, Object> fieldUpdates) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Listing listing = null;
        try{
            session.beginTransaction();
            listing = session.get(Listing.class, id);

            for(Map.Entry<String, Object> entry : fieldUpdates.entrySet()) {
                String fieldName = entry.getKey();
                Object fieldValue = entry.getValue();

                switch (fieldName) {
                    case "listingTitle":
                        listing.setTitle(fieldValue.toString());
                        break;
                    case "listingPrice":
                        listing.setPrice((Double)fieldValue);
                        break;
                    case "listingStock":
                        listing.setStock((Double)fieldValue);
                        break;
                    case "listingDescription":
                        listing.setDescription(fieldValue.toString());
                        break;
                    default:
                        System.out.println("Nombre de campo no valido: "+fieldName);
                        break;
                }

            }
            session.update(listing);
            session.getTransaction().commit();
        }

        catch(HibernateException e){
            if(session.getTransaction() != null){
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            throw new UserDaoException("Error al actualizar la publicacion:", e);
        }
        catch(RuntimeException e){
            if(session.getTransaction() != null){
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            throw new UserDaoException("Error al actualizar la publicacion:", e);
        }
        finally{
            session.close();
            return listing;
        }
    }

    @Override
    public Listing getListingById(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Listing listing = null;
        try{
            session.beginTransaction();
            listing = session.get(Listing.class, id);
            session.getTransaction().commit();
        }
        catch(HibernateException e){
            e.printStackTrace();
            throw new UserDaoException("Error al obtener la publicacion:", e);
        }
        catch(RuntimeException e){
            e.printStackTrace();
            throw new UserDaoException("Error al obtener la publicacion:", e);
        }
        finally {
            session.close();
            return listing;
        }
    }

    public void deleteListing(String id) {
        Listing listingToDelete = getListingById(id);
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.delete(listingToDelete);
        }
        catch(HibernateException e){
            if(session.getTransaction() != null){
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            throw new UserDaoException("Error al eliminar la publicacion:", e);
        }
        catch(RuntimeException e){
            if(session.getTransaction() != null){
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            throw new UserDaoException("Error al eliminar la publicacion:", e);
        }
        finally {
            session.close();
        }

    }


}
