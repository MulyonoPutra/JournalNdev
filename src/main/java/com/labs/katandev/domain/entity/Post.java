package com.labs.katandev.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter @Setter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "post")
public class Post extends AbstractAuditingEntity<String> implements Serializable {

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

    @NotNull
    @Column(name = "author", nullable = false)
    private String author;

    @Lob
    @Column(name = "images", nullable = false)
    private byte[] images;

    @Column(name = "images_content_type", nullable = false)
    private String imagesContentType;

    @ManyToOne
    private Category category_post;


}
