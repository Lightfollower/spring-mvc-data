package com.geekbrains.spring.mvc.services;

import com.geekbrains.spring.mvc.exceptions.ProductNotFoundException;
import com.geekbrains.spring.mvc.model.Product;
import com.geekbrains.spring.mvc.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Product> findRequiredProducts(Integer pageNumber, Integer minCost, Integer maxCost) {
        if (maxCost != null && minCost != null) {
            return productRepository.findAllByCostGreaterThanAndCostLessThan(PageRequest.of(pageNumber - 1, 5), minCost, maxCost).getContent();
        }

        if (minCost != null) {
            return productRepository.findAllByCostGreaterThan(PageRequest.of(pageNumber - 1, 5), minCost).getContent();
        }

        if (maxCost != null) {
            return productRepository.findAllByCostLessThan(PageRequest.of(pageNumber - 1, 5), maxCost).getContent();
        }
        return productRepository.findAll(PageRequest.of(pageNumber - 1, 5)).getContent();
    }
}
