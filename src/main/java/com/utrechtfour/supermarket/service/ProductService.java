package com.utrechtfour.supermarket.service;

import com.utrechtfour.supermarket.model.Brand;
import com.utrechtfour.supermarket.model.Category;
import com.utrechtfour.supermarket.model.Product;
import com.utrechtfour.supermarket.model.Supplier;
import com.utrechtfour.supermarket.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyNameException;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ValidationException;
import java.math.BigDecimal;
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
                try {
                    Supplier supplier = supplierSevice.getSupplierById(s.getId()).get();
                    suppliers.add(supplier);
                }catch (Exception e){
                    throw new InvalidConfigurationPropertyValueException("supplier", "id = "+ s.getId(),"Could not find supplier with this id");
                }
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
    public Product createProduct(Product product) throws InvalidConfigurationPropertyValueException {

            try {
                Brand brand = brandService.getBrandById(product.getBrand().getId()).get();
                product.setBrand(brand);
            } catch (Exception e) {
                throw new InvalidConfigurationPropertyValueException("brand", "id = " + product.getBrand().getId(),"Could not find brand with this id");
            }

            try {
                Category category = categoryService.getCategoryById(product.getCategory().getId()).get();
                product.setCategory(category);
            } catch (Exception e) {
                throw new InvalidConfigurationPropertyValueException("category","id = " + product.getCategory().getId(),"Could not find category with this id");
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


    public Product patchProduct(Map<String, Object> updates, Long id) {
        Product product = repository.findById(id).get();

        if (updates.containsKey("barcode")){
            product.setBarcode(String.valueOf(updates.get("barcode")));
        }

        if (updates.containsKey("name")){
            product.setBarcode(String.valueOf(updates.get("name")));
        }

        if (updates.containsKey("description")){
            product.setDescription(String.valueOf(updates.get("description")));
        }

        if (updates.containsKey("category")){
            Long categoryId = Long.valueOf(updates.get("category").toString().replaceAll("\\D+",""));
            product.setCategory(categoryService.getCategoryById(categoryId).get());
        }

        if (updates.containsKey("brand")){
            Long brandId = Long.valueOf(updates.get("brand").toString().replaceAll("\\D+",""));
            product.setBrand(brandService.getBrandById(brandId).get());
        }

        if (updates.containsKey("price")){
            product.setPrice((BigDecimal) updates.get("price"));
        }

        if (updates.containsKey("vatTarrif")){
            int vatId = Integer.parseInt((updates.get("vatTarrif").toString().replaceAll("\\D+","")));
            product.setVatTarrif(vatId);
        }

        if (updates.containsKey("unit")){
            int unitId = Integer.parseInt((updates.get("unit").toString().replaceAll("\\D+","")));
            product.setUnit(unitId);
        }

        if (updates.containsKey("suppliers")){

            throw new InvalidConfigurationPropertyNameException("supplier",null);
        }

        return repository.save(product);
    }
}
