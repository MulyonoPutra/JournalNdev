package com.labs.katandev.repository;

import com.labs.katandev.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.websocket.server.PathParam;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByTitleContains(String title);

    List<Post> findByAuthorContains(String author);

    @Query("SELECT p FROM Post p WHERE p.category_post.id LIKE :categoryId")
    public List<Post> findProductByCategory(@PathParam("categoryId") Long categoryId);
}

