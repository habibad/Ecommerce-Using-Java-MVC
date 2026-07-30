package com.anikur.SpringEcom.controller;

import com.anikur.SpringEcom.model.Product;
import com.anikur.SpringEcom.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
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
    @PostMapping(value = "/addProudct", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addData(@RequestPart("product") String productJson, @RequestPart("image") MultipartFile image) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        Product product = mapper.readValue(productJson, Product.class);
        product.setImageName(image.getOriginalFilename());
        product.setImageType(image.getContentType());
        product.setImageData(image.getBytes());
        return productService.saveData(product);

    }
}
