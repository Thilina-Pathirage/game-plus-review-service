package com.example.games.repositories;

import com.example.games.models.reviews;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface reviewRepository extends MongoRepository<reviews, String> {
}
