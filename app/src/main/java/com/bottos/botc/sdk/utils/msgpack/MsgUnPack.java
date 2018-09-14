package com.bottos.botc.sdk.utils.msgpack;

/**
 * Created by xionglh on 2018/9/10
 */
public final class MsgUnPack {

    public static long unPackUint8(long[] data) {
        return data[1];
    }

    public static long unPackUint16(long[] data) {
        long data1 = data[1] << 8;
        long data2 = data[2];
        return data1 | data2;
    }

    public static long unPackUint32(long[] data) {
        long data1 = data[1] << 24;
        long data2 = data[2] << 16;
        long data3 = data[3] << 8;
        long data4 = data[4];
        return data1 | data2 | data3 | data4;
    }

    public static long unPackUint64(long[] data) {
        return data[1] << 56 | data[2] << 48 | data[3] << 40 | data[4] << 32 | data[5] << 24 | data[6] << 16 | data[7] << 8 | data[8];
    }

    public static long[] unPackBin16(long[] data) {
        long data1 = data[1] << 8;
        long data2 = data[2];
        int length = (int) (data1 + data2);
        long[] bytes = new long[length - 3];
        for (int i = 3; i < length; i++) {
            bytes[i - 3] = data[i];
        }
        return bytes;
    }


    public static String unPackStr16(long[] data) {
        long data1 = data[1] << 8;
        long data2 = data[2];
        int length = (int) (data1 + data2);
        byte[] bytes = new byte[length];
        for (int i = 3; i < length + 3; i++) {
            byte by = (byte) data[i];
            bytes[i - 3] = by;
        }
        return new String(bytes);
    }


    public static int unPackArraySize(long[] data) {
        long data1 = (data[1] << 8);
        long data2 = data[2];
        return (int)(data1|data2);
    }

}
