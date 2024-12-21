package com.komalbandi.kb_blog.repositories;

import org.springframework.data.repository.CrudRepository;
import com.komalbandi.kb_blog.entities.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
