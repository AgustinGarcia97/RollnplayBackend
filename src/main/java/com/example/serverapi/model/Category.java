package com.example.serverapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
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
            cascade=CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Product> products;

    public Category(Long categoryId, String categoryName, List<Product> products) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.products = products;
    }

    public Category() {
    }
}
