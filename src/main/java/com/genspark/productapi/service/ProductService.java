package com.genspark.productapi.service;

import com.genspark.productapi.model.Product;
import java.util.Optional;

public interface ProductService {
    Optional<Product> getProductById(Long productId);
    void addProduct(Product product);
    void updateProduct(Product product);
    void deleteProductById(Long productId);
}
