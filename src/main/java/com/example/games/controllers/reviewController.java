package com.example.games.controllers;

import com.example.games.models.games;
import com.example.games.models.reviews;
import com.example.games.repositories.gameRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/reviews") // this is the base path for all endpoints in this controller
public class reviewController {

    @Autowired
    private gameRepository gameRepo;

    @PostMapping("/addReview/{id}")
    public ResponseEntity<String> addReview(@RequestBody reviews review, @PathVariable("id") String gameId) {
        games game = gameRepo.findById(gameId).orElse(null);

        if (game == null) {
            return new ResponseEntity<String>("No game found with ID: " + gameId, HttpStatus.NOT_FOUND);
        }

        String reviewId = generateUniqueReviewId(); // Generate a unique string ID for the review
        review.setReviewId(reviewId); // Set the generated ID as the review's ID

        game.addReview(review);
        gameRepo.save(game);

        return new ResponseEntity<String>("Review is inserted", HttpStatus.OK);
    }

    private String generateUniqueReviewId() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 12; i++) {
            int index = random.nextInt(characters.length());
            builder.append(characters.charAt(index));
        }
        return builder.toString();
    }



    @PostMapping("/updateReview/{gameId}/{reviewId}")
    public ResponseEntity<String> updateReview(@RequestBody reviews newReview, @PathVariable("gameId") String gameId, @PathVariable("reviewId") String reviewId) {
        games game = gameRepo.findById(gameId).orElse(null);

        if (game == null) {
            return new ResponseEntity<String>("No game found with ID: " + gameId, HttpStatus.NOT_FOUND);
        }

        List<reviews> reviews = game.getReviews();

        Optional<reviews> optionalReview = reviews.stream()
                .filter(r -> r.getReviewId().equals(reviewId))
                .findFirst();

        if (!optionalReview.isPresent()) {
            return new ResponseEntity<>("No review found with ID: " + reviewId, HttpStatus.NOT_FOUND);
        }

        reviews existingReview = optionalReview.get();

        // Update the fields of the existing review
        existingReview.setUserRating(newReview.getUserRating());
        existingReview.setReview(newReview.getReview());

        gameRepo.save(game);

        return new ResponseEntity<String>("Review updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/deleteReview/{gameId}/{reviewId}")
    public ResponseEntity<String> deleteReview(
            @PathVariable("gameId") String gameId,
            @PathVariable("reviewId") String reviewId) {

        games game = gameRepo.findById(gameId).orElse(null);

        if (game == null) {
            return new ResponseEntity<>("No game found with ID: " + gameId, HttpStatus.NOT_FOUND);
        }

        List<reviews> reviews = game.getReviews();

        Optional<reviews> optionalReview = reviews.stream()
                .filter(r -> r.getReviewId().equals(reviewId))
                .findFirst();

        if (!optionalReview.isPresent()) {
            return new ResponseEntity<>("No review found with ID: " + reviewId, HttpStatus.NOT_FOUND);
        }

        reviews reviewToDelete = optionalReview.get();

        // Remove the review from the list
        reviews.remove(reviewToDelete);

        gameRepo.save(game);

        return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
    }

}
