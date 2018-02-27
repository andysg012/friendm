package com.example.friendm.json;

public class JsonExceptionResponse {

    private boolean success;

    private String message;

    public JsonExceptionResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public static JsonExceptionResponse exception(String message) {
        return new JsonExceptionResponse(false, message);
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
