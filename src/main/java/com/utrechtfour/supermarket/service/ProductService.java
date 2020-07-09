package com.utrechtfour.supermarket.service;

import com.utrechtfour.supermarket.model.Brand;
import com.utrechtfour.supermarket.model.Product;
import com.utrechtfour.supermarket.model.Supplier;
import com.utrechtfour.supermarket.repository.BrandRepository;
import com.utrechtfour.supermarket.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository repository;
    @Autowired
    SupplierSevice supplierSevice;
    @Autowired
    BrandService brandService;



    @Transactional
    public Optional<Product> getProductById (Long id) {
               return repository.findById(id);
    }

    @Transactional
    public Product createProduct(Product product) {
            Brand brand = product.getBrand();
            brand.setProduct(product);
            return repository.save(product);
    }


    @Transactional
    public Product updateProduct(Product newProduct){

        Product product = repository.findById(newProduct.getId()).get();

        if (newProduct.getBrand() != null) {
            Brand brand = newProduct.getBrand();
            product.setBrand(newProduct.getBrand());
            brand.setProduct(newProduct);
            brandService.removeBrandById(product.getId());
            brandService.createOrUpdateBrand(brand);

        }
        return repository.save(product);
    }



}
