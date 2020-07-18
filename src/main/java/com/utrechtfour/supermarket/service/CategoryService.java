package com.utrechtfour.supermarket.service;

import com.utrechtfour.supermarket.model.Category;
import com.utrechtfour.supermarket.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository repository;

    @Transactional
    public Optional<Category> getCategoryById (Long id){
        return repository.findById(id);

    }

}
