package com.yonyou.accessibilityservicetest;

/**
 * Created by wangjt on 2017/9/7.
 */

public class MessageEvent {
    String message;

    public MessageEvent() {
    }

    public MessageEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
