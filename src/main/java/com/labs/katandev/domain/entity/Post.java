package com.labs.katandev.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter @Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "post")
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @Lob
    @Column(name = "content", nullable = false)
    private String content;

    //    @NotNull
    //    @Column(name = "date", nullable = false)
    //    private Instant date;

    @Lob
    @Column(name = "images", nullable = false)
    private byte[] images;

    @Column(name = "images_content_type", nullable = false)
    private String imagesContentType;

    @ManyToOne
    private Category category_post;


}
