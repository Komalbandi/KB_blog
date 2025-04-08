package com.komalbandi.kb_blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.komalbandi.kb_blog.repositories.AuthenticateRepository;
import com.komalbandi.kb_blog.repositories.UserRepository;
import com.komalbandi.kb_blog.entities.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.komalbandi.kb_blog.utils.JsonWebTokenUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/authenticate")
public class AuthenticateController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JsonWebTokenUtils jsonWebTokenUtils;

    @PostMapping("/login")
    public String getUSer(@RequestBody AuthenticateRepository authenticate) {

        Optional<User> findUser = this.userRepository.findByEmail(authenticate.email);
        if (findUser.isPresent()) {
            BCryptPasswordEncoder passwordDecoder = new BCryptPasswordEncoder();
            User user = findUser.get();
            if (passwordDecoder.matches(authenticate.password, user.getPassword())) {
                return this.jsonWebTokenUtils.createJWTToken(user.getEmail());
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Password or email is incorrect");
    }

    public boolean tokenIsValid(String token) {
        return this.jsonWebTokenUtils.validateJwtToken(token);
    }
}
