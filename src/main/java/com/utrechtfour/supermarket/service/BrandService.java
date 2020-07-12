package com.utrechtfour.supermarket.service;

import com.utrechtfour.supermarket.model.Brand;
import com.utrechtfour.supermarket.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class BrandService {

    @Autowired
    BrandRepository repository;

    @Transactional
    public Optional<Brand> getBrandById (Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Brand createOrUpdateBrand(Brand brand) {

        return repository.save(brand);
    }

    @Transactional
    public void removeBrandById(Long id){
        repository.deleteById(id);
    }

}
