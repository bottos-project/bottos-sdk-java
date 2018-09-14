package com.bottos.botc.sdk;

import com.bottos.botc.sdk.wallet.WalletService;
import com.bottos.botc.sdk.wallet.WalletServiceImp;
import com.bottos.botc.sdk.blockchain.BlockChainService;
import com.bottos.botc.sdk.blockchain.BlockChainServiceImp;
import com.bottos.botc.sdk.net.api.ApiWrapper;

/**
 * Created by xionglh on 2018/9/6
 */
public class BotcManger {

    private static BotcManger botcManger;

    private ApiWrapper mApiWrapper;

    private BotcManger() {
        mApiWrapper = new ApiWrapper();
    }

    public ApiWrapper getApiWrapper() {
        return mApiWrapper;
    }

    public synchronized static BotcManger getInstance() {
        if (botcManger == null) {
            botcManger = new BotcManger();
        }
        return botcManger;
    }

    public BlockChainService getBlickChainService() {
        return new BlockChainServiceImp();
    }

    public WalletService getWalletService() {
        return new WalletServiceImp();
    }


}
