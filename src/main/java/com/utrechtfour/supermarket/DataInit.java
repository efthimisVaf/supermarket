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
import java.util.ArrayList;
import java.util.Date;
/*
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

        //Creates brands
        Brand chiquita = new Brand();
        chiquita.setName("Chiquita");

        Brand faberCastell = new Brand();
        faberCastell.setName("Faber-Castell");






        //creates suppliers
        Supplier stationarySupplier = new Supplier();
        stationarySupplier.setName("stationary Supplier");
        Supplier genericSupplier = new Supplier();
        genericSupplier.setName("generic Supplier");
        Supplier fruitSupplier = new Supplier();
        fruitSupplier.setName("fruitSupplier");

        //Creates a product list on the suppliers
        stationarySupplier.setProducts(new ArrayList<Product>());
        genericSupplier.setProducts(new ArrayList<Product>());
        fruitSupplier.setProducts(new ArrayList<Product>());



        //Creates products
        Product bananas = new Product();
        bananas.setBarcode("1234567890123");
        bananas.setName("Bananas");
        bananas.setCreationTime(new Date());
        bananas.setUpdateTime(new Date());
        bananas.setDescription("These are some bananas");
        bananas.setCategory(2);
        bananas.setVatTarrif(2);
        bananas.setBrand(chiquita);
        bananas.setUnit(2);
        bananas.setPrice(BigDecimal.valueOf(0.99));

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
        faberCastell.setProduct(pencil);
        pencil.setPrice(BigDecimal.valueOf(0.5));
        pencil.setUnit(1);

        faberCastell.setProduct(pencil);



        //Adds suppliers in products
        bananas.getSuppliers().add(fruitSupplier);
        bananas.getSuppliers().add(genericSupplier);
        pencil.getSuppliers().add(stationarySupplier);
        pencil.getSuppliers().add(genericSupplier);

        for (Supplier s: pencil.getSuppliers()
             ) {
            supplierRepository.save(s);
        }

        for (Supplier s: bananas.getSuppliers()
        ) {
            supplierRepository.save(s);
        }


        //Adds products in suppliers
        genericSupplier.addProduct(bananas);
        fruitSupplier.addProduct(bananas);
        genericSupplier.addProduct(pencil);
        stationarySupplier.addProduct(pencil);

        //Persists the products with the suppliers;


        productRepository.save(bananas);
        productRepository.save(pencil);


    }
}
*/