package com.smartkid.dd.exception;

import androidx.annotation.Nullable;

public class AppException extends Exception {
    private String message;
    private int num;

    public AppException(String message, int num){
        this.message = message;
        this.num = num;
    }

    @Nullable
    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
