package com.labs.katandev.domain.dto;

import lombok.*;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter @Setter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO implements Serializable {

    private Long id;

    @NotNull
    private String title;

    @Lob
    private String content;

    @Lob
    private byte[] images;

    private String imagesContentType;

    private CategoryDTO category_post;


}
