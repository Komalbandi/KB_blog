package com.komalbandi.kb_blog.controllers;

import com.komalbandi.kb_blog.entities.BlogCategories;
import com.komalbandi.kb_blog.repositories.BlogCategoriesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/blog_category")
public class BlogCategoryController {
    BlogCategoriesRepository blogCategoriesRepository;

    public BlogCategoryController(BlogCategoriesRepository blogCategoriesRepository) {
        this.blogCategoriesRepository = blogCategoriesRepository;
    }

    @GetMapping()
    public Iterable<BlogCategories> getBlogCategories() {
        return this.blogCategoriesRepository.findAll();
    }

    @PostMapping()
    public ResponseEntity<BlogCategories> createBlogCategory(@RequestBody BlogCategories blogCategories) {
        BlogCategories createdBlogCategory = this.blogCategoriesRepository.save(blogCategories);
        return new ResponseEntity<BlogCategories>(createdBlogCategory, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<BlogCategories> updateBlogCategory(@RequestBody BlogCategories blogCategories) {
        Optional<BlogCategories> findBlogCategory = this.blogCategoriesRepository.findById(blogCategories.getId());
        if (findBlogCategory.isPresent()) {
            BlogCategories foundBlogCategory = findBlogCategory.get();

            if (blogCategories.getSlug() != null) {
                foundBlogCategory.setSlug(blogCategories.getSlug());
            }

            if (blogCategories.getName() != null) {
                foundBlogCategory.setName(blogCategories.getName());
            }

            if (blogCategories.getDescription() != null) {
                foundBlogCategory.setDescription(blogCategories.getDescription());
            }

            this.blogCategoriesRepository.save(foundBlogCategory);
            return new ResponseEntity<BlogCategories>(foundBlogCategory, HttpStatus.ACCEPTED);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BlogCategories> deleteBlogCategory(@PathVariable Long id) {
        Optional<BlogCategories> findBlogCategory = this.blogCategoriesRepository.findById(id);
        if (findBlogCategory.isPresent()) {
            this.blogCategoriesRepository.delete(findBlogCategory.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    }
}
