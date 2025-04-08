package com.komalbandi.kb_blog.controllers;

import com.komalbandi.kb_blog.entities.BlogPosts;
import com.komalbandi.kb_blog.entities.BlogPostsCategories;
import com.komalbandi.kb_blog.entities.BlogPostsRequestBody;
import com.komalbandi.kb_blog.entities.User;
import com.komalbandi.kb_blog.repositories.BlogPostsCategoriesRepository;
import com.komalbandi.kb_blog.repositories.BlogPostsRepository;
import com.komalbandi.kb_blog.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/blog_posts")
public class BlogPostsController {
    BlogPostsRepository blogPostsRepository;
    BlogPostsCategoriesRepository blogPostsCategoriesRepository;

    public BlogPostsController(BlogPostsRepository blogPostsRepository, BlogPostsCategoriesRepository blogPostsCategoriesRepository) {
        this.blogPostsRepository = blogPostsRepository;
        this.blogPostsCategoriesRepository = blogPostsCategoriesRepository;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public Iterable<BlogPosts> getBlogPosts() {
        return blogPostsRepository.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping()
    public BlogPosts createBlogPosts(@RequestBody BlogPostsRequestBody blogPosts) {
        BlogPosts blogs = blogPostsRepository.save(blogPosts.blogPosts);
        blogPosts.category_ids.forEach(category_id -> {
            BlogPostsCategories blogBridge = new BlogPostsCategories(blogs.getId(), category_id);
            this.blogPostsCategoriesRepository.save(blogBridge);
        });
        return blogs;
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping()
    public Optional<BlogPosts> updateBlogPosts(@RequestBody BlogPostsRequestBody blogPosts) {
        Optional<BlogPosts> updatedBlogPosts = blogPostsRepository.findById(blogPosts.blogPosts.getId());
        if (updatedBlogPosts.isPresent()) {
            BlogPosts foundBlogPosts = updatedBlogPosts.get();

            if (blogPosts.blogPosts.getUser_id() != null) {
                foundBlogPosts.setUser_id(blogPosts.blogPosts.getUser_id());
            }

            if (blogPosts.blogPosts.getTitle() != null) {
                foundBlogPosts.setTitle(blogPosts.blogPosts.getTitle());
            }

            if (blogPosts.blogPosts.getSlug() != null) {
                foundBlogPosts.setSlug(blogPosts.blogPosts.getSlug());
            }

            if (blogPosts.blogPosts.getExcerpt() != null) {
                foundBlogPosts.setExcerpt(blogPosts.blogPosts.getExcerpt());
            }

            if (blogPosts.blogPosts.getContent_html() != null) {
                foundBlogPosts.setContent_html(blogPosts.blogPosts.getContent_html());
            }

            BlogPosts saveBlog = this.blogPostsRepository.save(foundBlogPosts);

            this.updateBlogPostsCategories(blogPosts, saveBlog.getId());

            return Optional.of(foundBlogPosts);
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "BlogPosts not found");
    }

    private void updateBlogPostsCategories(BlogPostsRequestBody blogPosts, Long post_id) {
        this.blogPostsCategoriesRepository.findByPostId(post_id).forEach(blogPostsCategories -> {
            this.blogPostsCategoriesRepository.deleteById(blogPostsCategories.getPost_id());
        });

        blogPosts.category_ids.forEach(category_id -> {
            BlogPostsCategories blogBridge = new BlogPostsCategories(post_id, category_id);
            this.blogPostsCategoriesRepository.save(blogBridge);
        });
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public String deleteBlogPosts(@PathVariable Long id) {
        Optional<BlogPosts> blogPosts = this.blogPostsRepository.findById(id);
        blogPosts.ifPresent(blogPost -> this.blogPostsRepository.delete(blogPost));

        if (blogPosts.isPresent()) {
            return "OK";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "BlogPosts not found");
        }
    }
}
