package com.anikur.SpringEcom.service;

import com.anikur.SpringEcom.model.Product;
import com.anikur.SpringEcom.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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

    public ResponseEntity<?> saveData(Product product, MultipartFile image) {
        try {
            product.setImageName(image.getOriginalFilename());
            product.setImageType(image.getContentType());
            product.setImageData(image.getBytes());
            Product insertedProduct = productRepo.save(product);
            return new ResponseEntity<>(insertedProduct, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> saveProduct(Product product, MultipartFile image) {
        try {
            product.setImageName(image.getOriginalFilename());
            product.setImageType(image.getContentType());
            product.setImageData(image.getBytes());
            Product insertProduct = productRepo.save(product);
            return new ResponseEntity<>(insertProduct, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("throw the error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public Product fetchProductImage(int productID) {
        return productRepo.findById(productID).orElse(null);
    }

    public ResponseEntity<?> updateProductDetails(Product product, MultipartFile image) {
        try {
            product.setImageName(image.getOriginalFilename());
            product.setImageType(image.getContentType());
            product.setImageData(image.getBytes());
            Product updatedProduct = productRepo.save(product);
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("throw the error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Product fetchUpdateProductImage(int productID) {
        return productRepo.findById(productID).orElse(null);
    }

    public Product deleteProduct(int productId) {
        return productRepo.findById(productId).orElse(null);
    }

    public void deleteAction(Integer id) {
        productRepo.deleteById(id);
    }
}
