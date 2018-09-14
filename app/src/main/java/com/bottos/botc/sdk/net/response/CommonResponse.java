package com.bottos.botc.sdk.net.response;


/**
 * @param <T>
 */
public class CommonResponse<T> {

    private T result;

    private int errcode;

    private String msg;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
