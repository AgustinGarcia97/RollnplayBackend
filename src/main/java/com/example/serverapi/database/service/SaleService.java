package com.example.serverapi.database.service;

import com.example.serverapi.database.repository.SaleRepository;
import com.example.serverapi.exceptions.SaleNotFoundException;
import com.example.serverapi.exceptions.UserNotFoundException;
import com.example.serverapi.model.Sale;
import com.example.serverapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SaleService {
    @Autowired
    private SaleRepository saleRepository;

    @Autowired private UserService userService;

    public void createSale(Integer user_id) {
        Sale sale = new Sale();

        Optional<User> user = userService.getUserById(user_id);
        if (user.isPresent()) {
            // Verificamos si no existe ya un carrito para el usuario
            Integer count = this.countSales(user_id);
            if (count == 0) {
                sale.setUser(user.get());
                sale.setSaleDate(java.time.LocalDateTime.now());
                saleRepository.save(sale);
            }
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    public Integer countSales(Integer user_id) {
        return saleRepository.contarVentas(user_id);
    }

    public Sale findById(Long id) {
        Optional<Sale> sale = saleRepository.findById(id);
        if (sale.isPresent()) {
            return sale.get();
        } else {
            throw new SaleNotFoundException("Sale not found");
        }
    }

    public void deleteSale(Integer idInt) {
        Optional<Sale> sale = saleRepository.findByUser(idInt);
        if (sale.isPresent()) {
            saleRepository.delete(sale.get());
        } else {
            throw new SaleNotFoundException("Sale not found");
        }
    }
}
