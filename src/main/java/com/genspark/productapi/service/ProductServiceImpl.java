package com.genspark.productapi.service;

import com.genspark.productapi.dao.ProductDao;
import com.genspark.productapi.exception.ProductNotFoundException;
import com.genspark.productapi.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Override
    public Optional<Product> getProductById(Long productId) {
        try {
            if(productId == null) {
                throw new IllegalArgumentException("Product ID cannot be null");
            }
            if(productId == 0) {
                return Optional.empty();
            }
            return productDao.findById(productId);
        }
        catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void addProduct(Product product) {
        try {
            if(product == null) {
                throw new IllegalArgumentException("Product cannot be null.");
            }
            productDao.save(product);
        }
        catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void updateProduct(Product product) {
        try {
            if(product == null) {
                throw new IllegalArgumentException("Product cannot be null.");
            }

            if(getProductById(product.getId()).isEmpty()) {
                throw new ProductNotFoundException("Attempting to update a non-existent product.");
            }

            productDao.save(product);
        }
        catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void deleteProductById(Long productId) {
        try {
            if(productId == null) {
                throw new IllegalArgumentException("Product ID cannot be null.");
            }

            if(productId == 0 || getProductById(productId).isEmpty()) {
                throw new ProductNotFoundException("Attempting to delete non-existent product.");
            }

            productDao.deleteById(productId);
        }
        catch (Exception e) {
            throw e;
        }
    }
}
