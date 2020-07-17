package com.utrechtfour.supermarket.repository;

import com.utrechtfour.supermarket.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category,Long> {
}
