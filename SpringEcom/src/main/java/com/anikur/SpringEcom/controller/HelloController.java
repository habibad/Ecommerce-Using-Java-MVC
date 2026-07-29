package com.anikur.SpringEcom.controller;

import com.anikur.SpringEcom.model.Product;
import com.anikur.SpringEcom.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class HelloController {
    @Autowired
    private ProductService productService;
    @GetMapping ("/products")
    public List<Product> getAllProudct(){
        return productService.fetchAll();
    }
//    product/6
    @GetMapping("/product/{product_id}")
    public Product productData(@PathVariable("product_id") int product_id){
        return productService.fetchProduct(product_id);
    }
}
