package com.example.serverapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="player")
@Data               //getters, setters, hashcode, toString()
@AllArgsConstructor //constructor sobrecargado
@NoArgsConstructor //constructor vacio
public class Player implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="players_id")
    private Long playersId;
    @Column(unique=true, name="number_of_players")
    private String numberOfPlayers;
    @JoinColumn(name="product_id")

    @OneToMany(cascade=CascadeType.ALL,fetch =  FetchType.LAZY, mappedBy = "players")
    private List<Product> product;





}
