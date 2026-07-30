package com.anikur.SpringEcom.service;

import com.anikur.SpringEcom.model.Product;
import com.anikur.SpringEcom.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    public List<Product> fetchAll() {
        return productRepo.findAll();
    }

    public Product fetchProduct(int productId) {
        return productRepo.findById(productId).orElse(null);
    }

    public ResponseEntity<?> saveData(Product product) {
        Product insertProudct = productRepo.save(product);
        try{

            return new ResponseEntity<>(insertProudct, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>("insert Error: "+ e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
