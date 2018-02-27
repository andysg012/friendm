package com.example.friendm.user;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserFriends {

    @EmbeddedId
    private Long id;

    private UserRelationshipType relationshipType;
}
