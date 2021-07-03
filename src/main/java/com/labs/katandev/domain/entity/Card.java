package com.labs.katandev.domain.entity;

import java.io.Serializable;
import javax.persistence.*;
import lombok.*;

/**
 * A Card.
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "card")
public class Card extends AbstractAuditingEntity<String> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    @Column(name = "images", nullable = false)
    private byte[] images;

    @Column(name = "images_content_type", nullable = false)
    private String imagesContentType;
    
}
