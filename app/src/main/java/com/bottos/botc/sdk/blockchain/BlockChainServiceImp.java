package com.bottos.botc.sdk.blockchain;

import com.bottos.botc.sdk.BotcManger;
import com.bottos.botc.sdk.entity.BlockHeight;
import com.bottos.botc.sdk.net.api.RequestCallBackImp;

/**
 * Created by xionglh on 2018/9/6
 */
public class BlockChainServiceImp implements  BlockChainService {

    @Override
    public void getBlockHeight(RequestCallBackImp<BlockHeight> requestCallBackImp) {
        BotcManger.getInstance().getApiWrapper().requestBlockHeight(requestCallBackImp);
    }

}
