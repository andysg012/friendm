package com.example.friendm.friend;

import com.example.friendm.user.User;
import com.example.friendm.user.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FriendManagementController {

    private static final Logger logger = LoggerFactory.getLogger(FriendManagementController.class);

    @Autowired
    UserService userService;

    @PostMapping("/connect")
    public ResponseEntity<String> connect() {

        logger.info("=== connect");

        User andy = new User("andy@example.com");
        User john = new User("john@example.com");

        userService.saveIfEmailAddressNotExists(andy);
        userService.saveIfEmailAddressNotExists(john);

        return ResponseEntity.ok("Ok");
    }

    @GetMapping("/friends")
    public ResponseEntity<String> getFriendsList() {

        logger.info("=== getFriendsList");

        User andy = userService.findByEmailAddress("andy@example.com");

        logger.info("=== {}", andy.toString());

        return ResponseEntity.ok("Ok");
    }

    @GetMapping("/common")
    public ResponseEntity<String> getCommonFriendsList() {

        logger.info("=== getCommonFriendsList");

        User andy = userService.findByEmailAddress("andy@example.com");
        User john = userService.findByEmailAddress("john@example.com");

        logger.info("=== {}", andy.toString());
        logger.info("=== {}", john.toString());

        return ResponseEntity.ok("Ok");
    }

    @PutMapping("/subscribe")
    public ResponseEntity<String> subscribe() {

        logger.info("=== subscribe");

        User lisa = userService.findByEmailAddress("lisa@example.com");
        User john = userService.findByEmailAddress("john@example.com");

        logger.info("=== {}", lisa.toString());
        logger.info("=== {}", john.toString());

        return ResponseEntity.ok("Ok");
    }

    @PutMapping("/block")
    public ResponseEntity<String> block() {

        logger.info("=== block");

        User andy = userService.findByEmailAddress("andy@example.com");
        User john = userService.findByEmailAddress("john@example.com");

        logger.info("=== {}", andy.toString());
        logger.info("=== {}", john.toString());

        return ResponseEntity.ok("Ok");
    }

    @GetMapping("/broadcast")
    public ResponseEntity<String> broadcast() {

        logger.info("=== broadcast");

        User john = userService.findByEmailAddress("john@example.com");

        logger.info("=== {}", john.toString());

        return ResponseEntity.ok("Ok");
    }
}
