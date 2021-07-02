package com.labs.katandev.controllers;

import com.labs.katandev.domain.dto.PostDTO;
import com.labs.katandev.domain.entity.Post;
import com.labs.katandev.exception.BadException;
import com.labs.katandev.mapper.PostMapper;
import com.labs.katandev.repository.PostRepository;
import com.labs.katandev.service.PostService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


/**
 * REST controller for managing {@link com.labs.katandev.domain.entity.Post}.
 */
@RestController
@RequestMapping("/api/post")
public class PostController {

    private final Logger log = LoggerFactory.getLogger(PostController.class);

    private final PostService postService;

    private final PostRepository postRepository;

    private final ModelMapper modelMapper;

    private final PostMapper postMapper;

    public PostController(PostService postService, PostRepository postRepository, ModelMapper modelMapper, PostMapper postMapper) {
        this.postService = postService;
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
        this.postMapper = postMapper;
    }

    /**
     * {@code POST  /posts} : Create a new post.
     *
     * @param postDTO the postDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new postDTO, or with status {@code 400 (Bad Request)} if the post has already an ID.
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
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of posts in body.
     */
    @GetMapping
    public ResponseEntity<List<Post>> findAllPost() {
        List<Post> post = postService.findAll();
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

}
