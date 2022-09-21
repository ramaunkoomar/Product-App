package com.genspark.productapi.controller;

import com.genspark.productapi.exception.ProductNotFoundException;
import com.genspark.productapi.model.Product;
import com.genspark.productapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/product")
public class ProductController {
    private Logger logger = Logger.getLogger(ProductController.class.getName());
    @Autowired
    private ProductService productService;

    @GetMapping("{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable("productId") Long productId) {
        try {
            Optional<Product> product = productService.getProductById(productId);
            return product.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
        }
        catch (Exception e) {
            logger.severe(e.getMessage());
           return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        try {
            productService.addProduct(product);
            return ResponseEntity.created(URI.create("")).build();
        }
        catch(Exception e) {
            logger.severe(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        try {
            productService.updateProduct(product);
            return ResponseEntity.ok().build();
        }
        catch(IllegalArgumentException e) {
            logger.severe(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
        catch(ProductNotFoundException e) {
            logger.severe(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{productId}")
    public ResponseEntity<Product> deleteProductById(@PathVariable("productId") Long productId) {
        try {
            productService.deleteProductById(productId);
            return ResponseEntity.noContent().build();
        }
        catch(IllegalArgumentException e) {
            logger.severe(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
        catch(ProductNotFoundException e) {
            logger.severe(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
