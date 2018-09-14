package com.bottos.botc.sdk.entity;



/**
 * Created by xionglh on 2018/9/5
 */
public class TradeInfo {

    private int version;//链版本号

    private long cursor_num;//最新区块号，调用获取区块头获得

    private int cursor_lab;//最新区块标签，调用获取区块头获得

    private long lifetime;//交易过期时间，调用获取区块头，加一定的延时

    private String sender;//发送者

    private String contract;//合约名称

    private String method;//合约方法

    private String param;//业务参数，16进制字符串

    private long sig_alg;//签名算法

    private String signature;//签名值

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

    public int getCursor_lab() {
        return cursor_lab;
    }

    public void setCursor_lab(int cursor_lab) {
        this.cursor_lab = cursor_lab;
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

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

}
