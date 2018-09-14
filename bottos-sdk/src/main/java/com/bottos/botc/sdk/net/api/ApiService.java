package com.bottos.botc.sdk.net.api;

import com.bottos.botc.sdk.entity.BlockHeight;
import com.bottos.botc.sdk.entity.TradeInfo;
import com.bottos.botc.sdk.net.response.CommonResponse;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by xionglh on 2018/9/4
 */
public interface ApiService {

    @GET("block/height")
    Observable<CommonResponse<BlockHeight>> requestBlockHeight();

    @POST("transaction/send")
    Observable<CommonResponse<TradeInfo>> transactionSend(@Body RequestBody requestBody);



}
