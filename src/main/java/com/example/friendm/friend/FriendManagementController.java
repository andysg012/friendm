package com.example.friendm.friend;

import java.util.Arrays;

import com.example.friendm.json.JsonRequestEmail;
import com.example.friendm.json.JsonRequestFriends;
import com.example.friendm.json.JsonRequestRequestor;
import com.example.friendm.json.JsonRequestSender;
import com.example.friendm.json.JsonResponse;
import com.example.friendm.json.View;
import com.example.friendm.user.User;
import com.example.friendm.user.UserService;
import com.fasterxml.jackson.annotation.JsonView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FriendManagementController {

    private static final Logger logger = LoggerFactory.getLogger(FriendManagementController.class);

    @Autowired
    UserService userService;

    @JsonView(View.SuccessView.class)
    @PostMapping(path = "/connect", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResponse> connect(@RequestBody JsonRequestFriends request) throws FriendException {

        logger.info("=== connect");

        request.validate();

        User user1 = new User(request.getFriends().get(0));
        User user2 = new User(request.getFriends().get(1));

        userService.saveIfEmailAddressNotExists(user1);
        userService.saveIfEmailAddressNotExists(user2);

        return ResponseEntity.ok(JsonResponse.SUCCESS);
    }

    @JsonView(View.FriendView.class)
    @GetMapping(path = "/friends", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResponse> getFriendsList(@RequestBody JsonRequestEmail request) throws FriendException {

        logger.info("=== getFriendsList");

        request.validate();

        User andy = userService.findByEmailAddress("andy@example.com");

        logger.info("=== {}", andy.toString());

        return ResponseEntity.ok(JsonResponse.friends(Arrays.asList(andy.getEmailAddress())));
    }

    @JsonView(View.FriendView.class)
    @GetMapping(path = "/common", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResponse> getCommonFriendsList(@RequestBody JsonRequestFriends request) throws FriendException {

        logger.info("=== getCommonFriendsList");

        request.validate();

        User andy = userService.findByEmailAddress("andy@example.com");
        User john = userService.findByEmailAddress("john@example.com");

        logger.info("=== {}", andy.toString());
        logger.info("=== {}", john.toString());

        return ResponseEntity.ok(JsonResponse.friends(Arrays.asList(andy.getEmailAddress(), john.getEmailAddress())));
    }

    @JsonView(View.SuccessView.class)
    @PutMapping(path = "/subscribe", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResponse> subscribe(@RequestBody JsonRequestRequestor request) throws FriendException {

        logger.info("=== subscribe");

        request.validate();

        User lisa = userService.findByEmailAddress("lisa@example.com");
        User john = userService.findByEmailAddress("john@example.com");

        logger.info("=== {}", lisa.toString());
        logger.info("=== {}", john.toString());

        return ResponseEntity.ok(JsonResponse.SUCCESS);
    }

    @JsonView(View.SuccessView.class)
    @PutMapping(path = "/block", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResponse> block(@RequestBody JsonRequestRequestor request) throws FriendException {

        logger.info("=== block");

        request.validate();

        User andy = userService.findByEmailAddress("andy@example.com");
        User john = userService.findByEmailAddress("john@example.com");

        logger.info("=== {}", andy.toString());
        logger.info("=== {}", john.toString());

        return ResponseEntity.ok(JsonResponse.SUCCESS);
    }

    @JsonView(View.RecipientView.class)
    @GetMapping(path = "/broadcast", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResponse> broadcast(@RequestBody JsonRequestSender request) throws FriendException {

        logger.info("=== broadcast");

        request.validate();

        User john = userService.findByEmailAddress("john@example.com");

        logger.info("=== {}", john.toString());

        return ResponseEntity.ok(JsonResponse.recipients(Arrays.asList(john.getEmailAddress())));
    }
}
