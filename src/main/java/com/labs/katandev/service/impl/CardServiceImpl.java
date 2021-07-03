package com.labs.katandev.service.impl;

import com.labs.katandev.domain.dto.CardDTO;
import com.labs.katandev.domain.entity.Card;
import com.labs.katandev.mapper.CardMapper;
import com.labs.katandev.repository.CardRepository;
import com.labs.katandev.service.CardService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CardServiceImpl implements CardService {

    private final Logger log = LoggerFactory.getLogger(CardServiceImpl.class);

    private final CardRepository cardRepository;

    private final CardMapper cardMapper;

    public CardServiceImpl(CardRepository cardRepository, CardMapper cardMapper) {
        this.cardRepository = cardRepository;
        this.cardMapper = cardMapper;
    }

    @Override
    public CardDTO save(CardDTO cardDTO) {
        log.debug("Request to save Post : {}", cardDTO);
        Card card = cardMapper.toEntity(cardDTO);
        card = cardRepository.save(card);
        return cardMapper.toDto(card);
    }

    @Override
    public Optional<CardDTO> partialUpdate(CardDTO cardDTO) {
        log.debug("Request to partially update Post : {}", cardDTO);

        return cardRepository
        .findById(cardDTO.getId())
        .map(
            existingCard -> {
            cardMapper.partialUpdate(existingCard, cardDTO);
            return existingCard;
            }
        )
        .map(cardRepository::save)
        .map(cardMapper::toDto);
    }

    @Override
    public List<Card> findAll() {
        log.debug("Request to get all Card");
        return cardRepository.findAll();
    }

    @Override
    public Optional<CardDTO> findOne(Long id) {
        log.debug("Request to get Card : {}", id);
        return cardRepository.findById(id).map(cardMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Card : {}", id);
        cardRepository.deleteById(id);
    }
}
