package com.kypi.demoproject.domain.error;

public class MyError extends Exception {

    public static final int
            SUCCESS = 0,
            FAIL = 1,
            ERROR = 2,
            DDOS = 3,
            INVALID_PARAMS = 4,
            NOT_EXISTS = 5,
            EXISTED = 6,
            NOT_LOGIN = 7,
            FULL = 8,
            PERMISSION_DENI = 9,
            INVALID_USERNAME = 100,
            INVALID_DISPLAY_NAME = 101,
            INVALID_BIRTHDAY = 102,
            INVALID_GENDER = 103,
            INVALID_PHONE = 104,
            INVALID_EMAIL = 105,

    // Local Error Code
    // Error code này được bắn trong trường hợp Điện thoại ko có mạng, và chapter chưa được tải
    NOT_DOWNLOADED = 1001;



    private int statusCode;
    private String message;

    public MyError(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public int getMyErrorStatus() {
        return statusCode;
    }

    public String getMyErrorMessage() {
        return message;
    }
}