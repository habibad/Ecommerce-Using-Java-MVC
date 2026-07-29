package com.anikur.SpringEcom.repo;

import com.anikur.SpringEcom.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Integer> {
}
