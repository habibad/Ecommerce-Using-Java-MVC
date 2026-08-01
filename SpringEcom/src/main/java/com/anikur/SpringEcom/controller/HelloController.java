package com.anikur.SpringEcom.controller;

import com.anikur.SpringEcom.model.Product;
import com.anikur.SpringEcom.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class HelloController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProudct() {
        return new ResponseEntity<>(productService.fetchAll(), HttpStatus.OK);
    }

    @GetMapping("/product/{product_id}")
    public ResponseEntity<Product> productData(@PathVariable("product_id") int product_id) {
        Product product = productService.fetchProduct(product_id);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/addProudct", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addData(@RequestPart("product") String productJson,
                                     @RequestPart("image") MultipartFile image) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Product product = mapper.readValue(productJson, Product.class);
        product.setImageName(image.getOriginalFilename());
        product.setImageType(image.getContentType());
        product.setImageData(image.getBytes());
        return productService.saveData(product);
    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart("product") Product product,
                                        @RequestPart("image") MultipartFile image) {
        return productService.saveProduct(product, image);
    }

    @PutMapping("/updateProduct/{productId}")
    public ResponseEntity<?> updateProudct(@PathVariable int productId,
                                           @RequestPart("product") Product product,
                                           @RequestPart("image") MultipartFile image) {
        try {
            return productService.updateProductDetails(product, image);
        } catch (Exception e) {
            return new ResponseEntity<>("failed to update " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product/{productID}/image")
    public ResponseEntity<byte[]> getProductImage(@PathVariable int productID) {
        Product product = productService.fetchProductImage(productID);
        if (product == null || product.getImageData() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(product.getImageType()))
                .body(product.getImageData());
    }

    @GetMapping("/updateProduct/{productID}/image")
    public ResponseEntity<byte[]> getUpdateProductImage(@PathVariable int productID) {
        Product product = productService.fetchUpdateProductImage(productID);
        if (product == null || product.getImageData() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(product.getImageType()))
                .body(product.getImageData());
    }
}
