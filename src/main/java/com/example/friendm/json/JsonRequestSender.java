package com.example.friendm.json;

import com.example.friendm.friend.FriendException;

public class JsonRequestSender {

    private String sender;
    private String text;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void validate() throws FriendException {
        if (sender == null) throw new FriendException("Request sender is empty.");
        if (sender.length() == 0) throw new FriendException("Request sender is empty string.");

        if (text == null) throw new FriendException("Request text is empty.");
        if (text.length() == 0) throw new FriendException("Request text is empty string.");
    }
}
