package com.labs.katandev.domain.dto;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class PostDTO implements Serializable {

    private Long id;

    @NotNull
    private String title;

    @Lob
    private String content;

    @Lob
    private byte[] images;

    private String imagesContentType;

    /**
     * A Default Constructor
     */
    public PostDTO() {
    }

    /**
     * A Constructor with Parameter
     */
    public PostDTO(@NotNull String title, String content, byte[] images, String imagesContentType) {
        this.title = title;
        this.content = content;
        this.images = images;
        this.imagesContentType = imagesContentType;
    }

    /**
     * Getter Setter
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public byte[] getImages() {
        return images;
    }

    public void setImages(byte[] images) {
        this.images = images;
    }

    public String getImagesContentType() {
        return imagesContentType;
    }

    public void setImagesContentType(String imagesContentType) {
        this.imagesContentType = imagesContentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PostDTO)) return false;
        PostDTO postDTO = (PostDTO) o;
        return Objects.equals(id, postDTO.id) &&
                Objects.equals(title, postDTO.title) &&
                Objects.equals(content, postDTO.content) &&
                Arrays.equals(images, postDTO.images) &&
                Objects.equals(imagesContentType, postDTO.imagesContentType);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, title, content, imagesContentType);
        result = 31 * result + Arrays.hashCode(images);
        return result;
    }

    @Override
    public String toString() {
        return "PostDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", images=" + Arrays.toString(images) +
                ", imagesContentType='" + imagesContentType + '\'' +
                '}';
    }


}
