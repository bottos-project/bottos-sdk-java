package com.bottos.botc.sdk.net.request;

import com.bottos.botc.sdk.utils.ArraysUtils;
import com.bottos.botc.sdk.utils.crypto.CryptTool;
import com.bottos.botc.sdk.utils.crypto.Numeric;
import com.bottos.botc.sdk.utils.msgpack.MsgPack;

/**
 * Created by xionglh on 2018/9/5
 */
public class RequestDataSign extends BaseParamsRequest {

    private int version = 1;

    private long cursor_num;

    private long cursor_label;

    private long lifetime;

    private String sender;

    private String contract;

    private String method;

    private String param;

    private long sig_alg;

    private String signature;



    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public long getCursor_num() {
        return cursor_num;
    }

    public void setCursor_num(long cursor_num) {
        this.cursor_num = cursor_num;
    }

    public long getCursor_label() {
        return cursor_label;
    }

    public void setCursor_label(long cursor_label) {
        this.cursor_label = cursor_label;
    }

    public long getLifetime() {
        return lifetime;
    }

    public void setLifetime(long lifetime) {
        this.lifetime = lifetime;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public long getSig_alg() {
        return sig_alg;
    }

    public void setSig_alg(long sig_alg) {
        this.sig_alg = sig_alg;
    }


    public static long[] messageProtoEncode(RequestDataSign sendTransactionRequest,long []param) {
        long[] pArraySize = MsgPack.packArraySize(9);
        long[] pVersion = MsgPack.packUint32(sendTransactionRequest.getVersion());
        long[] pCursorNum = MsgPack.packUint64(sendTransactionRequest.getCursor_num());
        long[] pCursorLabel = MsgPack.packUint32(sendTransactionRequest.getCursor_label());
        long[] pLifeTime = MsgPack.packUint64(sendTransactionRequest.getLifetime());
        long[] pSender = MsgPack.packStr16(sendTransactionRequest.getSender());
        long[] pContract = MsgPack.packStr16(sendTransactionRequest.getContract());
        long[] pMethod = MsgPack.packStr16(sendTransactionRequest.getMethod());
        long[] pParam = MsgPack.packBin16(param);
        long[] uint8Param = new long[pParam.length];
        for (int i = 0; i < pParam.length; i++) {
            uint8Param[i] = MsgPack.Uint8Array(pParam[i]);
        }
        long[] pSigalg = MsgPack.packUint32(sendTransactionRequest.getSig_alg());
        return ArraysUtils.arrayCopylong(pArraySize, pVersion, pCursorNum, pCursorLabel, pLifeTime, pSender, pContract, pMethod, uint8Param, pSigalg);
    }

    public static void getSignaturedFetchParam(RequestDataSign sendTransactionRequest,long []params, String privateKeyStr, String chain) {
        long[] encodeBuf1 = messageProtoEncode(sendTransactionRequest,params);
        byte[] ss = Numeric.hexStringToByteArray(chain);
        long[] encodeBuf2 = new long[ss.length];
        for (int i = 0; i < ss.length; i++) {
            encodeBuf2[i] = MsgPack.Uint8Array((long) ss[i]);
        }
        long[] newMsgProto = ArraysUtils.arrayCopylong(encodeBuf1, encodeBuf2);
        String rStr = CryptTool.getSignaturedParam(newMsgProto, privateKeyStr);
        sendTransactionRequest.setSignature(rStr);
    }



}
