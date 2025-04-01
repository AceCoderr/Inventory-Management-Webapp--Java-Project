package com.webapp.invems.Controller;

import com.webapp.invems.Model.Product;
import com.webapp.invems.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "dashboard")
public class DashboardController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllproducts(){
        return productService.getAllProducts();
    }

    @PostMapping(value = "addProducts")
    public Product addProduct(@RequestBody Product product) {
        return productService.addProducts(product);
    }
}
