package com.example.serverapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Table(name="category")
@Data
@Entity
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="category_id")
    private Long categoryId;
    @Column(name="category_name")
    private String categoryName;

    @OneToMany(
            mappedBy="category",
            fetch = FetchType.LAZY
    )
    private List<Product> products;

    public Category( String categoryName) {
        this.categoryName = categoryName;

    }

    public Category() {
    }

    public void setProductToList(Product product) {
        if(this.products == null) {
            this.products = new ArrayList<>();
        }
        this.products.add(product);
    }
}
