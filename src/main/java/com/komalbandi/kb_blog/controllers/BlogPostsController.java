package com.komalbandi.kb_blog.controllers;

import com.komalbandi.kb_blog.entities.BlogPosts;
import com.komalbandi.kb_blog.entities.BlogPostsRequestBody;
import com.komalbandi.kb_blog.entities.User;
import com.komalbandi.kb_blog.repositories.BlogPostsRepository;
import com.komalbandi.kb_blog.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/blog_posts")
public class BlogPostsController {
    BlogPostsRepository blogPostsRepository;
    UserRepository userRepository;

    public BlogPostsController(BlogPostsRepository blogPostsRepository, UserRepository userRepository) {
        this.blogPostsRepository = blogPostsRepository;
        this.userRepository = userRepository;
    }

    @GetMapping()
    public Iterable<BlogPosts> getBlogPosts() {
        return blogPostsRepository.findAll();
    }

    @PostMapping()
    public BlogPosts createBlogPosts(@RequestBody BlogPosts blogPosts) {
        return blogPostsRepository.save(blogPosts);
    }

    @PutMapping()
    public Optional<BlogPosts> updateBlogPosts(@RequestBody BlogPosts blogPosts) {
        Optional<BlogPosts> updatedBlogPosts = blogPostsRepository.findById(blogPosts.getId());
        if (updatedBlogPosts.isPresent()) {
            BlogPosts foundBlogPosts = updatedBlogPosts.get();

            if (blogPosts.getUser_id() != null) {
                foundBlogPosts.setUser_id(blogPosts.getUser_id());
            }

            if (blogPosts.getTitle() != null) {
                foundBlogPosts.setTitle(blogPosts.getTitle());
            }

            if (blogPosts.getSlug() != null) {
                foundBlogPosts.setSlug(blogPosts.getSlug());
            }

            if (blogPosts.getExcerpt() != null) {
                foundBlogPosts.setExcerpt(blogPosts.getExcerpt());
            }

            if (blogPosts.getContent_html() != null) {
                foundBlogPosts.setContent_html(blogPosts.getContent_html());
            }

            this.blogPostsRepository.save(foundBlogPosts);
            return Optional.of(foundBlogPosts);
        }

        return Optional.empty();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBlogPosts(@PathVariable Long id) {
        Optional<BlogPosts> blogPosts = blogPostsRepository.findById(id);
        if (blogPosts.isPresent()) {
            blogPostsRepository.delete(blogPosts.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
