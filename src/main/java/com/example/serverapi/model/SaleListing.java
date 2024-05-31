package com.example.serverapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="sale_listing")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleListing {

    @Id
    @Column(name="listing_id")
    private Long listingId;

    @Column(name="sale_id")
    private long saleId;
}
