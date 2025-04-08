package com.komalbandi.kb_blog.repositories;

import org.springframework.data.repository.CrudRepository;
import com.komalbandi.kb_blog.entities.BlogPosts;

public interface BlogPostsRepository extends CrudRepository<BlogPosts, Long> {
}
