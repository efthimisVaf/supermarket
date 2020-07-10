package com.utrechtfour.supermarket.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.utrechtfour.supermarket.model.Product;
import com.utrechtfour.supermarket.model.Supplier;
import com.utrechtfour.supermarket.service.SupplierSevice;
import com.utrechtfour.supermarket.views.RestViews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.ValidationException;

@RestController
public class SupplierController {


    @Autowired
    SupplierSevice supplierSevice;


    @Transactional
    @PostMapping("/supplier")
    public ResponseEntity<Supplier> createSupplier (@RequestBody @Valid Supplier supplier){

        if (supplier.getId() != null){
            throw new ValidationException("Id is automatically created by the database, please do the request again without providing an id");
        }

        return new ResponseEntity(supplierSevice.createSupplier(supplier), HttpStatus.CREATED);


    }

}
