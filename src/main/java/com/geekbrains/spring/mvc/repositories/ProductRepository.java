package com.geekbrains.spring.mvc.repositories;

import com.geekbrains.spring.mvc.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findOneByTitle(String title);
    Page<Product> findAll(Pageable pageable);
    Page<Product> findAllByCostGreaterThanAndCostLessThan(Pageable pageable, int minCost, int maxCost);
}