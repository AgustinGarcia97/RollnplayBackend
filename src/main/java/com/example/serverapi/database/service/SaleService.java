package com.example.serverapi.database.service;

import com.example.serverapi.database.repository.SaleRepository;
import com.example.serverapi.exceptions.SaleNotFoundException;
import com.example.serverapi.exceptions.UserNotFoundException;
import com.example.serverapi.model.Sale;
import com.example.serverapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Optional;
import java.util.UUID;

@Service
public class SaleService {
    @Autowired
    private SaleRepository saleRepository;

    @Autowired private UserService userService;

    public Sale createSale(Integer user_id) {
        Sale sale = new Sale();

        Optional<User> user = userService.getUserById(user_id);
        if (user.isPresent()) {
            System.out.println(user.get().getName());
            sale.setUser(user.get());
            sale.setSaleDate(java.time.LocalDateTime.now());
            return saleRepository.save(sale);
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    public Sale findById(Long id) {
        Optional<Sale> sale = saleRepository.findById(id);
        if (sale.isPresent()) {
            return sale.get();
        } else {
            throw new SaleNotFoundException("Sale not found");
        }
    }
}
