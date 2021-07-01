package com.labs.katandev.service.impl;

import com.labs.katandev.domain.dto.PostDTO;
import com.labs.katandev.domain.entity.Post;
import com.labs.katandev.mapper.PostMapper;
import com.labs.katandev.repository.PostRepository;
import com.labs.katandev.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Post}.
 */
@Service
@Transactional
public class PostServiceImpl implements PostService {

    private final Logger log = LoggerFactory.getLogger(PostServiceImpl.class);


    private final PostRepository postRepository;

    private final PostMapper postMapper;

    /**
     * A Constructor
     */
    public PostServiceImpl(PostRepository postRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    @Override
    public PostDTO save(PostDTO postDTO) {
        log.debug("Request to save Post : {}", postDTO);
        Post post = postMapper.toEntity(postDTO);
        post = postRepository.save(post);
        return postMapper.toDto(post);
    }

    @Override
    public Optional<PostDTO> partialUpdate(PostDTO postDTO) {
        log.debug("Request to partially update Post : {}", postDTO);

        return postRepository.findById(postDTO.getId()).map(existingPost -> {
            postMapper.partialUpdate(existingPost, postDTO);
            return existingPost;
        })
                .map(postRepository::save)
                .map(postMapper::toDto);
    }

    @Override
    public List<Post> findAll() {
        log.debug("Request to get all Posts");
        return postRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PostDTO> findOne(Long id) {
        log.debug("Request to get Post : {}", id);
        return postRepository.findById(id).map(postMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Post : {}", id);
        postRepository.deleteById(id);
    }
}
