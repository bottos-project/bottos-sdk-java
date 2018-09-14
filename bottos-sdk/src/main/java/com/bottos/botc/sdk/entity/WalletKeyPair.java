package com.bottos.botc.sdk.entity;

/**
 * Created by xionglh on 2018/8/20
 */
public class WalletKeyPair {

    private String publicKey;

    private String privateKey;

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }
}
