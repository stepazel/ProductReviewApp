package com.isep.api.gateway.controllers;

import com.isep.api.gateway.services.HttpService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@Tag(name = "Review", description = "Endpoints for managing Review")
class ReviewController {
    private final HttpService httpService;

    @Value("${services.review.url}")
    private String reviewServiceUrl;

    ReviewController(HttpService httpService) {this.httpService = httpService;}

    @Operation(summary = "finds a product through its sku and shows its review by status")
    @GetMapping("/products/{sku}/reviews/{status}")
    public ResponseEntity<?> findById(@PathVariable(value = "sku") final String sku,
            @PathVariable(value = "status") final String status) throws IOException, InterruptedException {

        try {
            String url      = reviewServiceUrl + "/products/" + sku + "/reviews/" + status;
            Object response = httpService.sendGetRequest(url, Object.class);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            ResponseEntity.internalServerError().body("Error getting review by status");
            throw e;
        }
    }

    @Operation(summary = "gets review by user")
    @GetMapping("/reviews/{userID}")
    public ResponseEntity<?> findReviewByUser(@PathVariable(value = "userID") final Long userID) throws IOException, InterruptedException {
        try {
            String url      = reviewServiceUrl + "/reviews/" + userID;
            Object response = httpService.sendGetRequest(url, Object.class);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            ResponseEntity.internalServerError().body("Error getting review by user with ID: " + userID);
            throw e;
        }
    }

    @Operation(summary = "creates review")
    @PostMapping("/products/{sku}/reviews")
    public ResponseEntity<?> createReview(@PathVariable(value = "sku") final String sku,
            @RequestBody Object createReviewDTO) throws IOException, InterruptedException {

        try {
            String url      = reviewServiceUrl + "/products/" + sku + "/reviews";
            Object response = httpService.sendPostRequest(url, createReviewDTO, Object.class);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            ResponseEntity.internalServerError().body("Error creating review");
            throw e;
        }
    }


    @Operation(summary = "deletes review")
    @DeleteMapping("/reviews/{reviewID}")
    public ResponseEntity<Boolean> deleteReview(@PathVariable(value = "reviewID") final Long reviewID) throws IOException, InterruptedException {
        try {
            String url      = reviewServiceUrl + "/reviews/" + reviewID;
            Object response = httpService.sendDeleteRequest(url, Object.class);
            return ResponseEntity.ok().body((Boolean) response);
        } catch (Exception e) {
            ResponseEntity.internalServerError().body("Error deleting review with ID: " + reviewID);
            throw e;
        }
    }

    @Operation(summary = "gets pedding reviews")
    @GetMapping("/reviews/pending")
    public ResponseEntity<?> getPendingReview() throws IOException, InterruptedException {
        try {
            String url      = reviewServiceUrl + "/reviews/pending";
            Object response = httpService.sendGetRequest(url, Object.class);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            ResponseEntity.internalServerError().body("Error getting pending reviews");
            throw e;
        }
    }

    @Operation(summary = "Accept or reject review")
    @PutMapping("/reviews/acceptreject/{reviewID}")
    public ResponseEntity<?> putAcceptRejectReview(@PathVariable(value = "reviewID") final Long reviewID,
            @RequestBody String approved) throws IOException, InterruptedException {
        try {
            String url      = reviewServiceUrl + "/reviews/acceptreject/" + reviewID;
            Object response = httpService.sendPutRequest(url, approved, Object.class);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            ResponseEntity.internalServerError().body("Error accepting or rejecting review with ID: " + reviewID);
            throw e;
        }
    }

    @GetMapping(value = "/aggregatedrating/{sku}")
    ResponseEntity<?> getAverage(@PathVariable("sku") final String sku) throws IOException, InterruptedException {
        try {
            String url      = reviewServiceUrl + "/aggregatedrating/" + sku;
            Object response = httpService.sendGetRequest(url, Object.class);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            ResponseEntity.internalServerError().body("Error getting aggregated rating for product with SKU: " + sku);
            throw e;
        }
    }
}
