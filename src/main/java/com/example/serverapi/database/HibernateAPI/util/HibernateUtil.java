package com.example.serverapi.database.HibernateAPI.util;


import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

    //objeto que generara del xml - sessionFactory

    public static SessionFactory getSessionFactory(){
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() //busca y lee los datos del hibernate.cfg.xml
                .build();

        SessionFactory sessionFactory = null;
        try{


            sessionFactory = new MetadataSources(registry)
                    .buildMetadata() //primero genera la metadata
                    .buildSessionFactory(); // luego el sessionFactory
        }
        catch(Exception e){

            StandardServiceRegistryBuilder.destroy(registry);
            throw e;

        }


        return sessionFactory;
    }
}
