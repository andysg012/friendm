package com.example.friendm.json;

import com.example.friendm.friend.FriendException;

public class JsonRequestEmail {

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void validate() throws FriendException {
        if (email == null) throw new FriendException("Request email is empty.");
        if (email.length() == 0) throw new FriendException("Request email is empty string.");
    }
}
