package com.webapp.invems.Model;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts(Long userId){
        Optional<List<Product>> products = productRepository.findByUserId(userId);
        return products.orElse(Collections.emptyList());
    }

    public Product addProducts(Product product){
        return productRepository.save(product);
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }
}
