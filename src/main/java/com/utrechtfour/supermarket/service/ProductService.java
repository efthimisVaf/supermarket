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
            if (product.getBrand().getId() == null){
                Brand newBrand = product.getBrand();
                newBrand.addProduct(product);
                brandService.createOrUpdateBrand(newBrand);
                return repository.save(product);
            }

                Brand newBrand = product.getBrand();
                brandService.createOrUpdateBrand(newBrand);
                return repository.save(product);

    }


    @Transactional
    public Product updateProduct(Product newProduct, Long id){
    Product product = repository.findById(id).get();

        for (Supplier s:newProduct.getSuppliers()) {
            product.addSupplier(s);
        }
    product.setBarcode(newProduct.getBarcode());
    product.setBrand(product.getBrand());
    product.setName(newProduct.getName());
    product.setCategory(newProduct.getCategory());
    product.setVatTarrif(newProduct.getVatTarrif().getTariffId());
    product.setUnit(newProduct.getUnit().getUnitId());
    product.setPrice(newProduct.getPrice());





    return repository.save(product);


    }



}
