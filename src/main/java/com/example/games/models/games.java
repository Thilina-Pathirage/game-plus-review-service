package com.example.games.models;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.List;

@Document
public class games {

    @Id
    private String id;

    private String gameName;

    private String description;

    private String imageUrl;

    private Double rating;

    private List<reviews> reviews; // Review class assumed to be in the same package

    public games() {} // no-arg constructor

    public games(String gameName, String description, String imageUrl, Double rating) {
        this.gameName = gameName;
        this.description = description;
        this.imageUrl = imageUrl;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }


    public List<reviews> getReviews() {
        return reviews;
    }

    public void setReviews(List<reviews> reviews) {
        this.reviews = reviews;
    }

    // This method can be used to add a review to the list
    public void addReview(reviews review) {
        if (this.reviews == null) {
            this.reviews = new ArrayList<>();
        }
        this.reviews.add(review);
    }

    @Override
    public String toString() {
        return "games{" +
                "id='" + id + '\'' +
                ", gameName='" + gameName + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", rating=" + rating +
                ", reviews=" + reviews +
                '}';
    }
}
