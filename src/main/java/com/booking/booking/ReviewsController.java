package com.booking.booking.controller;

import com.booking.booking.service.ReviewsService;
import com.booking.booking.util.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Reviews")
public class ReviewsController {
    private ReviewsService reviewsService;

    public ReviewsController(ReviewsService reviewsService)
    {
        this.reviewsService = reviewsService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllReviews()
    {
        try
        {
            return ResponseEntity.ok(new ApiResponse(true, "success", reviewsService.getAllReviews(),null));
        }
        catch (Exception e)
        {
            return ResponseEntity.status(500).body(new ApiResponse(false, "An error occurred", null, e.getMessage()));
        }
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<ApiResponse> getReviewsById(@PathVariable String reviewId)
    {
        try
        {
            return ResponseEntity.ok(new ApiResponse(true, "success", reviewsService.getReviewsById(reviewId),null));
        }
        catch (Exception e)
        {
            return ResponseEntity.status(500).body(new ApiResponse(false, "An error occurred", null, e.getMessage()));
        }
    }
}
