package com.example.friendm.view;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

public class ViewResponseEntity {

    @JsonView(View.SuccessView.class)
    private boolean success;

    @JsonView(View.FriendView.class)
    private List<String> friends;

    @JsonView(View.FriendView.class)
    private int count;

    @JsonView(View.RecipientView.class)
    private List<String> recipients;

    public static final ViewResponseEntity SUCCESS = new ViewResponseEntity(true);
    public static final ViewResponseEntity FAILED = new ViewResponseEntity(false);

    public ViewResponseEntity(boolean success) {
        this.success = success;
    }

    public ViewResponseEntity(boolean success, List<String> friends, List<String> recipients) {
        this.success = success;
        this.friends = friends;
        this.count = friends.size();
        this.recipients = recipients;
    }

    public static ViewResponseEntity friends(List<String> friends) {
        return new ViewResponseEntity(true, friends, Collections.emptyList());
    }

    public static ViewResponseEntity recipients(List<String> recipients) {
        return new ViewResponseEntity(true, Collections.emptyList(), recipients);
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }

    public List<String> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<String> recipients) {
        this.recipients = recipients;
    }
}
