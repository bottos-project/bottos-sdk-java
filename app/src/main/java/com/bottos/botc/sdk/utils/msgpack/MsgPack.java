package com.bottos.botc.sdk.utils.msgpack;

import java.math.BigInteger;

/**
 * Created by xionglh on 2018/9/6
 */
public final class MsgPack {

    public final static int BIN16 = 0xc5;
    public final static int UINT8 = 0xcc;
    public final static int UINT16 = 0xcd;
    public final static int UINT32 = 0xce;
    public final static int UINT64 = 0xcf;
    public final static int STR16 = 0xda;
    public final static int ARRAY16 = 0xdc;
    public final static int LEN_INT32 = 4;
    public final static int LEN_INT64 = 8;
    public final static int MAX16BIT = 2 << (16 - 1);
    public final static int REGULAR_UINT7_MAX = 2 << (7 - 1);
    public final static int REGULAR_UINT8_MAX = 2 << (8 - 1);
    public final static int REGULAR_UINT16_MAX = 2 << (16 - 1);
    public final static int REGULAR_UINT32_MAX = 2 << (32 - 1);
    public final static int SPECIAL_INT8 = 32;
    public final static int SPECIAL_INT32 = 2 << (16 - 2);
    public final static int SPECIAL_INT64 = 2 << (32 - 2);

    public static long Uint8Array(long num) {
        long j = num;
        if (j < 0) {
            j = num + Math.abs(num) / 256 * 256+256;
        }
        if (j > 255) {
            j = num - num / 256 * 256;
        }
        return j;
    }

    public static long[] packUint8(long value) {
        long[] buf = new long[2];
        buf[0] = Uint8Array(UINT8);
        buf[1] = Uint8Array(value);
        return buf;
    }


    public static long[] packUint16(int value) {
        long[] buf = new long[3];
        buf[0] = Uint8Array(UINT16);
        buf[1] = Uint8Array(value >> 8);
        buf[2] = Uint8Array(value);
        return buf;
    }

    public static long[] packUint32(long value) {
        long[] buf = new long[5];
        buf[0] = Uint8Array(UINT32);
        buf[1] = Uint8Array(value >> 24);
        buf[2] = Uint8Array(value >> 16);
        buf[3] = Uint8Array(value >> 8);
        buf[4] = Uint8Array(value);
        return buf;
    }

    public static long[] packUint64(long n) {
        long[] buf = new long[9];
        buf[0] = Uint8Array(UINT64);
        buf[1] = Uint8Array(n >> 56);
        buf[2] = Uint8Array(n >> 48);
        buf[3] = Uint8Array(n >> 40);
        buf[4] = Uint8Array(n >> 32);
        buf[5] = Uint8Array(n >> 24);
        buf[6] = Uint8Array(n >> 16);
        buf[7] = Uint8Array(n >> 8);
        buf[8] = Uint8Array(n);
        return buf;
    }

    public static long[] packUint256(String num) {
        BigInteger b = new BigInteger(num);
        String n = b.toString(16);
        char[] hexNumberArr = n.toCharArray();
        int hexLength = n.length();
        int zeroLength = 64 - hexLength;
        char[] arr64 = new char[64];
        for (int i = 0; i < arr64.length; i++) {
            if (i < zeroLength)
                arr64[i] = '0';
            else
                arr64[i] = hexNumberArr[i - zeroLength];
        }
        long[] arr32 = new long[32];
        int j = 0;
        for (int i = 0; i < arr64.length; i++) {
            if (i % 2 == 0) {
                arr32[j] = Long.parseLong(String.valueOf(arr64[i]) + String.valueOf(String.valueOf(arr64[i + 1])), 16);
                j++;
            }
        }
        return packBin16(arr32);
    }


    public static long[] packBin16(long[] value) {
        int byteLen = value.length;
        int len = byteLen + 3;
        long[] bytes = new long[len];
        bytes[0] = Uint8Array(BIN16);
        bytes[1] = Uint8Array(byteLen >> 8);
        bytes[2] = Uint8Array(byteLen);
        for (int i = 0; i < byteLen; i++) {
            bytes[i + 3] = value[i];
        }
        return bytes;
    }

    public static long[] packStr16(String str) {
        int len = str.length();
        int byteLen = len + 3;
        long[] bytes = new long[byteLen];
        bytes[0] = Uint8Array(STR16);
        bytes[1] = (len >> 8);
        bytes[2] = (len);
        for (int i = 0; i < len; i++) {
            bytes[i + 3] = str.getBytes()[i];
        }
        return bytes;
    }

    public static long[] packArraySize(int length) {
        long[] size = new long[3];
        size[0] = Uint8Array(ARRAY16);
        size[1] = Uint8Array((length >> 8));
        size[2] = Uint8Array((length));
        return size;
    }


}
