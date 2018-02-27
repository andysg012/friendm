package com.example.friendm.user;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(
    indexes = {
        @Index(name = "USER_IDX_1", columnList = "emailAddress", unique = true) })
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String emailAddress;

    //private List<UserFriends> friends = new ArrayList<>();

    public User() {
    }

    public User(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return String.format("User: { id: %d, emailAddress: %s }", id, emailAddress);
    }
}
