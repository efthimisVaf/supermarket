package com.utrechtfour.supermarket;

import com.utrechtfour.supermarket.model.Brand;
import com.utrechtfour.supermarket.model.Product;
import com.utrechtfour.supermarket.model.ProductCategories;
import com.utrechtfour.supermarket.model.Supplier;
import com.utrechtfour.supermarket.repository.BrandRepository;
import com.utrechtfour.supermarket.repository.ProductRepository;
import com.utrechtfour.supermarket.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Component
public class DataInit implements ApplicationRunner {

    private ProductRepository productRepository;
    private BrandRepository brandRepository;
    private SupplierRepository supplierRepository;


    @Autowired
    public DataInit(ProductRepository productRepository, BrandRepository brandRepository, SupplierRepository supplierRepository){
        this.productRepository = productRepository;
        this.brandRepository = brandRepository;
        this.supplierRepository = supplierRepository;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception{

        //Creates brands and populates them into the table
        Brand chiquita = new Brand();
        chiquita.setName("Chiquita");
        Brand faberCastell = new Brand();
        faberCastell.setName("Faber-Castell");


        //Creates products
        Product bananas = new Product();
        bananas.setBarcode("1234567890123");
        bananas.setName("Bananas");
        bananas.setCreationTime(new Date());
        bananas.setDescription("These are some bananas");
        bananas.setCategory(2);
        bananas.setVatTarrif(2);
        bananas.setBrand(chiquita);
        bananas.setUnit(2);
        chiquita.setProduct(bananas);
        Product pencil = new Product();
        pencil.setBarcode("2345678901234");
        pencil.setName("Pencil");
        pencil.setCreationTime(new Date());
        pencil.setUpdateTime(new Date());
        pencil.setDescription("A very nice pencil");
        pencil.setCategory(1);
        pencil.setVatTarrif(3);
        pencil.setBrand(faberCastell);
        pencil.setPrice(BigDecimal.valueOf(0.5));
        pencil.setUnit(1);
       // faberCastell.setProduct(pencil);

        //creates suppliers
        Supplier stationarySupplier = new Supplier();
        Supplier genericSupplier = new Supplier();
        Supplier fruitSupplier = new Supplier();
        supplierRepository.save(stationarySupplier);
        supplierRepository.save(genericSupplier);
        supplierRepository.save(fruitSupplier);
        //Adds suppliers to products
        bananas.getSuppliers().add(fruitSupplier);
        bananas.getSuppliers().add(genericSupplier);
        pencil.getSuppliers().add(stationarySupplier);
        pencil.getSuppliers().add(genericSupplier);
        //Persists the products with the suppliers;
        productRepository.save(bananas);
        productRepository.save(pencil);
    }
}
