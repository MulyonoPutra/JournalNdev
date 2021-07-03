package com.labs.katandev.controllers;

import static com.labs.katandev.constants.ResponseConstants.DELETED_SUCCESSFULLY;

import com.labs.katandev.domain.dto.CardDTO;
import com.labs.katandev.domain.dto.ResponseMessages;
import com.labs.katandev.domain.entity.Card;
import com.labs.katandev.exception.BadException;
import com.labs.katandev.service.CardService;
import com.labs.katandev.utils.ResponseUtils;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/card")
public class CardController {

    private final Logger log = LoggerFactory.getLogger(CardController.class);

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    /**
     * {@code Card /card} : Create a new card.
     *
     * @param cardDTO the cardDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
     *         body the new cardDTO, or with status {@code 400 (Bad Request)} if the
     *         post has already an ID.
     * @throws BadException if the Location URI syntax is incorrect.
     */
    @PostMapping
    public ResponseEntity<CardDTO> createCard(
        @Valid @RequestBody CardDTO cardDTO
    )
        throws BadException {
        log.debug("REST request to save Card : {}", cardDTO);
        if (cardDTO.getId() != null) {
        throw new BadException("Error!");
        }
        CardDTO result = cardService.save(cardDTO);
        return ResponseEntity.ok(result);
    }

    /**
     * {@code GET /card} : get all the cards.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of cards in body.
     */
    @GetMapping
    public ResponseEntity<List<Card>> findAllCard() {
        List<Card> cards = cardService.findAll();
        return new ResponseEntity<>(cards, HttpStatus.OK);
    }

    /**
     * {@code GET /post/:id} : get the "id" post.
     *
     * @param id the id of the postDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the postDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CardDTO> findById(@PathVariable Long id) {
        log.debug("REST request to get Card : {}", id);
        Optional<CardDTO> post = cardService.findOne(id);
        return new ResponseEntity(post, HttpStatus.OK);
    }

    /**
     * {@code DELETE  /card/:id} : delete the "id" post.
     * @param id the id of the postDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessages> deleteCard(@PathVariable Long id) {
        log.debug("REST request to delete Cards : {}", id);
        cardService.delete(id);
        return ResponseUtils.response(HttpStatus.OK, DELETED_SUCCESSFULLY);
    }
}
