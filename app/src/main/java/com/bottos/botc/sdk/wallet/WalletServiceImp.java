package com.bottos.botc.sdk.wallet;

import com.bottos.botc.sdk.BotcManger;
import com.bottos.botc.sdk.Config.Constants;
import com.bottos.botc.sdk.entity.BlockHeight;
import com.bottos.botc.sdk.entity.TradeInfo;
import com.bottos.botc.sdk.exceptions.BotcError;
import com.bottos.botc.sdk.exceptions.BotcException;
import com.bottos.botc.sdk.net.api.RequestCallBackImp;
import com.bottos.botc.sdk.net.request.CreateAccountParamsRequest;
import com.bottos.botc.sdk.net.request.RequestDataSign;
import com.bottos.botc.sdk.net.request.SendTransactionParamsRequest;
import com.bottos.botc.sdk.utils.KeystoreKeyCreatTool;
import com.bottos.botc.sdk.utils.Strings;

/**
 * Created by xionglh on 2018/9/5
 */
public class WalletServiceImp implements WalletService {


    @Override
    public String createKeys() {
        return KeystoreKeyCreatTool.createKeys();
    }

    @Override
    public String createKeystore(String password, String privateKey) {
        return KeystoreKeyCreatTool.createKeystore(password, privateKey);
    }

    @Override
    public String recoverKeystore(String pwd, String keystore) {
        return KeystoreKeyCreatTool.recoverKeystore(pwd, keystore);
    }

    @Override
    public void createAccount(final String name, final String publicKey, final String privateKey, final RequestCallBackImp<TradeInfo> requestCallBackImp) {
        if (Strings.isEmpty(name)) {
            throw new BotcException(BotcError.ACCOUNT_NAME_ERROR);
        }
        if (Strings.isEmpty(publicKey)) {
            throw new BotcException(BotcError.PUBLICKYE_EMPTY_ERR);
        }
        if (Strings.isEmpty(privateKey)) {
            throw new BotcException(BotcError.PRIVATEKEY_EMPTY_ERR);
        }
        BotcManger.getInstance().getBlickChainService().getBlockHeight(new RequestCallBackImp<BlockHeight>() {
            @Override
            public void onNext(BlockHeight blockHeight) {
                RequestDataSign sendTransactionRequest = new RequestDataSign();
                sendTransactionRequest.setVersion(1);
                sendTransactionRequest.setCursor_lab(blockHeight.getCursor_label());
                sendTransactionRequest.setCursor_num(blockHeight.getHead_block_num());
                sendTransactionRequest.setLifetime(blockHeight.getHead_block_time() + 30);
                sendTransactionRequest.setSender(Constants.CREATE_ACCOUNT_SEND);
                sendTransactionRequest.setMethod(Constants.CREATE_ACCOUNT_METHOD);
                sendTransactionRequest.setContract(Constants.CREATE_ACCOUNT_CONTRACT);
                sendTransactionRequest.setSig_alg(1);
                CreateAccountParamsRequest param = new CreateAccountParamsRequest();
                param.setName(name);
                param.setPubkey(publicKey);
                long[] params = CreateAccountParamsRequest.getPackParam(param);
                sendTransactionRequest.setParam(params);
                RequestDataSign.getSignaturedFetchParam(sendTransactionRequest, privateKey, blockHeight.getChain_id());
                BotcManger.getInstance().getApiWrapper().sendTransaction(sendTransactionRequest, requestCallBackImp);
            }
        });
    }

    @Override
    public void sendTransaction(final String toUser, final String fromUser, final long price, final String privateKey, final RequestCallBackImp<TradeInfo> requestCallBackImp) {
        if (Strings.isEmpty(toUser)||Strings.isEmpty(fromUser)) {
            throw new BotcException(BotcError.PARAMS_EMPTY_EMPTY_ERR);
        }

        BotcManger.getInstance().getBlickChainService().getBlockHeight(new RequestCallBackImp<BlockHeight>() {
            @Override
            public void onNext(BlockHeight blockHeight) {
                RequestDataSign sendTransactionRequest = new RequestDataSign();
                sendTransactionRequest.setVersion(1);
                sendTransactionRequest.setCursor_lab(blockHeight.getCursor_label());
                sendTransactionRequest.setCursor_num(blockHeight.getHead_block_num());
                sendTransactionRequest.setLifetime(blockHeight.getHead_block_time() + 30);
                sendTransactionRequest.setSender(fromUser);
                sendTransactionRequest.setMethod(Constants.SEND_TRANSACTION_METHOD);
                sendTransactionRequest.setContract(Constants.SEND_TRANSACTION_CONTRACT);
                sendTransactionRequest.setSig_alg(1);
                SendTransactionParamsRequest param = new SendTransactionParamsRequest();
                param.setTo(toUser);
                param.setFrom(fromUser);
                param.setPrice(price);
                long[] params = SendTransactionParamsRequest.getPackParam(param);
                sendTransactionRequest.setParam(params);
                RequestDataSign.getSignaturedFetchParam(sendTransactionRequest, privateKey, blockHeight.getChain_id());
                BotcManger.getInstance().getApiWrapper().sendTransaction(sendTransactionRequest, requestCallBackImp);
            }
        });
    }


}
