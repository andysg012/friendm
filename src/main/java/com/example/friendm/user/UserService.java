package com.example.friendm.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveIfEmailAddressNotExists(String emailAddress) {

        User retrievedUser = findByEmailAddress(emailAddress);

        if (retrievedUser == null) {
            retrievedUser = userRepository.save(new User(emailAddress));
        }

        return retrievedUser;
    }

    public User findByEmailAddress(String emailAddress) {
        return userRepository.findByEmailAddress(emailAddress);
    }
}
