package com.example.friendm.json;

import com.example.friendm.friend.FriendException;

public class JsonRequestRequestor {

    private String requestor;
    private String target;

    public String getRequestor() {
        return requestor;
    }

    public void setRequestor(String requestor) {
        this.requestor = requestor;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public void validate() throws FriendException {
        if (requestor == null) throw new FriendException("Request requestor is empty.");
        if (requestor.length() == 0) throw new FriendException("Request requestor is empty string.");

        if (target == null) throw new FriendException("Request target is empty.");
        if (target.length() == 0) throw new FriendException("Request target is empty string.");
    }
}
