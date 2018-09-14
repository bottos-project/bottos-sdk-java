package com.bottos.botc.sdk.wallet;

import com.bottos.botc.sdk.entity.TradeInfo;
import com.bottos.botc.sdk.net.api.RequestCallBackImp;

/**
 * Created by xionglh on 2018/9/4
 */
public interface WalletService {


    String createKeys();

    String createKeystore(String password, String privateKey);

    String recoverKeystore(String pwd, String keystore);

    void sendTransaction( String toUser,  String fromUser, long price, String privateKey, RequestCallBackImp<TradeInfo> requestCallBackImp);

    void createAccount(String name, String publicKey,String privateKey, final RequestCallBackImp<TradeInfo> requestCallBackImp);


}
