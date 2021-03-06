package com.labs.katandev.service;

import com.labs.katandev.domain.dto.CategoryDTO;
import com.labs.katandev.domain.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    /**
     * Save a category.
     * @param categoryDTO the entity to save.
     * @return the persisted entity.
     */
    CategoryDTO save(CategoryDTO categoryDTO);

    /**
     * Partially updates a category.
     * @param categoryDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<CategoryDTO> partialUpdate(CategoryDTO categoryDTO);

    /**
     * Get all the categories.
     * @return the list of entities.
     */
    List<Category> findAll();


    /**
     * Get the "id" category.
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CategoryDTO> findOne(Long id);


    /**
     * Delete the "id" category.
     * @param id the id of the entity.
     */
    void delete(Long id);
}
