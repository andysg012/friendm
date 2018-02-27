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

    @JsonView(View.ExceptionView.class)
    private String message;

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

    public JsonResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public static JsonResponse friends(List<String> friends) {
        return new JsonResponse(true, friends, Collections.emptyList());
    }

    public static JsonResponse recipients(List<String> recipients) {
        return new JsonResponse(true, Collections.emptyList(), recipients);
    }

    public static JsonResponse exception(String message) {
        return new JsonResponse(false, message);
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
