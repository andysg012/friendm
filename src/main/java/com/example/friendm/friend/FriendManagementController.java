package com.example.friendm.friend;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import com.example.friendm.user.User;
import com.example.friendm.user.UserService;
import com.example.friendm.view.View;
import com.example.friendm.view.ViewResponseEntity;
import com.fasterxml.jackson.annotation.JsonView;

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

    @JsonView(View.SuccessView.class)
    @PostMapping("/connect")
    public ResponseEntity<ViewResponseEntity> connect() {

        logger.info("=== connect");

        User andy = new User("andy@example.com");
        User john = new User("john@example.com");

        userService.saveIfEmailAddressNotExists(andy);
        userService.saveIfEmailAddressNotExists(john);

        return ResponseEntity.ok(ViewResponseEntity.SUCCESS);
    }

    @JsonView(View.FriendView.class)
    @GetMapping("/friends")
    public ResponseEntity<ViewResponseEntity> getFriendsList() {

        logger.info("=== getFriendsList");

        User andy = userService.findByEmailAddress("andy@example.com");

        logger.info("=== {}", andy.toString());

        return ResponseEntity.ok(ViewResponseEntity.friends(Arrays.asList(andy.getEmailAddress())));
    }

    @JsonView(View.FriendView.class)
    @GetMapping("/common")
    public ResponseEntity<ViewResponseEntity> getCommonFriendsList() {

        logger.info("=== getCommonFriendsList");

        User andy = userService.findByEmailAddress("andy@example.com");
        User john = userService.findByEmailAddress("john@example.com");

        logger.info("=== {}", andy.toString());
        logger.info("=== {}", john.toString());

        return ResponseEntity.ok(ViewResponseEntity.friends(Arrays.asList(andy.getEmailAddress(), john.getEmailAddress())));
    }

    @JsonView(View.SuccessView.class)
    @PutMapping("/subscribe")
    public ResponseEntity<ViewResponseEntity> subscribe() {

        logger.info("=== subscribe");

        User lisa = userService.findByEmailAddress("lisa@example.com");
        User john = userService.findByEmailAddress("john@example.com");

        logger.info("=== {}", lisa.toString());
        logger.info("=== {}", john.toString());

        return ResponseEntity.ok(ViewResponseEntity.SUCCESS);
    }

    @JsonView(View.SuccessView.class)
    @PutMapping("/block")
    public ResponseEntity<ViewResponseEntity> block() {

        logger.info("=== block");

        User andy = userService.findByEmailAddress("andy@example.com");
        User john = userService.findByEmailAddress("john@example.com");

        logger.info("=== {}", andy.toString());
        logger.info("=== {}", john.toString());

        return ResponseEntity.ok(ViewResponseEntity.SUCCESS);
    }

    @JsonView(View.RecipientView.class)
    @GetMapping("/broadcast")
    public ResponseEntity<ViewResponseEntity> broadcast() {

        logger.info("=== broadcast");

        User john = userService.findByEmailAddress("john@example.com");

        logger.info("=== {}", john.toString());

        return ResponseEntity.ok(ViewResponseEntity.recipients(Arrays.asList(john.getEmailAddress())));
    }
}
