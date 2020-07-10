package com.utrechtfour.supermarket.repository;

import com.utrechtfour.supermarket.model.Supplier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends CrudRepository<Supplier,Long> {
}
