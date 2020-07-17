package com.utrechtfour.supermarket.service;

import com.utrechtfour.supermarket.model.Brand;
import com.utrechtfour.supermarket.model.Category;
import com.utrechtfour.supermarket.model.Product;
import com.utrechtfour.supermarket.model.Supplier;
import com.utrechtfour.supermarket.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ValidationException;
import java.util.*;

@Service
public class ProductService {

    @Autowired
    ProductRepository repository;
    @Autowired
    SupplierSevice supplierSevice;
    @Autowired
    BrandService brandService;
    @Autowired
    CategoryService categoryService;


    @Transactional
    public Optional<Product> getProductById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public void associateProductsAndSuppliers(Product product) {
        Set<Supplier> suppliers = new HashSet<>();
        for (Supplier s : product.getSuppliers()
        ) {
            if (s.getId() != null) {
                suppliers.add(supplierSevice.getSupplierById(s.getId()).get());
            }
        }

        for (Supplier s : product.getSuppliers()
        ) {
            if (s.getId() == null) {
                suppliers.add(s);
            }
        }
        product.setSuppliers(suppliers);
    }

    @Transactional
    public Product createProduct(Product product) {
        //If brand is not new, Associates the brand with the product
        if (product.getBrand().getId() != null) {
            Brand brand = brandService.getBrandById(product.getBrand().getId()).get();
            product.setBrand(brand);
        }

        if (product.getCategory().getId() != null) {
            Category category = categoryService.getCategoryById(product.getCategory().getId()).get();
            product.setCategory(category);
        }

        associateProductsAndSuppliers(product);
        repository.save(product);
        return repository.findById(product.getId()).get();
    }

    @Transactional
    public Product updateProduct(Product newProduct, Long id) {

        Product product = repository.findById(id).get();


        Optional<Brand> brand = brandService.getBrandById(newProduct.getBrand().getId());
        if (brand.isPresent()) {
            product.setBrand(brand.get());
        } else throw new NoSuchElementException("Brand with an id of " + newProduct.getBrand().getId() + " not found");


        Optional<Category> category = categoryService.getCategoryById(newProduct.getCategory().getId());
        if (category.isPresent()) {
            product.setCategory(category.get());
        } else throw new NoSuchElementException("Category with an id of " + newProduct.getCategory().getId() + " not found");


        for (Supplier s:newProduct.getSuppliers()
             ) {
            if (!supplierSevice.getSupplierById(s.getId()).isPresent())
            {throw new NoSuchElementException("Cannot find supplier with an id of "+ s.getId());}
        }

        associateProductsAndSuppliers(newProduct);

        product.setSuppliers(newProduct.getSuppliers());
        product.setBarcode(newProduct.getBarcode());
        product.setName(newProduct.getName());
        product.setVatTarrif(newProduct.getVatTarrif().getTariffId());
        product.setUnit(newProduct.getUnit().getUnitId());
        product.setPrice(newProduct.getPrice());

        System.out.println(product.getCategory().getName());
        return repository.save(product);
    }


}
