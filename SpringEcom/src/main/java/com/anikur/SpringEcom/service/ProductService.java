package com.anikur.SpringEcom.service;

import com.anikur.SpringEcom.model.Product;
import com.anikur.SpringEcom.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
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
        return productRepo.getReferenceById(productId);
    }
}
