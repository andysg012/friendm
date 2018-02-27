package com.example.friendm.friend;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import com.example.friendm.user.User;
import com.example.friendm.user.UserRelationship;
import com.example.friendm.user.UserRelationshipService;
import com.example.friendm.user.UserRelationshipType;
import com.example.friendm.user.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendManagementService {

	private static final Logger logger = LoggerFactory.getLogger(FriendManagementService.class);

	@Autowired
	private UserService userService;

	@Autowired
	private UserRelationshipService userRelationshipService;

	@Transactional
	public void connect(String email1, String email2) {

        User user1 = userService.saveIfEmailAddressNotExists(email1);
        User user2 = userService.saveIfEmailAddressNotExists(email2);

		userRelationshipService.connect(user1.getId(), user2.getId());
	}

	public Set<String> getFriends(String email) throws FriendException {

        Set<String> friends = new HashSet<>();

        User user = getUser(email);

        List<UserRelationship> userRelationships = user.getFriends();

		for (UserRelationship userRelationship : userRelationships) {
			if(UserRelationshipType.FRIEND.equals(userRelationship.getId().getRelationshipType())) {
				friends.add(userRelationship.getFriend().getEmailAddress());
			}
        }

		return friends;
	}

	public List<String> getCommonFriends(String email1, String email2) throws FriendException {

        List<String> commonFriends = new ArrayList<String>();

		Set<String> friend1 = getFriends(email1);
		Set<String> friend2 = getFriends(email2);

		if(friend1.size() > 0 && friend2.size() > 0) {
			for(String friend: friend2) {
				if(friend1.contains(friend)) {
					commonFriends.add(friend);
				}
			}
        }

		return commonFriends;
    }

	public void subscribe(String requestor, String target) {

		User user1 = userService.saveIfEmailAddressNotExists(requestor);
		User user2 = userService.saveIfEmailAddressNotExists(target);

		userRelationshipService.subscribe(user1.getId(), user2.getId());
	}

	public void block(String requestor, String target) throws FriendException {

		User user1 = getUser(requestor);
		User user2 = getUser(target);

		userRelationshipService.block(user1.getId(), user2.getId());
	}

	public List<String> broadcast(String senderEmail, String text) throws FriendException {

        User sender = getUser(senderEmail);

        List<String> recipients = userRelationshipService.broadcast(sender.getId());

        // Not sure whether should create kate@example.com
        // For now just add as recipients
        Matcher m = Pattern.compile("[a-zA-Z0-9.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9]+").matcher(text);
        while (m.find()) {
            recipients.add(m.group());
        }

		return recipients;
	}

	private User getUser(String email) throws FriendException {

        User user = userService.findByEmailAddress(email);

		if(user == null) {
			throw new FriendException("User not exists.");
        }

		return user;
	}

}
