package com.example.friendm.user;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class UserRelationshipPK implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long userId;

    private Long friendId;

    private UserRelationshipType relationshipType;

    public UserRelationshipPK() {
    }

    public UserRelationshipPK(Long userId, Long friendId, UserRelationshipType relationshipType) {
        this.userId = userId;
        this.friendId = friendId;
        this.relationshipType = relationshipType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFriendId() {
        return friendId;
    }

    public void setFriendId(Long friendId) {
        this.friendId = friendId;
    }

    public UserRelationshipType getRelationshipType() {
        return relationshipType;
    }

    public void setRelationshipType(UserRelationshipType relationshipType) {
        this.relationshipType = relationshipType;
    }

    public boolean equals(Object object) {
        if (object instanceof UserRelationshipPK) {
            UserRelationshipPK pk = (UserRelationshipPK)object;
            return userId == pk.userId && friendId == pk.friendId && relationshipType.equals(pk.relationshipType);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return (int)(userId + friendId + relationshipType.hashCode());
    }
}
