package com.example.friendm.json;

import java.util.List;

import com.example.friendm.friend.FriendException;

public class JsonRequestFriends {

    private List< String> friends;

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }

    public void validate() throws FriendException {
        if (friends == null) throw new FriendException("Request friends list is empty.");
        if (friends.size() != 2) throw new FriendException("Request friends list must ONLY contains 2 emails.");
        if (friends.get(0).length() == 0 || friends.get(1).length() == 0) throw new FriendException("Request friends list have empty string.");
        if (friends.get(0).equalsIgnoreCase(friends.get(1))) throw new FriendException("Request friends list have duplicate emails.");
    }
}
