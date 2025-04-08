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

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public Iterable<BlogPostsCategories> getBlogPostsCategories() {
        return this.blogPostsCategoriesRepository.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public BlogPostsCategories createBlogPostsCategory(@RequestBody BlogPostsCategories blogPostsCategories) {
        return this.blogPostsCategoriesRepository.save(blogPostsCategories);
    }

    @ResponseStatus(HttpStatus.OK)
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

            blogPostsCategories.setUpdated_at();

            this.blogPostsCategoriesRepository.save(foundBlogPostsCategory);
            return findBlogPostsCategory;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "BlogPostsCategory not found");
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public Optional<BlogPostsCategories> deleteBlogPostsCategory(@PathVariable Long id) {
        Optional<BlogPostsCategories> findBlogPostsCategory = this.blogPostsCategoriesRepository.findById(id);
        findBlogPostsCategory.ifPresentOrElse(
                blogPostsCategories -> this.blogPostsCategoriesRepository.deleteById(id),
                () -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "BlogPostsCategory not found");
                }
        );
        return findBlogPostsCategory;
    }
}
