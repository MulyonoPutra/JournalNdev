package com.labs.katandev.service;

import com.labs.katandev.domain.dto.FeedbackDTO;
import com.labs.katandev.domain.dto.PostDTO;
import com.labs.katandev.domain.entity.Feedback;
import com.labs.katandev.domain.entity.Post;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.labs.katandev.domain.entity.Feedback}.
 */
public interface FeedbackService {
    /**
     * Save a feedback.
     *
     * @param feedbackDTO the entity to save.
     * @return the persisted entity.
     */
    FeedbackDTO save(FeedbackDTO feedbackDTO);

    /**
     * Get all the feedback.
     *
     * @return the list of entities.
     */
    List<Feedback> findAll();

    /**
     * Get the "id" feedback.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FeedbackDTO> findOne(Long id);




}
