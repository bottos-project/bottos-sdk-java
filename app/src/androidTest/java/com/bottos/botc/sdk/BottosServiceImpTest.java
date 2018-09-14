package com.bottos.botc.sdk;

import com.bottos.botc.sdk.wallet.WalletServiceImp;
import com.bottos.botc.sdk.entity.BlockHeight;
import com.bottos.botc.sdk.net.api.RequestCallBackImp;

/**
 * Created by xionglh on 2018/9/5
 */
public class BottosServiceImpTest {

    @org.junit.Test
    public void test() {
        WalletServiceImp mBottosService = new WalletServiceImp();
        mBottosService.createAccount("", "", new RequestCallBackImp<BlockHeight>() {
            @Override
            public void onNext(BlockHeight blockHeight) {

            }
        });
    }
}