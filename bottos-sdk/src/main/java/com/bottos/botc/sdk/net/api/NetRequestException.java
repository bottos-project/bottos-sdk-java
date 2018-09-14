package com.bottos.botc.sdk.net.api;

import android.support.annotation.NonNull;

/**
 * Created by xionglh on 2017/2/23.
 */
public class NetRequestException extends Exception {


    private int code;
    private String message;

    public NetRequestException(@NonNull int code, @NonNull String message) {
        super(String.format("Request error:errcode:msgCode %s ,%s", code, message));
        this.code = code;
        this.message = message;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
