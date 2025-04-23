package com.komalbandi.kb_blog.repositories;

import org.springframework.data.repository.CrudRepository;
import com.komalbandi.kb_blog.entities.BlogCategories;

import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BlogCategoriesRepository extends CrudRepository<BlogCategories, Long> {
    public List<BlogCategories> findAllByNameContaining(String name, Pageable pageable);

    public List<BlogCategories> findAllBySlugContaining(String slug, Pageable pageable);

    public List<BlogCategories> findAllByCreatedAt(Date createdAt, Pageable pageable);
}
