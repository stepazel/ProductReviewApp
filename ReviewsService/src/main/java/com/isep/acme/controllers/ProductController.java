package com.isep.acme.controllers;

import com.isep.acme.model.Product;
import com.isep.acme.model.dto.CreateProductDTO;
import com.isep.acme.model.dto.ProductDTO;
import com.isep.acme.model.dto.ProductDetailDTO;
import com.isep.acme.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;


@Tag(name = "Product", description = "Endpoints for managing  products")
@RestController
@RequestMapping("/products")
class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductService service;
    private final String         productServiceUrl = "http://localhost:8081/";
    private final RestTemplate   restTemplate      = new RestTemplate();
    /*private final ServiceBusSenderClient senderClient;
    private final ServiceBusReceiverClient receiverClient;*/

    public ProductController(ProductService service) {
        this.service = service;
        /*this.senderClient = new ServiceBusClientBuilder()
                .connectionString(connectionString)
                .sender()
                .queueName("productReviewQueue")
                .buildClient();
        this.receiverClient = new ServiceBusClientBuilder()
                .connectionString(connectionString)
                .receiver()
                .queueName("productReviewQueue")
                .buildClient();*/
    }


    @Operation(summary = "gets catalog, i.e. all products")
    @GetMapping
    public ResponseEntity<Iterable<ProductDTO>> getCatalog() {
        // create a get request to http://localhost:8081
        var response = this.restTemplate.getForObject(this.productServiceUrl, String.class);

        // TODO co chci udelat je ze tento modul bude pouze dummy.. vsechny requesty pujdou na localhost:8081 (products service)
        // tohle muze klidne vracet string
        // v tom ProductsService modulu pak budu mit v podstate tent ocontroller, service, domain modely, JPA modely a repository
        // nejdriv vyzkouset PoC na teto (getCatalog) metode a pak to rozsirit na vsechny ostatni!!


        final var products = service.getCatalog();
        //        final var response = this.restTemplate.getForObject(this.productServiceUrl, Iterable.class);

        //
        return ResponseEntity.ok().body(products);
    }

    @Operation(summary = "gets catalog with details, i.e. all products")
    @GetMapping(value = "/details")
    public ResponseEntity<Iterable<ProductDetailDTO>> getCatalogWithDetails() {
        final var products = service.getCatalogDetails();

        return ResponseEntity.ok().body(products);
    }

    @Operation(summary = "gets the details of a product by sku")
    @GetMapping(value = "/details/{sku}")
    public ResponseEntity<ProductDetailDTO> getDetails(@PathVariable("sku") final String sku) {

        final Optional<ProductDetailDTO> product = service.getDetails(sku);

        if (product.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.");
        else
            return ResponseEntity.ok().body(product.get());
    }

    @Operation(summary = "finds product by sku")
    @GetMapping(value = "/{sku}")
    public ResponseEntity<ProductDTO> getProductBySku(@PathVariable("sku") final String sku) {
        //        senderClient.sendMessage(new ServiceBusMessage("Hello, world!"));
        //        System.out.print("Sent a message to queue");
        //        senderClient.close();
        //
        //        var message = receiverClient.receiveMessages(1).iterator().next();
        //        System.out.printf("Message received: %s from queue: %s%n", message.getBody(), receiverClient.getEntityPath());
        //        receiverClient.close();

        final Optional<ProductDTO> product = service.findBySku(sku);

        if (product.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.");
        else
            return ResponseEntity.ok().body(product.get());
    }

    @Operation(summary = "finds product by designation")
    @GetMapping(value = "/designation/{designation}")
    public ResponseEntity<Iterable<ProductDTO>> findAllByDesignation(@PathVariable("designation") final String designation) {

        final Iterable<ProductDTO> products = service.findByDesignation(designation);

        return ResponseEntity.ok().body(products);
    }

    @Operation(summary = "creates a product")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductDTO> create(@RequestBody CreateProductDTO createProductDTO) {
        try {
            final ProductDTO product = service.create(createProductDTO);
            return new ResponseEntity<ProductDTO>(product, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Product must have a unique SKU.");
        }
    }

    @Operation(summary = "updates a product")
    @PatchMapping(value = "/{sku}")
    public ResponseEntity<ProductDTO> Update(@PathVariable("sku") final String sku,
            @RequestBody final Product product) {

        final ProductDTO productDTO = service.updateBySku(sku, product);

        if (productDTO == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.");
        else
            return ResponseEntity.ok().body(productDTO);
    }

    @Operation(summary = "deletes a product")
    @DeleteMapping(value = "/{sku}")
    public ResponseEntity<Product> delete(@PathVariable("sku") final String sku) {

        service.deleteBySku(sku);
        return ResponseEntity.noContent().build();
    }
}
