package com.geekbrains.spring.mvc.services;

import com.geekbrains.spring.mvc.exceptions.ProductNotFoundException;
import com.geekbrains.spring.mvc.model.Product;
import com.geekbrains.spring.mvc.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product saveOrUpdate(Product product) {
        return productRepository.save(product);
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Can't found product with id = " + id));
    }

    public Product findByTitle(String title) {
        return productRepository.findOneByTitle(title);
    }

    public Page<Product> findByMinCost(int pageNumber, int minCost) {
        return productRepository.findAllByCostGreaterThan(PageRequest.of(pageNumber-1,5), minCost);
    }

    public Page<Product> findByMaxCost(int pageNumber, int maxCost) {
        return productRepository.findAllByCostLessThan(PageRequest.of(pageNumber-1,5), maxCost);
    }

    public Page<Product> findByMaxAndMinCost(int pageNumber, int minCost, int maxCost){
        return productRepository.findAllByCostGreaterThanAndCostLessThan(PageRequest.of(pageNumber-1,5),minCost, maxCost);
    }

    public Page<Product> findAll( Integer pageNumber) {
        if (pageNumber < 1L) {
            pageNumber = 1;
        }
        return productRepository.findAll(PageRequest.of(pageNumber - 1, 5));
    }
}
