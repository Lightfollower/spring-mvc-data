package com.geekbrains.spring.mvc.services;

import com.geekbrains.spring.mvc.exceptions.ProductNotFoundException;
import com.geekbrains.spring.mvc.model.Product;
import com.geekbrains.spring.mvc.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

//    public List<Product> getAll() {
//        return productRepository.findAll();
//    }

    public Product saveOrUpdate(Product product) {
        return productRepository.save(product);
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Can't found product with id = " + id));
    }

    public Product findByTitle(String title) {
        return productRepository.findOneByTitle(title);
    }

    public List<Product> findByMinCost(/*int pageNumber,*/ int minCost) {
        return productRepository.findAllByCostGreaterThan(minCost);
    }

    public List<Product> findByMaxCost(int maxCost) {
        return productRepository.findAllByCostLessThan(maxCost);
    }

    public List<Product> findByMaxAndMinCost(int minCost, int maxCost){
        return productRepository.findAllByCostGreaterThanAndCostLessThan(minCost, maxCost);
    }
//    public Page<Product> findByPage(int pageNumber, int pageSize) {
//        return productRepository.findAll(PageRequest.of(pageNumber, pageSize));
//    }

//    public List<Product> findAll() {
//        return productRepository.findAll();
//    }

    public List<Product> findAll(/* Integer page*/) {
//        if (page < 1L) {
//            page = 1;
//        }
        return productRepository.findAll(/*PageRequest.of(page - 1, 5)*/);
    }


}
