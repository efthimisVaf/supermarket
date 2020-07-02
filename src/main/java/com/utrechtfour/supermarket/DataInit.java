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
        Brand chiquita = new Brand();
        chiquita.setName("Chiquita");

        Brand faberCastell = new Brand();
        faberCastell.setName("Faber-Castell");


        brandRepository.save(faberCastell);
        brandRepository.save(chiquita);




        Product product1 = new Product();
        product1.setBarcode("1234567");
        product1.setName("bananas");
        product1.setCreationTime(new Date());
        product1.setDescription("These are some bananas");
        product1.setCategory(2);
        product1.setVatTarrif(1);
        product1.setBrand(chiquita);
        chiquita.setProduct(product1);

        Product product2 = new Product();
        product2.setBarcode("2345678");
        product2.setName("pencil");
        product2.setCreationTime(new Date());
        product2.setDescription("This is a pencil");
        product2.setCategory(1);
        product2.setVatTarrif(2);
        product2.setPrice(BigDecimal.valueOf(01.50));
        product2.setBrand(faberCastell);
        faberCastell.setProduct(product2);

        Supplier stationarySupplier = new Supplier();
        stationarySupplier.setName("Stationary supplier");
        supplierRepository.save(stationarySupplier);



        productRepository.save(product1);
        productRepository.save(product2);
    }
}
