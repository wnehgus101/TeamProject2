package org.techtown.myapplication;

public class ReviewInfo {

    private String review;
    private String reviewer;

    public ReviewInfo(String review, String reviewer) {
        this.review = review;
        this.reviewer = reviewer;
    }

    public ReviewInfo(){

    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }
}
