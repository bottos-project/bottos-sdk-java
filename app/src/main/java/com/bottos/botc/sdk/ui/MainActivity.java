package com.bottos.botc.sdk.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bottos.botc.sdk.entity.TradeInfo;
import com.bottos.botc.sdk.net.api.NetRequestException;
import com.bottos.botc.sdk.net.api.RequestCallBackImp;
import com.bottos.botc.sdk.utils.GJsonUtil;
import com.bottos.botc.sdk.utils.KeystoreKeyCreatTool;
import com.bottos.botc.sdk.utils.msgpack.MsgUnPack;
import com.bottos.botc.sdk.wallet.WalletServiceImp;
import com.bottos.botc.sdk.R;
import com.bottos.botc.sdk.log.BtoLog;
import com.bottos.botc.sdk.utils.msgpack.MsgPack;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by xionglh on 2018/9/5
 */
public class MainActivity extends Activity {

    private WalletServiceImp mBottosService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String ss= KeystoreKeyCreatTool.createKeys();
        BtoLog.e("keystore",ss);
        mBottosService = new WalletServiceImp();
        findViewById(R.id.btn_create).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String puckkey="040aa3e0749eb7ebed6cabdd400b8144a5a1b5a0b9aedb0047c6a5bd66edd5ea4d768c1216295003b22ffefdc8473d06199ca62379018faaabda2e43d5c092aa";
                String privateKey="89cb844b1c22e06f3e884fea69044a463eea1652439edf9734e01ebe8685fe5c";
                mBottosService.createAccount("xionglihui" + (new Random(100)).nextInt()
                        , puckkey, privateKey, new RequestCallBackImp<TradeInfo>() {
                    @Override
                    public void onNext(TradeInfo tradeInfo) {
                        String tradeInfoStr= GJsonUtil.toJson(tradeInfo,TradeInfo.class);
                    }

                    @Override
                    public void onError(NetRequestException apiexception) {
                        super.onError(apiexception);
                    }
                });

                BtoLog.e("MsgPack.PackUint8  ", Arrays.toString(MsgPack.packUint8(16)));
                BtoLog.e("MsgUnPack.unPackUint8  ", MsgUnPack.unPackUint8(MsgPack.packUint8(16)) +"");

                BtoLog.e("MsgPack.PackUint16", Arrays.toString(MsgPack.packUint16(16)));
                BtoLog.e("MsgUnPack.unPackUint16  ", MsgUnPack.unPackUint16(MsgPack.packUint16(16)) +"");

                BtoLog.e("MsgPack.PackUint32", Arrays.toString(MsgPack.packUint32(199999)));
                BtoLog.e("MsgUnPack.unPackUint32  ", MsgUnPack.unPackUint32(MsgPack.packUint32(199999)) +"");


                BtoLog.e("MsgPack.packUint64", Arrays.toString(MsgPack.packUint64(999999999999l)));
                BtoLog.e("MsgUnPack.unPackUint64  ", MsgUnPack.unPackUint64(MsgPack.packUint64(999999999999l)) +"");

                BtoLog.e("MsgPack.PackUint256", Arrays.toString(MsgPack.packUint256("10000000000")));

                long[] cc = new long[]{206, 0, 3, 13, 63};
                BtoLog.e("MsgPack.PackBin16",Arrays.toString(MsgPack.packBin16(cc)));
                BtoLog.e("MsgPack.unPackBin16",Arrays.toString(MsgUnPack.unPackBin16(MsgPack.packBin16(cc))));


                BtoLog.e("MsgPack.PackStr16", Arrays.toString(MsgPack.packStr16("abcd")));
                BtoLog.e("MsgPack.unPackStr16", MsgUnPack.unPackStr16(MsgPack.packStr16("abcd")));


                BtoLog.e("MsgPack.PackArraySize", Arrays.toString(MsgPack.packArraySize(499)));
                BtoLog.e("MsgPack.unPackArraySize",MsgUnPack.unPackArraySize(MsgPack.packArraySize(499))+"");


            }
        });

    }




}
