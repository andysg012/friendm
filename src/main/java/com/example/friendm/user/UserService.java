package com.example.friendm.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveIfEmailAddressNotExists(User paramUser) {

        User retrievedUser = userRepository.findByEmailAddress(paramUser.getEmailAddress());

        if (retrievedUser == null) {
            userRepository.save(paramUser);
        }

        return retrievedUser;
    }

    public User findByEmailAddress(String emailAddress) {
        return userRepository.findByEmailAddress(emailAddress);
    }
}
