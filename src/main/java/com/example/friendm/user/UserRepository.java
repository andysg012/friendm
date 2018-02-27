package com.example.friendm.user;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    public User findByEmailAddress(String emailAddress);
}
