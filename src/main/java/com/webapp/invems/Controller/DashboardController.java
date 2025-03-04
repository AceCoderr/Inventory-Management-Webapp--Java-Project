package com.webapp.invems.Controller;

import com.webapp.invems.Model.Product;
import com.webapp.invems.Model.ProductRepository;
import com.webapp.invems.Model.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Dashboard/")
public class DashboardController {
    @Autowired
    private ProductService productService;

    @GetMapping(value = "/{userId}")
    public List<Product> getAllproducts(@PathVariable Long userId){
        return productService.getAllProducts(userId);
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.addProducts(product);
    }
}
