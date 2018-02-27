package com.example.friendm.friend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

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
    FriendManagementService service;

    @JsonView(View.SuccessView.class)
    @PostMapping(path = "/connect", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResponse> connect(@RequestBody JsonRequestFriends request) throws FriendException {

        logger.info("=== connect");

        request.validate();

        String email1 = request.getFriends().get(0);
        String email2 = request.getFriends().get(1);

        logger.info("=== {} {}", email1, email2);

        service.connect(email1, email2);

        return ResponseEntity.ok(JsonResponse.SUCCESS);
    }

    @JsonView(View.FriendView.class)
    @GetMapping(path = "/friends", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResponse> getFriendsList(@RequestBody JsonRequestEmail request) throws FriendException {

        logger.info("=== getFriendsList");

        request.validate();

        String email = request.getEmail();

        logger.info("=== {}", email);

        Set<String> friends = service.getFriends(email);

        return ResponseEntity.ok(JsonResponse.friends(new ArrayList<String>(friends)));
    }

    @JsonView(View.FriendView.class)
    @GetMapping(path = "/common", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResponse> getCommonFriendsList(@RequestBody JsonRequestFriends request) throws FriendException {

        logger.info("=== getCommonFriendsList");

        request.validate();

        String email1 = request.getFriends().get(0);
        String email2 = request.getFriends().get(1);

        logger.info("=== {} {}", email1, email2);

        List<String> commonFriends = service.getCommonFriends(email1, email2);

        return ResponseEntity.ok(JsonResponse.friends(commonFriends));
    }

    @JsonView(View.SuccessView.class)
    @PutMapping(path = "/subscribe", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResponse> subscribe(@RequestBody JsonRequestRequestor request) throws FriendException {

        logger.info("=== subscribe");

        request.validate();

        String requestor = request.getRequestor();
        String target = request.getTarget();

        logger.info("=== {} {}", requestor, target);

        service.subscribe(requestor, target);

        return ResponseEntity.ok(JsonResponse.SUCCESS);
    }

    @JsonView(View.SuccessView.class)
    @PutMapping(path = "/block", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResponse> block(@RequestBody JsonRequestRequestor request) throws FriendException {

        logger.info("=== block");

        request.validate();

        String requestor = request.getRequestor();
        String target = request.getTarget();

        logger.info("=== {} {}", requestor, target);

        service.block(requestor, target);

        return ResponseEntity.ok(JsonResponse.SUCCESS);
    }

    @JsonView(View.RecipientView.class)
    @GetMapping(path = "/broadcast", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResponse> broadcast(@RequestBody JsonRequestSender request) throws FriendException {

        logger.info("=== broadcast");

        request.validate();

        String sender = request.getSender();
        String text = request.getText();

        logger.info("=== {} {}", sender, text);

        List<String> recipients = service.broadcast(sender, text);

        return ResponseEntity.ok(JsonResponse.recipients(recipients));
    }
}
