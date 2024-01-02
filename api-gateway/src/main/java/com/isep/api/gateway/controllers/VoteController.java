package com.isep.api.gateway.controllers;

import com.isep.api.gateway.services.HttpService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@Tag(name = "Vote", description = "Endpoints for managing votes")
public class VoteController {
    private final HttpService httpService;

    @Value("${services.vote.url}")
    private String voteServiceUrl;

    public VoteController(HttpService httpService) {this.httpService = httpService;}

    @Operation(summary = "add vote")
    @PutMapping("/reviews/{reviewID}/vote")
    public ResponseEntity<Boolean> addVote(@PathVariable(value = "reviewID") final Long reviewID,
            @RequestBody Object voteReviewDTO) throws IOException, InterruptedException {

        try {
            String url      = voteServiceUrl + "/reviews/" + reviewID + "/vote";
            Object response = httpService.sendPutRequest(url, voteReviewDTO, Object.class);
            return ResponseEntity.ok().body((Boolean) response);
        } catch (Exception e) {
            ResponseEntity.internalServerError().body("Error adding vote to review with ID: " + reviewID);
            throw e;
        }
    }
}
