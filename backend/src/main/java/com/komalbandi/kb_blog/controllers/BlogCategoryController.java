package com.komalbandi.kb_blog.controllers;

import com.komalbandi.kb_blog.constants.BlogCategorySort;
import com.komalbandi.kb_blog.entities.BlogCategories;
import com.komalbandi.kb_blog.models.BlogCategoryRequest;
import com.komalbandi.kb_blog.repositories.BlogCategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/blog_category")
public class BlogCategoryController {
    @Autowired
    BlogCategoriesRepository blogCategoriesRepository;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public List<BlogCategories> getBlogCategories(@RequestBody BlogCategoryRequest blogCategoryRequest) {
        try {
            return this.searchBlogCategories(blogCategoryRequest);
        } catch (ParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid date format");
        }
    }

    private List<BlogCategories> searchBlogCategories(BlogCategoryRequest blogCategoryRequest) throws ParseException {
        int offset = blogCategoryRequest.getPage() * blogCategoryRequest.getRowCount();
        Pageable pageable = PageRequest.of(offset, blogCategoryRequest.getRowCount());
        switch (blogCategoryRequest.getSearchBy()) {
            case BlogCategorySort.NAME:
                return this.blogCategoriesRepository.findAllByNameContaining(blogCategoryRequest.getSearch(), pageable);
            case BlogCategorySort.SLUG:
                return this.blogCategoriesRepository.findAllBySlugContaining(blogCategoryRequest.getSearch(), pageable);
            case BlogCategorySort.CREATED_AT:
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
                String dateInString = blogCategoryRequest.getSearch();
                Date createdAt = formatter.parse(dateInString);
                return this.blogCategoriesRepository.findAllByCreatedAt(createdAt, pageable);
            default:
                List<BlogCategories> result = new ArrayList<>();
                this.blogCategoriesRepository.findAll().forEach(result::add);
                return result;
        }
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
}
