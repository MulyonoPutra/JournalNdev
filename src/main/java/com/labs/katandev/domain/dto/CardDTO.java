package com.labs.katandev.domain.dto;

import java.io.Serializable;
import javax.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardDTO implements Serializable {

    private Long id;

    private String title;

    @Lob
    private byte[] images;

    private String imagesContentType;

}
