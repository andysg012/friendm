package com.example.friendm.user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRelationshipService {

    @Autowired
    private UserRelationshipRepository userRelationshipRepository;

    public void connect(Long userId, Long friendId) {

        UserRelationshipPK pk1 = new UserRelationshipPK(userId, friendId, UserRelationshipType.FRIEND);
        UserRelationshipPK pk2 = new UserRelationshipPK(friendId, userId, UserRelationshipType.FRIEND);

        save(new UserRelationship(pk1));
        save(new UserRelationship(pk2));
    }

    public void subscribe(Long requestorId, Long targetId) {

        UserRelationshipPK pk1 = new UserRelationshipPK(targetId, requestorId, UserRelationshipType.SUBSCRIBE);

        save(new UserRelationship(pk1));
    }

    public void block(Long requestorId, Long targetId) {

        UserRelationshipPK pk1 = new UserRelationshipPK(targetId, requestorId, UserRelationshipType.BLOCK);

        save(new UserRelationship(pk1));
    }

    public List<String> broadcast(Long senderId) {

        Set<String> recipients = new HashSet<>();
        List<UserRelationship> friends = userRelationshipRepository.findByUserId(senderId);

        if (friends != null) {
            for (UserRelationship friend : friends) {
                if (friend.getId().getRelationshipType() != UserRelationshipType.BLOCK) {
                    recipients.add(friend.getFriend().getEmailAddress());
                } else if (friend.getId().getRelationshipType() == UserRelationshipType.BLOCK) {
                    recipients.remove(friend.getFriend().getEmailAddress());
                }
            }
        }

        return new ArrayList<String>(recipients);
    }

    private void save(UserRelationship userRelationship) {

        UserRelationship retrievedUserRelationship = userRelationshipRepository.findOne(userRelationship.getId());
        if (retrievedUserRelationship == null) {
            userRelationshipRepository.save(userRelationship);
        }
    }
}
