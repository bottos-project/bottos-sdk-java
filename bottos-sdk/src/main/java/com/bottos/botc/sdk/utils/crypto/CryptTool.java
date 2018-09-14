package com.bottos.botc.sdk.utils.crypto;

/**
 * Created by xionglh on 2018/9/12
 */
public class CryptTool {

    public static String getSignaturedParam(long  [] buf, String privateKeyStr) {
        String hex = "";
        for (int i = 0; i < buf.length; i++) {
            String hexStr=Long.toHexString(buf[i]);
            hexStr=hexStr.length()==1?"0"+hexStr:hexStr;
            hex +=hexStr ;
        }
        byte []hass=Sha256Hash.hash(hex.getBytes());
        ECKeyPair ecKeyPair = ECKeyPair.create(Numeric.toBigInt(privateKeyStr));
        ECDSASignature signature= ecKeyPair.sign(hass);
        return signature.r.toString(16)+signature.s.toString(16);
    }

    public static String getHex16(long []buf){
        String hex = "";
        for (int i = 0; i < buf.length; i++) {
            String hexStr=Long.toHexString(buf[i]);
            hexStr=hexStr.length()==1?"0"+hexStr:hexStr;
            hex +=hexStr ;
        }
        return hex;
    }

}
