package com.labs.katandev.mapper;

import com.labs.katandev.domain.dto.PostDTO;
import com.labs.katandev.domain.entity.Post;
import org.mapstruct.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;

/**
 * Mapper for the entity {@link Post} and its DTO {@link PostDTO}.
 */
@Component
@EnableAutoConfiguration
@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface PostMapper extends EntityMapper<PostDTO, Post> {
    @Mapping(target = "category_post", source = "category_post", qualifiedByName = "id")
    PostDTO toDto(Post s);
}

