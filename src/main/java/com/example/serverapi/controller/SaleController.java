package com.example.serverapi.controller;

import com.example.serverapi.database.service.SaleService;
import com.example.serverapi.exceptions.UserNotFoundException;
import com.example.serverapi.model.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

@RestController
public class SaleController {
    @Autowired
    private SaleService saleService;

    @PostMapping("/create-sale")
    public ResponseEntity<?> createSale (@RequestParam String id) throws UserNotFoundException {

        // Verificamos si el usuario envió el param
        if (id == null) {
            return new ResponseEntity<>("ID no recibido", HttpStatus.BAD_REQUEST);
        }

        System.out.println(id);
        // Verificamos si el param es un integer
        if (id.isEmpty()) {
            return new ResponseEntity<>("ID no recibido", HttpStatus.BAD_REQUEST);
        } else {
            try {
                Integer id_int = Integer.parseInt(id);
                try {
                    saleService.createSale(id_int);
                    return new ResponseEntity<>(HttpStatus.OK);
                } catch (UserNotFoundException e) {
                    return new ResponseEntity<>("ID de usuario no encontrado", HttpStatus.NOT_FOUND);
                }
            } catch (NumberFormatException e) {
                return new ResponseEntity<>("Formato de ID incorrecto", HttpStatus.BAD_REQUEST);
            }
        }
    }

    // Excepción para revisar si el param es recibido
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public String handleMissingParams(MissingServletRequestParameterException ex) {
        String paramName = ex.getParameterName();
        return "El parámetro '" + paramName + "' es requerido";
    }
}
