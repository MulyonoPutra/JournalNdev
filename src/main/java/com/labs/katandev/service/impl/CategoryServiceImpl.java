package com.labs.katandev.service.impl;

import com.labs.katandev.domain.dto.CategoryDTO;
import com.labs.katandev.domain.entity.Category;
import com.labs.katandev.mapper.CategoryMapper;
import com.labs.katandev.repository.CategoryRepository;
import com.labs.katandev.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Category}.
 */
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }


    @Override
    public CategoryDTO save(CategoryDTO categoryDTO) {
        log.debug("Request to save Category : {}", categoryDTO);
        Category category = categoryMapper.toEntity(categoryDTO);
        category = categoryRepository.save(category);
        return categoryMapper.toDto(category);
    }

    @Override
    public Optional<CategoryDTO> partialUpdate(CategoryDTO categoryDTO) {
        log.debug("Request to partially update Category : {}", categoryDTO);

        return categoryRepository
                .findById(categoryDTO.getId())
                .map(
                        existingCategory -> {
                            categoryMapper.partialUpdate(existingCategory, categoryDTO);
                            return existingCategory;
                        }
                )
                .map(categoryRepository::save)
                .map(categoryMapper::toDto);
    }

    @Override
    public List<Category> findAll() {
        log.debug("Request to get all Posts");
        return categoryRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<CategoryDTO> findOne(Long id) {
        log.debug("Request to get Category : {}", id);
        return categoryRepository.findById(id).map(categoryMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Category : {}", id);
        categoryRepository.deleteById(id);
    }
}
