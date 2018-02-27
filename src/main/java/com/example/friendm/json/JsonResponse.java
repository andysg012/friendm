package com.example.friendm.json;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

public class JsonResponse {

    @JsonView(View.SuccessView.class)
    private boolean success;

    @JsonView(View.FriendView.class)
    private List<String> friends;

    @JsonView(View.FriendView.class)
    private int count;

    @JsonView(View.RecipientView.class)
    private List<String> recipients;

    public static final JsonResponse SUCCESS = new JsonResponse(true);
    public static final JsonResponse FAILED = new JsonResponse(false);

    public JsonResponse(boolean success) {
        this.success = success;
    }

    public JsonResponse(boolean success, List<String> friends, List<String> recipients) {
        this.success = success;
        this.friends = friends;
        this.count = friends.size();
        this.recipients = recipients;
    }

    public static JsonResponse friends(List<String> friends) {
        return new JsonResponse(true, friends, Collections.emptyList());
    }

    public static JsonResponse recipients(List<String> recipients) {
        return new JsonResponse(true, Collections.emptyList(), recipients);
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
