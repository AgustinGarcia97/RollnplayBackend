package com.example.serverapi.database.repository;

import com.example.serverapi.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT COUNT(s) FROM Sale s WHERE s.user.userId = :user_id")
    Integer contarVentas(Integer user_id);

    @Query("SELECT s FROM Sale s WHERE s.user.userId = :user_id")
    Optional<Sale> findByUser(Integer user_id);

}
