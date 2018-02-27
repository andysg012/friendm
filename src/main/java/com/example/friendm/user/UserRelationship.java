package com.example.friendm.user;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class UserRelationship {

    @EmbeddedId
    private UserRelationshipPK id;

    @OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="userId", referencedColumnName="id", insertable = false, updatable = false)
	private User user;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="friendId", referencedColumnName="id", insertable = false, updatable = false)
	private User friend;

    public UserRelationship() {
    }

    public UserRelationship(UserRelationshipPK id) {
        this.id = id;
    }

    public UserRelationshipPK getId() {
        return id;
    }

    public void setId(UserRelationshipPK id) {
        this.id = id;
    }

    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getFriend() {
		return friend;
	}

	public void setFriend(User friend) {
		this.friend = friend;
	}
}
