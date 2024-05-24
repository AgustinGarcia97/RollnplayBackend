package com.example.serverapi.database.repository;

import com.example.serverapi.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    Player findByNumberOfPlayers(String numberOfPlayers);
}
