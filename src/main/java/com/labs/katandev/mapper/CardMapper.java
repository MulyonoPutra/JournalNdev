package com.labs.katandev.mapper;

import com.labs.katandev.domain.dto.CardDTO;
import com.labs.katandev.domain.entity.Card;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {})
public interface CardMapper extends EntityMapper<CardDTO, Card> {

    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CardDTO toDtoId(Card card);

}
