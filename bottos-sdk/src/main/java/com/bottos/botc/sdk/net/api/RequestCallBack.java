package com.bottos.botc.sdk.net.api;

/**
 * 请求回调
 * Created by xionglh on 2017/2/22.
 */
public interface RequestCallBack<T> {

    void onCompleted();
    void onError(NetRequestException apiexception);
    void onNext(T t);
}
