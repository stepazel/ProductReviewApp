package com.isep.api.gateway.controllers;

import com.isep.api.gateway.services.HttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final HttpService httpService;

    @Value("${services.product.url}")
    private String productServiceUrl;

    @Autowired
    public ProductController(HttpService httpService) {
        this.httpService = httpService;
    }

    @PatchMapping("/{sku}/accept")
    public ResponseEntity<?> publish(@PathVariable String sku) throws IOException, InterruptedException {
        try {
            String url      = productServiceUrl + "/products/" + sku + "/accept";
            Object response = httpService.sendPatchRequest(url, Object.class);
            return ResponseEntity.ok().body(response);
        } catch (IOException | InterruptedException e) {
            ResponseEntity.internalServerError().body("Error accepting product with SKU: " + sku);
            throw e;
        }
    }

    @GetMapping
    public ResponseEntity<?> getCatalog() throws IOException, InterruptedException {
        try {
            String url      = productServiceUrl + "/products";
            Object response = httpService.sendGetRequest(url, Object.class);
            return ResponseEntity.ok().body(response);
        } catch (IOException | InterruptedException e) {
            ResponseEntity.internalServerError().body("Error fetching product catalog");
            throw e;
        }
    }

    @GetMapping("/details")
    public ResponseEntity<?> getCatalogWithDetails() throws IOException, InterruptedException {
        try {
            String url      = productServiceUrl + "/products/details";
            Object response = httpService.sendGetRequest(url, Object.class);
            return ResponseEntity.ok().body(response);
        } catch (IOException | InterruptedException e) {
            ResponseEntity.internalServerError().body("Error fetching product details");
            throw e;
        }
    }

    @GetMapping("/details/{sku}")
    public ResponseEntity<?> getDetails(@PathVariable String sku) throws IOException, InterruptedException {
        try {
            String url      = productServiceUrl + "/products/details/" + sku;
            Object response = httpService.sendGetRequest(url, Object.class);
            return ResponseEntity.ok().body(response);
        } catch (IOException | InterruptedException e) {
            ResponseEntity.internalServerError().body("Error fetching product details for SKU: " + sku);
            throw e;
        }
    }

    @GetMapping("/{sku}")
    public ResponseEntity<?> getProductBySku(@PathVariable String sku) throws IOException, InterruptedException {
        try {
            String url      = productServiceUrl + "/products/" + sku;
            Object response = httpService.sendGetRequest(url, Object.class);
            return ResponseEntity.ok().body(response);
        } catch (IOException | InterruptedException e) {
            ResponseEntity.internalServerError().body("Error fetching product by SKU: " + sku);
            throw e;
        }
    }

    @GetMapping("/designation/{designation}")
    public ResponseEntity<?> findAllByDesignation(@PathVariable String designation) throws IOException, InterruptedException {
        try {
            String url      = productServiceUrl + "/products/designation/" + designation;
            Object response = httpService.sendGetRequest(url, Object.class);
            return ResponseEntity.ok().body(response);
        } catch (IOException | InterruptedException e) {
            ResponseEntity.internalServerError().body("Error fetching products by designation: " + designation);
            throw e;
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Object createProductDTO) throws IOException, InterruptedException {
        try {
            String url      = productServiceUrl + "/products";
            Object response = httpService.sendPostRequest(url, createProductDTO, Object.class);
            return ResponseEntity.ok().body(response);
        } catch (IOException | InterruptedException e) {
            ResponseEntity.internalServerError().body("Error creating product");
            throw e;
        }
    }

    @PatchMapping("/{sku}")
    public ResponseEntity<?> update(@PathVariable String sku, @RequestBody Object product) throws IOException, InterruptedException {
        try {
            String url      = productServiceUrl + "/products/" + sku;
            Object response = httpService.sendPatchRequest(url, product, Object.class);
            return ResponseEntity.ok().body(response);
        } catch (IOException | InterruptedException e) {
            ResponseEntity.internalServerError().body("Error updating product with SKU: " + sku);
            throw e;
        }
    }

    @DeleteMapping("/{sku}")
    public ResponseEntity<?> delete(@PathVariable String sku) throws IOException, InterruptedException {
        try {
            String url = productServiceUrl + "/products/" + sku;
            httpService.sendDeleteRequest(url);
            return ResponseEntity.noContent().build();
        } catch (IOException | InterruptedException e) {
            ResponseEntity.internalServerError().body("Error deleting product with SKU: " + sku);
            throw e;
        }
    }
}
