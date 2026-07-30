package com.anikur.SpringEcom.controller;

import com.anikur.SpringEcom.model.Product;
import com.anikur.SpringEcom.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class HelloController {
    @Autowired
    private ProductService productService;
    @GetMapping ("/products")
    public ResponseEntity<List<Product>> getAllProudct(){
        return new ResponseEntity<>(productService.fetchAll(), HttpStatus.OK);
    }
//    product/6
    @GetMapping("/product/{product_id}")
    public ResponseEntity<Product> productData(@PathVariable("product_id") int product_id){
        Product product = productService.fetchProduct(product_id);
        if(product != null){
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @PostMapping("/addProudct")
    public ResponseEntity<?> addData(@RequestBody Product product){
        return productService.saveData(product);

    }
}
