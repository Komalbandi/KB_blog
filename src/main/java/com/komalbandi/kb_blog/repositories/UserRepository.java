package com.komalbandi.kb_blog.repositories;

import org.springframework.data.repository.CrudRepository;
import com.komalbandi.kb_blog.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {
}
