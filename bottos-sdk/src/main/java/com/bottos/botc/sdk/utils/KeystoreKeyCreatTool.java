package com.bottos.botc.sdk.utils;


import com.bottos.botc.sdk.utils.crypto.DeterministicSeed;
import com.bottos.botc.sdk.entity.WalletKeyPair;
import com.bottos.botc.sdk.utils.crypto.CipherException;
import com.bottos.botc.sdk.utils.crypto.Credentials;
import com.bottos.botc.sdk.utils.crypto.ECKeyPair;
import com.bottos.botc.sdk.utils.crypto.Keys;
import com.bottos.botc.sdk.utils.crypto.SecureRandomUtils;
import com.bottos.botc.sdk.utils.crypto.Wallet;
import com.bottos.botc.sdk.utils.crypto.WalletFile;
import com.bottos.botc.sdk.utils.protocol.ObjectMapperFactory;
import com.bottos.botc.sdk.utils.crypto.Numeric;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;

import static com.bottos.botc.sdk.utils.crypto.Hash.sha256;

/**
 * Created by xionglh on 2018/8/20
 */
public class KeystoreKeyCreatTool {

    private static final SecureRandom secureRandom = SecureRandomUtils.secureRandom();
    private static final int PUBLIC_KEY_SIZE = 64;
    private static final int PUBLIC_KEY_LENGTH_IN_HEX = PUBLIC_KEY_SIZE << 1;
    private static ObjectMapper objectMapper = ObjectMapperFactory.getObjectMapper();

    private static WalletKeyPair createWalletKeyPair() {
        WalletKeyPair walletKeyPair = new WalletKeyPair();
        long creationTimeSeconds = System.currentTimeMillis() / 1000;
        DeterministicSeed ds = new DeterministicSeed(secureRandom, 128, "", creationTimeSeconds);
        ECKeyPair ecKeyPair = getECKeyPair(ds);
        BigInteger publicKey = ecKeyPair.getPublicKey();
        String publicKeyStr = Numeric.toHexStringWithPrefixZeroPadded(publicKey, PUBLIC_KEY_LENGTH_IN_HEX);
        BigInteger privateKey = ecKeyPair.getPrivateKey();
        String privateKeyStr = Numeric.toHexStringNoPrefixZeroPadded(privateKey, Keys.PRIVATE_KEY_LENGTH_IN_HEX);
        walletKeyPair.setPrivateKey(privateKeyStr);
        walletKeyPair.setPublicKey(publicKeyStr);
        return walletKeyPair;
    }

    private static ECKeyPair getECKeyPair(DeterministicSeed ds) {
        byte[] seedBytes = ds.getSeedBytes();
        if (seedBytes == null)
            return null;
        return ECKeyPair.create(sha256(seedBytes));
    }

    public static String createKeys() {
        WalletKeyPair walletKeyPair = createWalletKeyPair();
        return GJsonUtil.toJson(walletKeyPair, WalletKeyPair.class);
    }

    public static String createKeystore(String password, String privateKey) {
        ECKeyPair ecKeyPair = ECKeyPair.create(Numeric.toBigInt(privateKey));
        WalletFile walletFile;
        try {
            walletFile = Wallet.create(password, ecKeyPair, 1024, 1);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return GJsonUtil.toJson(walletFile, WalletFile.class);
    }

    public static String recoverKeystore(String pwd, String keystore) {
        Credentials credentials;
        ECKeyPair keypair;
        String privateKey = null;
        try {
            WalletFile walletFile = objectMapper.readValue(keystore, WalletFile.class);
            credentials = Credentials.create(Wallet.decrypt(pwd, walletFile));
            keypair = credentials.getEcKeyPair();
            privateKey = Numeric.toHexStringNoPrefixZeroPadded(keypair.getPrivateKey(), Keys.PRIVATE_KEY_LENGTH_IN_HEX);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CipherException e) {
            e.printStackTrace();
        }
        return privateKey;
    }

}
