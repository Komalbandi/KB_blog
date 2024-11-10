package com.komalbandi.kb_blog.repositories;

import org.springframework.data.repository.CrudRepository;
import com.komalbandi.kb_blog.entities.BlogPostsCategories;
import com.komalbandi.kb_blog.entities.BlogPosts;

import java.util.List;

public interface BlogPostsCategoriesRepository extends CrudRepository<BlogPostsCategories, Long> {
    List<BlogPostsCategories> findByPostId(Long post_id);
}
