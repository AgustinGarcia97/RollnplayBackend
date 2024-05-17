package com.example.serverapi.utils;

import com.example.serverapi.database.HibernateAPI.dao.listing_dao.ListingDAO;
import com.example.serverapi.database.HibernateAPI.dao.listing_dao.ListingDAOImplement;
import com.example.serverapi.database.HibernateAPI.dao.product_dao.ProductDAO;
import com.example.serverapi.database.HibernateAPI.dao.product_dao.ProductDAOImplement;
import com.example.serverapi.database.HibernateAPI.dao.user_dao.UserDAO;
import com.example.serverapi.database.HibernateAPI.dao.user_dao.UserDAOImplement;
import com.example.serverapi.model.Listing;
import com.example.serverapi.model.Product;
import com.example.serverapi.model.User;

public class Main {

    public static void main(String[] args) {
/*
        User user1 = new User(
                "seller1",
                "john doe",
                "123456",
                "mail@mail.com",
                "false street 123",
                "113422546",
                "4400949",
                true
        );

        User user2 = new User(
                "seller2",
                "Jane Smith",
                "password123",
                "jane@example.com",
                "123 Main Street",
                "555-123-4567",
                "9876543",
                true
        );

        User user3 = new User(
                "buyer1",
                "Alice Johnson",
                "securepass",
                "alice@example.com",
                "456 Elm Street",
                "555-987-6543",
                "1234567",
                false
        );

        Product product1 = new Product(
                "Monopoly",
                "Juego para todas las edades de 2-4 jugadores",
                999.99,"familiar",
                10);

        Product product2 = new Product(
                "Risk",
                "Juego de estrategia para conquistar territorios",
                799.99, "familiar",
                15);

        Product product3 = new Product("Catan",
                "Juego de mesa de negociación y estrategia",
                1299.99,
                "familiar",
                8);

        Listing listing1 = new Listing("Vendo Monopoly","Juego familiar para 2 a 4 jugadores en español", 9999.99,5);
        listing1.setProduct(product1);
        listing1.setUser(user1);
        user1.setListings(listing1);

        Listing listing2 = new Listing("Vendo Monopoly completo","Juego familiar para 2 a 4 jugadores en español e ingles", 899.99,7);
        listing2.setProduct(product1);
        listing2.setUser(user2);
        user2.setListings(listing2);

        Listing listing3 = new Listing("Vendo Risk","Juego de estrategia para 2 a 4 jugadores en español e ingles", 1899.99,10);
        listing3.setProduct(product2);
        listing3.setUser(user1);
        user3.setListings(listing3);

        UserDAO userDAO = new UserDAOImplement();
        ProductDAO productDAO = new ProductDAOImplement();
        ListingDAO listingDAO = new ListingDAOImplement();

        userDAO.createUser(user1);
        userDAO.createUser(user2);
        userDAO.createUser(user3);


        UserDAO userDAO = new UserDAOImplement();
        System.out.println(userDAO.getUserById("346aa7fe-916f-4285-a768-154582c0c184").toString());



        System.out.println(userDAO.getUserById("346aa7fe-916f-4285-a768-154582c0c184").toString());
*/
        User user1 = new User(
                "seller1",
                "john doe",
                "123456",
                "mail@mail.com",
                "false street 123",
                "113422546",
                "4400949",
                true
        );
        UserDAO userDAO = new UserDAOImplement();
        userDAO.createUser(user1);
    }
}
