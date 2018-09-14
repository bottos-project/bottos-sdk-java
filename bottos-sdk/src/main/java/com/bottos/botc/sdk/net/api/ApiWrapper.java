package com.bottos.botc.sdk.net.api;

import com.bottos.botc.sdk.entity.BlockHeight;
import com.bottos.botc.sdk.entity.TradeInfo;
import com.bottos.botc.sdk.net.request.RequestDataSign;

/**
 * Created by xionglh on 2018/9/4
 */

@SuppressWarnings("unchecked")
public class ApiWrapper extends RetrofitClient {


    public void requestBlockHeight(RequestCallBackImp<BlockHeight> requestCallBackImp) {
       applySchedulers(getService(ApiService.class).requestBlockHeight()).subscribe(newMySubscriber(requestCallBackImp));
    }

    public void sendTransaction(RequestDataSign sendTransactionRequest, RequestCallBackImp<TradeInfo> requestCallBackImp){
        applySchedulers(getService(ApiService.class).transactionSend(toRequestBody(sendTransactionRequest))).subscribe( newMySubscriber(requestCallBackImp));
    }

}
