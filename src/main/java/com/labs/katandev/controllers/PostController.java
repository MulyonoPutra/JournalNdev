package com.labs.katandev.controllers;

import com.labs.katandev.domain.dto.PostDTO;
import com.labs.katandev.domain.dto.ResponseMessages;
import com.labs.katandev.domain.entity.Post;
import com.labs.katandev.exception.BadException;
import com.labs.katandev.service.PostService;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import com.labs.katandev.utils.ResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static com.labs.katandev.constants.ResponseConstants.DELETED_SUCCESSFULLY;

/**
 * REST controller for managing {@link com.labs.katandev.domain.entity.Post}.
 */
@RestController
@RequestMapping("/api/post")
public class PostController {

    private final Logger log = LoggerFactory.getLogger(PostController.class);

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    /**
     * {@code POST  /posts} : Create a new post.
     *
     * @param postDTO the postDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
     *         body the new postDTO, or with status {@code 400 (Bad Request)} if the
     *         post has already an ID.
     * @throws BadException if the Location URI syntax is incorrect.
     */
    @PostMapping
    public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO postDTO) throws BadException {
      log.debug("REST request to save Post : {}", postDTO);
      if (postDTO.getId() != null) {
        throw new BadException("Error!");
      }
      PostDTO result = postService.save(postDTO);
      return ResponseEntity.ok(result);
    }

    /**
     * {@code GET  /posts} : get all the posts.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of posts in body.
     */
    @GetMapping
    public ResponseEntity<List<Post>> findAllPost() {
      List<Post> post = postService.findAll();
      return new ResponseEntity<>(post, HttpStatus.OK);
    }

  /**
   * {@code GET  /post/:id} : get the "id" post.
   *
   * @param id the id of the postDTO to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the postDTO, or with status {@code 404 (Not Found)}.
   */
    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> findById(@PathVariable Long id) {
      log.debug("REST request to get Post : {}", id);
      Optional<PostDTO> post = postService.findOne(id);
      return new ResponseEntity(post, HttpStatus.OK);
    }


  /**
   * {@code DELETE  /post/:id} : delete the "id" post.
   *
   * @param id the id of the postDTO to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessages> deletePost(@PathVariable Long id) {
      log.debug("REST request to delete Post : {}", id);
      postService.delete(id);
      return ResponseUtils.response(HttpStatus.OK, DELETED_SUCCESSFULLY);
    }

}
