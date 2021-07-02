package com.labs.katandev.repository;

import com.labs.katandev.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Post entity.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
