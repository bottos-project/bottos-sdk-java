package com.bottos.botc.sdk.net.request;

import com.bottos.botc.sdk.utils.ArraysUtils;
import com.bottos.botc.sdk.utils.msgpack.MsgPack;

/**
 * Created by xionglh on 2018/9/13
 */
public class SendTransactionParamsRequest {


    private String from;

    private String to;

    private long price;


    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public static long[] getPackParam(SendTransactionParamsRequest param) {
        long[] arsize = MsgPack.packArraySize(3);
        long[] froml = MsgPack.packStr16(param.getFrom());
        long[] to1 = MsgPack.packStr16(param.getTo());
        long[] pricel = MsgPack.packUint256(String.valueOf(param.getPrice()));
        return ArraysUtils.arrayCopylong(arsize, froml, to1, pricel);
    }

}
