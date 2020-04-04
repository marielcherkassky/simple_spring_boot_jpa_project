package com.repositories;

import com.dao.Identities.ProductId;
import com.dao.Manufacturer;
import com.dao.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, ProductId> {
    List<Product> findByPidAndManufacturer(Long id, Manufacturer m);
    List<Product> findByPidIn(List<Long>pids);

}
