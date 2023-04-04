package com.example.sendhttprequest;

public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
