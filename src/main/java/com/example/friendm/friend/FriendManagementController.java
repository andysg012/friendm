package com.example.friendm.friend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FriendManagementController {

    private static final Logger logger = LoggerFactory.getLogger(FriendManagementController.class);

    @PostMapping("/connect")
    public ResponseEntity<String> connect() {
        logger.info("=== connect");
        return ResponseEntity.ok("Ok");
    }

    @GetMapping("/friends")
    public ResponseEntity<String> getFriendsList() {
        logger.info("=== getFriendsList");
        return ResponseEntity.ok("Ok");
    }

    @GetMapping("/common")
    public ResponseEntity<String> getCommonFriendsList() {
        logger.info("=== getCommonFriendsList");
        return ResponseEntity.ok("Ok");
    }

    @PutMapping("/subscribe")
    public ResponseEntity<String> subscribe() {
        logger.info("=== subscribe");
        return ResponseEntity.ok("Ok");
    }

    @PutMapping("/block")
    public ResponseEntity<String> block() {
        logger.info("=== block");
        return ResponseEntity.ok("Ok");
    }

    @GetMapping("/broadcast")
    public ResponseEntity<String> broadcast() {
        logger.info("=== broadcast");
        return ResponseEntity.ok("Ok");
    }
}
