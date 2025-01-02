package com.komalbandi.kb_blog.controllers;

import com.komalbandi.kb_blog.entities.User;
import com.komalbandi.kb_blog.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping()
    public ResponseEntity<Iterable<User>> getUsers() {
        return new ResponseEntity<Iterable<User>>(this.userRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<User> addUser(@RequestBody User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreate_at();
        user.setUpdated_at();
        return new ResponseEntity<User>(this.userRepository.save(user), HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<Optional<User>> updateUser(@RequestBody User userBody) {
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

            foundUser.setUpdated_at();

            this.userRepository.save(foundUser);
            return new ResponseEntity<Optional<User>>(this.userRepository.findById(userBody.getId()), HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    }

    @GetMapping("/test")
    public String test() {
        return "test successful";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @DeleteMapping("/{id}")
    public Optional<String> deleteUser(@PathVariable Long id) {
        Optional<User> findUser = this.userRepository.findById(id);
        findUser.ifPresent(user -> this.userRepository.delete(user));
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    }

}
