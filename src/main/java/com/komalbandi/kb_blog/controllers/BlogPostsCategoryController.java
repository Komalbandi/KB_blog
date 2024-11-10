package com.komalbandi.kb_blog.controllers;

import com.komalbandi.kb_blog.entities.BlogPostsCategories;
import com.komalbandi.kb_blog.repositories.BlogPostsCategoriesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/blog_posts_category")
public class BlogPostsCategoryController {

    BlogPostsCategoriesRepository blogPostsCategoriesRepository;

    public BlogPostsCategoryController(BlogPostsCategoriesRepository blogPostsCategoriesRepository) {
        this.blogPostsCategoriesRepository = blogPostsCategoriesRepository;
    }

    @GetMapping()
    public Iterable<BlogPostsCategories> getBlogPostsCategories() {
        return this.blogPostsCategoriesRepository.findAll();
    }

    @PostMapping()
    public BlogPostsCategories createBlogPostsCategory(@RequestBody BlogPostsCategories blogPostsCategories) {
        return this.blogPostsCategoriesRepository.save(blogPostsCategories);
    }

    @PutMapping()
    public Optional<BlogPostsCategories> updateBlogPostsCategory(@RequestBody BlogPostsCategories blogPostsCategories) {
        Optional<BlogPostsCategories> findBlogPostsCategory = this.blogPostsCategoriesRepository.findById(blogPostsCategories.getId());
        if (findBlogPostsCategory.isPresent()) {
            BlogPostsCategories foundBlogPostsCategory = findBlogPostsCategory.get();

            if (blogPostsCategories.getPost_id() != null) {
                foundBlogPostsCategory.setPost_id(blogPostsCategories.getPost_id());
            }

            if (blogPostsCategories.getCategory_id() != null) {
                foundBlogPostsCategory.setCategory_id(blogPostsCategories.getCategory_id());
            }

            this.blogPostsCategoriesRepository.save(foundBlogPostsCategory);
            return findBlogPostsCategory;
        }

        return Optional.empty();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BlogPostsCategories> deleteBlogPostsCategory(@PathVariable Long id) {
        Optional<BlogPostsCategories> findBlogPostsCategory = this.blogPostsCategoriesRepository.findById(id);
        if (findBlogPostsCategory.isPresent()) {
            this.blogPostsCategoriesRepository.deleteById(id);
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
