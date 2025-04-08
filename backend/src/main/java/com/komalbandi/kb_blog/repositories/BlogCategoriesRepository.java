package com.komalbandi.kb_blog.repositories;

import org.springframework.data.repository.CrudRepository;
import com.komalbandi.kb_blog.entities.BlogCategories;

public interface BlogCategoriesRepository extends CrudRepository<BlogCategories, Long> {
}
