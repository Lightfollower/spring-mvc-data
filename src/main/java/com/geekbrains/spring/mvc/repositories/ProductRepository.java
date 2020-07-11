package com.geekbrains.spring.mvc.repositories;

import com.geekbrains.spring.mvc.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findOneByTitle(String title);
    List<Product> findAllByCostGreaterThan(int minCost);
    List<Product> findAllByCostLessThan(int maxCost);
    List<Product> findAllByCostGreaterThanAndCostLessThan(int minCost, int maxCost);
}