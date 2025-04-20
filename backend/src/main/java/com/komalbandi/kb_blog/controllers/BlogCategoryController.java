package com.komalbandi.kb_blog.controllers;

import com.komalbandi.kb_blog.entities.BlogCategories;
import com.komalbandi.kb_blog.repositories.BlogCategoriesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/blog_category")
public class BlogCategoryController {
    BlogCategoriesRepository blogCategoriesRepository;

    public BlogCategoryController(BlogCategoriesRepository blogCategoriesRepository) {
        this.blogCategoriesRepository = blogCategoriesRepository;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public Iterable<BlogCategories> getBlogCategories() {
        return this.blogCategoriesRepository.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public BlogCategories createBlogCategory(@RequestBody BlogCategories blogCategories) {
        BlogCategories createdBlogCategory = this.blogCategoriesRepository.save(blogCategories);
        return createdBlogCategory;
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping()
    public BlogCategories updateBlogCategory(@RequestBody BlogCategories blogCategories) {
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

            foundBlogCategory.setUpdated_at();

            this.blogCategoriesRepository.save(foundBlogCategory);
            return foundBlogCategory;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "BlogCategory not found");
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public String deleteBlogCategory(@PathVariable Long id) {
        Optional<BlogCategories> findBlogCategory = this.blogCategoriesRepository.findById(id);
        findBlogCategory.ifPresentOrElse(
                blogCategories -> this.blogCategoriesRepository.deleteById(id),
                () -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "BlogCategory not found");
                }
        );
        return "BlogCategory deleted successfully";
    }

    @PostMapping("/search/{searchName}")
    public Iterable<BlogCategories> searchBlogCategory(@PathVariable String searchName) {
        List<BlogCategories> blogCategories = new ArrayList<BlogCategories>();
        this.blogCategoriesRepository.findAll().forEach(blogCategories::add);
        return blogCategories.stream().filter(bc->bc.getName().toLowerCase().contains(searchName.toLowerCase())).collect(Collectors.toList());
    }
}
