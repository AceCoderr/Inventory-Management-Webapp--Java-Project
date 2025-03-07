package com.webapp.invems.Controller;

import com.webapp.invems.Model.Product;
import com.webapp.invems.Model.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Dashboard/")
public class DashboardController {
    @Autowired
    private ProductService productService;

    @GetMapping(value = "/{id}")
    public List<Product> getAllproducts(@PathVariable Long id){
        return productService.getAllProducts(id);
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.addProducts(product);
    }
}
