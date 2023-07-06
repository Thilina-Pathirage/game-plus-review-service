package com.example.games.models;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class reviews {

    private String reviewId;
    private String userName;
    private int userRating;
    private String review;

    public reviews(String reviewId, String userName, int userRating, String review) {
        this.reviewId = reviewId;
        this.userName = userName;
        this.userRating = userRating;
        this.review = review;
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserRating() {
        return userRating;
    }

    public void setUserRating(int userRating) {
        this.userRating = userRating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    @Override
    public String toString() {
        return "reviews{" +
                "reviewId=" + reviewId +
                ", userName='" + userName + '\'' +
                ", userRating=" + userRating +
                ", review='" + review + '\'' +
                '}';
    }
}
