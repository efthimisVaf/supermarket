package com.utrechtfour.supermarket.repository;

import com.utrechtfour.supermarket.model.Brand;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends CrudRepository<Brand,Long> {

}
