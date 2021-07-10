package com.labs.katandev.service;

import com.labs.katandev.domain.dto.PostDTO;
import com.labs.katandev.domain.entity.Post;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.labs.katandev.domain.entity.Post}.
 */
public interface PostService {

    /**
     * Save a post.
     *
     * @param postDTO the entity to save.
     * @return the persisted entity.
     */
    PostDTO save(PostDTO postDTO);


    /**
     * Partially updates a post.
     *
     * @param postDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<PostDTO> partialUpdate(PostDTO postDTO);


    /**
     * Get all the posts.
     *
     * @return the list of entities.
     */
    List<Post> findAll();


    /**
     * Get the "id" post.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PostDTO> findOne(Long id);


    /**
     * Delete the "id" post.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    List<Post> findPostByCategoryId(Long categoryId);

    List<Post> findByAuthorName(String author) ;
}
