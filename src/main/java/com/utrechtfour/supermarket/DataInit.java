package com.utrechtfour.supermarket;

import com.utrechtfour.supermarket.model.Product;
import com.utrechtfour.supermarket.model.ProductCategories;
import com.utrechtfour.supermarket.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DataInit implements ApplicationRunner {

    private ProductRepository productRepository;

    @Autowired
    public DataInit(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception{
        Product product1 = new Product();
        product1.setBarcode((long)1234567);
        product1.setName("bananas");
        product1.setCreationTime(new Date());
        product1.setDescription("These are some bananas");
        product1.setCategory(1);
        product1.setSupplierIds(1);
        product1.setSupplierIds(2);
        product1.setBrandId(1);
        product1.setVatTarrif(1);


        Product product2 = new Product();
        product2.setBarcode((long) 2345678);
        product2.setName("pencil");
        product2.setCreationTime(new Date());
        product2.setDescription("This is a pencil");
        product2.setCategory(0);
        product2.setSupplierIds(1);
        product2.setBrandId(7);
        product2.setVatTarrif(2);





        productRepository.save(product1);
        productRepository.save(product2);
    }
}
