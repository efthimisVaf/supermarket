package com.utrechtfour.supermarket.service;

import com.utrechtfour.supermarket.model.Supplier;
import com.utrechtfour.supermarket.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class SupplierSevice {

    @Autowired
    SupplierRepository repository;

    @Autowired
    EntityManager entityManager;

    @Transactional
    public Optional<Supplier> getSupplierById (Long id) {
        return repository.findById(id);
    }


    @Transactional
    public Supplier createSupplier(Supplier supplier) {

        return repository.save(supplier);
    }





}
