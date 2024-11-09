package com.komalbandi.kb_blog.controllers;

import com.komalbandi.kb_blog.entities.User;
import com.komalbandi.kb_blog.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping()
    public Iterable<User> getUsers() {
        return this.userRepository.findAll();
    }

    @PostMapping()
    public User addUser(@RequestBody User user) {
        return this.userRepository.save(user);
    }

    @PutMapping("")
    public Optional<User> updateUser(@RequestBody User userBody) {
        Optional<User> findUser = this.userRepository.findById(userBody.getId());
        if (findUser.isPresent()) {
            User foundUser = findUser.get();

            if (userBody.getFirst_name() != null) {
                foundUser.setFirst_name(userBody.getFirst_name());
            }

            if (userBody.getLast_name() != null) {
                foundUser.setLast_name(userBody.getLast_name());
            }

            if (userBody.getEmail() != null) {
                foundUser.setEmail(userBody.getEmail());
            }

            if (userBody.getPassword() != null) {
                foundUser.setPassword(userBody.getPassword());
            }

            this.userRepository.save(foundUser);
            return this.userRepository.findById(userBody.getId());
        }
        return Optional.empty();
    }

    @GetMapping("/test")
    public String test() {
        return "test successful";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @DeleteMapping("/{id}")
    public Optional<String> deleteUser(@PathVariable Long id) {
        Optional<User> findUser = this.userRepository.findById(id);

        if(findUser.isPresent()){
            this.userRepository.delete(findUser.get());
            return Optional.empty();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    }

}
