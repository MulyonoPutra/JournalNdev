package com.labs.katandev.mapper;

import com.labs.katandev.domain.dto.CardDTO;
import com.labs.katandev.domain.dto.FeedbackDTO;
import com.labs.katandev.domain.entity.Card;
import com.labs.katandev.domain.entity.Feedback;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {})
public interface FeedbackMapper extends EntityMapper<FeedbackDTO, Feedback> {

    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    Feedback toDtoId(Feedback feedback);

}
