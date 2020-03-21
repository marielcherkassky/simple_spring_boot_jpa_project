package com.repositories;

import com.dao.Manufacturer;
import com.dao.Product;
import com.dao.ProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByIdAndManufacturer(Long id, Manufacturer m);
}
