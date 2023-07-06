package com.example.games.repositories;

import com.example.games.models.games;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface gameRepository extends MongoRepository<games, String> {
}
