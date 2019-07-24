package com.ali.test.nine.concurrent;

public interface OnResponseListener {
    void onFailure(String message);
    void onSuccess(long responseTime);
}
