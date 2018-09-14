package com.bottos.botc.sdk.utils;

import java.util.Arrays;

/**
 * Created by xionglh on 2018/9/11
 */
public class ArraysUtils {

    public static <T> T[] arraycopy(T[] first, T[]... rest) {
        int totalLength = first.length;
        for (T[] array : rest) {
            totalLength += array.length;
        }
        T[] result = Arrays.copyOf(first, totalLength);

        int offset = first.length;
        for (T[] array : rest) {
            System.arraycopy(array, 0, result, offset, array.length);
            offset += array.length;
        }
        return result;
    }

    public static  long[] arrayCopylong(long[] first, long[]... rest) {
        int totalLength = first.length;
        for (long[] array : rest) {
            totalLength += array.length;
        }
        long[] result = Arrays.copyOf(first, totalLength);

        int offset = first.length;
        for (long[] array : rest) {
            System.arraycopy(array, 0, result, offset, array.length);
            offset += array.length;
        }
        return result;
    }
}
