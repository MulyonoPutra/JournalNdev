package com.labs.katandev.controllers;

import com.labs.katandev.domain.dto.CategoryDTO;
import com.labs.katandev.domain.entity.Category;
import com.labs.katandev.exception.BadException;
import com.labs.katandev.repository.CategoryRepository;
import com.labs.katandev.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final Logger log = LoggerFactory.getLogger(PostController.class);

    private final CategoryService categoryService;

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryService categoryService, CategoryRepository categoryRepository) {
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> createPost(@Valid @RequestBody CategoryDTO categoryDTO) throws BadException {
        log.debug("REST request to save Post : {}", categoryDTO);
        if (categoryDTO.getId() != null) {
            throw new BadException("Error!");
        }
        CategoryDTO result = categoryService.save(categoryDTO);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<List<Category>> findAllCategory() {
        List<Category> categories = categoryService.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> findById(@PathVariable Long id) {
        log.debug("REST request to get Category : {}", id);
        Optional<CategoryDTO> categoryDTO = categoryService.findOne(id);
        return new ResponseEntity(categoryDTO, HttpStatus.OK);
    }
}
