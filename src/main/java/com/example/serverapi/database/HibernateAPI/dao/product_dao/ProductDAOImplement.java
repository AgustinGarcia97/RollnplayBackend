package com.example.serverapi.database.HibernateAPI.dao.product_dao;

import com.example.serverapi.database.HibernateAPI.util.HibernateUtil;
import com.example.serverapi.exceptions.UserDaoException;
import com.example.serverapi.model.Product;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import com.example.serverapi.model.Listing;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
public class ProductDAOImplement implements ProductDAO {

    public Product createProduct(Product product) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Long id = (Long) session.save(product);
            session.getTransaction().commit();
            product.setProductId(id);
        }
        catch(HibernateException e){
            if(session.getTransaction() != null){
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            throw new UserDaoException("Error al crear el producto:", e);
        }
        catch(RuntimeException e){
            if(session.getTransaction() != null){
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            throw new UserDaoException("Error al crear el producto:", e);
        }
        finally {
            session.close();
            return product;
        }
    }

    @Override
    public Product getProductById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Product product = null;
        try{
            session.beginTransaction();
            product = session.get(Product.class, id);
            session.getTransaction().commit();
        }
        catch(HibernateException e){
            e.printStackTrace();
            throw new UserDaoException("Error al obtener al producto:", e);
        }
        catch(RuntimeException e){
            e.printStackTrace();
            throw new UserDaoException("Error al obtener al producto:", e);
        }
        finally {
            session.close();
            return product;
        }
    }

    @Override
    public Product updateProduct(Long id, Map<String, Object> fieldUpdates) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Product product = null;
        try {
            session.beginTransaction();
            product = session.get(Product.class, id);

            for (Map.Entry<String, Object> entry : fieldUpdates.entrySet()) {
                String fieldName = entry.getKey();
                Object fieldValue = entry.getValue();

                switch (fieldName) {
                    case "productName":
                        product.setProductName(fieldValue.toString());
                        break;
                    case "productDescription":
                        product.setProductDescription(fieldValue.toString());
                        break;
                    case "productListing":
                        product.setListings((Listing)fieldValue);
                        break;
                    default:
                        System.out.println("Nombre de campo no valido: " + fieldName);
                        break;
                }
            }
            session.update(product);
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
            return product;
        }
    }

    @Override
    public void deleteProduct(Long id) {
        Product productToDelete = getProductById(id);
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.delete(productToDelete);
        }
        catch(HibernateException e){
            if(session.getTransaction() != null){
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            throw new UserDaoException("Error al eliminar el producto:", e);
        }
        catch(RuntimeException e){
            if(session.getTransaction() != null){
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            throw new UserDaoException("Error al eliminar el producto:", e);
        }
        finally {
            session.close();
        }
    }
}
