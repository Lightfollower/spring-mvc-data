package com.geekbrains.spring.mvc.repositories;

import com.geekbrains.spring.mvc.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository /*extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product>*/ {
    private List<Product> products;

    public List<Product> findAll() {
        return products;
    }

    public List<Product> findById(Long id) {
        return products;
    }

    public Product save(Product product) {
        return product;
    }


//    Product findOneByTitle(String title);
//    List<Product> findAllByCostGreaterThan(int minCost);
}