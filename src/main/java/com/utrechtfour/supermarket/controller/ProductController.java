package com.utrechtfour.supermarket.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.utrechtfour.supermarket.errors.ApiError;
import com.utrechtfour.supermarket.model.Product;
import com.utrechtfour.supermarket.model.Supplier;
import com.utrechtfour.supermarket.service.ProductService;
import com.utrechtfour.supermarket.views.RestViews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product/{id}")
    @ResponseStatus(HttpStatus.OK)
    @JsonView({RestViews.ProductView.class})
    public ResponseEntity<Product> getProductById (@PathVariable Long id, HttpServletResponse response){
        if (productService.getProductById(id).isPresent()){
            return new ResponseEntity(productService.getProductById(id), HttpStatus.OK);
        }
          else return new ResponseEntity("Cannot find product with an id of " + id, HttpStatus.BAD_REQUEST);
    }





    @Transactional
    @PostMapping("/product")
    @JsonView({RestViews.ProductView.class})
    public ResponseEntity<Product> createProduct (@RequestBody @Valid Product product){


            Product newProduct = productService.createProduct(product);

            return new ResponseEntity(newProduct,HttpStatus.OK);

    }



    @PutMapping("/product/")
    @ResponseStatus(HttpStatus.OK)
    @JsonView({RestViews.ProductView.class})
    public Product updateProduct(@RequestBody Product attributesForChange){

        return productService.updateProduct(attributesForChange);
    }

}
