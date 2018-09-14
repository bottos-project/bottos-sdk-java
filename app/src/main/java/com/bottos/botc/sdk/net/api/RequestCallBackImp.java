package com.bottos.botc.sdk.net.api;

/**
 * Created by xionglh on 2017/2/22.
 */
public abstract class RequestCallBackImp<T> implements RequestCallBack<T> {

    @Override
    public void onCompleted() {
    }
    @Override
    public void onError(NetRequestException apiexception) {

    }
}
