package com.labs.katandev.service;

import com.labs.katandev.domain.dto.CardDTO;
import com.labs.katandev.domain.entity.Card;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.labs.katandev.domain.entity.Card}.
 */
public interface CardService {
    
    /**
     * Save a post.
     *
     * @param cardDTO the entity to save.
     * @return the persisted entity.
     */
    CardDTO save(CardDTO cardDTO);

    /**
     * Partially updates a post.
     *
     * @param cardDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<CardDTO> partialUpdate(CardDTO cardDTO);

    /**
     * Get all the Card.
     *
     * @return the list of entities.
     */
    List<Card> findAll();

    /**
     * Get the "id" Card.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CardDTO> findOne(Long id);

    /**
     * Delete the "id" Card.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

}
