package com.utrechtfour.supermarket.service;

import com.utrechtfour.supermarket.model.Product;
import com.utrechtfour.supermarket.model.Supplier;
import com.utrechtfour.supermarket.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository repository;
    @Autowired
    SupplierSevice supplierSevice;

    @Transactional
    public Optional<Product> getProductById (Long id) {
               return repository.findById(id);
    }

    @Transactional
    public Product createProduct(Product product) {

            return repository.save(product);
    }


    @Transactional
    public Product updateProduct(Product newProduct){
        Product product = repository.findById(newProduct.getId()).get();

        if (product != null){
            System.out.println("sdada");
            if (newProduct.getBrand() == null) {
                newProduct.setBrand(product.getBrand());
            }

            if (newProduct.getCategory() == null) {
                newProduct.setCategory(product.getCategory());
            }

            if (newProduct.getUnit() == null) {
                newProduct.setUnit(product.getUnit().getUnitId());
            }

            if (newProduct.getSuppliers().isEmpty()) {
                newProduct.setSuppliers(product.getSuppliers());
            }

            if (newProduct.getVatTarrif() == null) {
                newProduct.setVatTarrif(product.getVatTarrif().getTariffId());
            }
        }
        return repository.save(newProduct);
    }



}
