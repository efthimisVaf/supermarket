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
import java.util.*;

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
        //If brand is not new, Associates the brand with the product
        if (product.getBrand().getId() != null){
            Brand brand = brandService.getBrandById(product.getBrand().getId()).get();
            product.setBrand(brand);
        }
        ///////////////////////////////////////
        //Associates suppliers with products
        Set<Supplier> suppliers = new HashSet<>();
        for (Supplier s: product.getSuppliers()
             ) {
            if (s.getId() != null)
            {suppliers.add(supplierSevice.getSupplierById(s.getId()).get());}
        }

        for (Supplier s: product.getSuppliers()
        ) {
            if (s.getId() == null)
            {suppliers.add(s);}
        }


        product.setSuppliers(suppliers);
        return repository.save(product);
    }



    @Transactional
    public Product updateProduct(Product newProduct, Long id){
    Product product = repository.findById(id).get();

    if (newProduct.getBrand().getId() != null){
        System.out.println("Aaaaaaa");
        Brand brand = brandService.getBrandById(newProduct.getBrand().getId()).get();
        System.out.println(newProduct.getBrand().getId());
        product.setBrand(brand);
    }

    else {
        product.setBrand(newProduct.getBrand());
    }



    Set<Supplier> suppliers = new HashSet<>();
    for (Supplier s: newProduct.getSuppliers()
    ) {
        if (s.getId() != null)
        {suppliers.add(supplierSevice.getSupplierById(s.getId()).get());}
    }

    for (Supplier s: newProduct.getSuppliers()
    ) {
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        if (s.getId() == null)
        {suppliers.add(s);}
    }

    product.setSuppliers(suppliers);

    product.setBarcode(newProduct.getBarcode());
    product.setName(newProduct.getName());
    product.setCategory(newProduct.getCategory());
    product.setVatTarrif(newProduct.getVatTarrif().getTariffId());
    product.setUnit(newProduct.getUnit().getUnitId());
    product.setPrice(newProduct.getPrice());
    return repository.save(product);

    }



}
