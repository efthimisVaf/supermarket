package com.utrechtfour.supermarket.service;

import com.utrechtfour.supermarket.model.Product;
import com.utrechtfour.supermarket.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository repository;

    @Transactional
    public Optional<Product> getProductById (Long id){
        return repository.findById(id);
    }

    @Transactional
    public Product createProduct(Product product) {
        return repository.save(product);
    }

    @Transactional
    public void deleteProductById(Long id) {
        repository.deleteById(id);

    }

    @Transactional
    public Product updateProduct(Product product){
        return repository.save(product);
    }
}
