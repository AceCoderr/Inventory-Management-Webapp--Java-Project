package com.webapp.invems.services;

import com.webapp.invems.Model.Product;
import com.webapp.invems.Model.StoreUser;
import com.webapp.invems.repo.ProductRepository;
import com.webapp.invems.repo.StoreUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StoreUserRepository storeUserRepository;

    public List<Product> getAllProducts(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Optional<StoreUser> user = storeUserRepository.findByUsername(username);
        if (user.isPresent())
        {
            return productRepository.findByUserId(user.get().getId());
        }
        return Collections.emptyList();
    }

    public Product addProducts(Product product){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Optional<StoreUser> user = storeUserRepository.findByUsername(username);
        if(user.isPresent())
        {
            product.setStoreUser(user.get());
        }
        else{
            System.out.println("Not present");
        }
        return productRepository.save(product);
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }
}
