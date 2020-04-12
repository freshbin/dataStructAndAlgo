package com.freshbin.dataStructAndAlgo.chapter06.Array.LRU;

/**
 * @author freshbin
 * @date 2020/4/12 9:23
 * 数组实现LRU缓存淘汰策略
 */
public class MyArrayLRU {
    private volatile static MyArrayLRU myArrayLRU = null;
    private Integer maxSize = 3;
    private String[] cacheArr = new String[3];
    private Integer currentCount = 0;

    private MyArrayLRU() { }

    public static synchronized MyArrayLRU getMyArrayLRU() {
        if(myArrayLRU == null) {
            synchronized (MyArrayLRU.class) {
                if(myArrayLRU == null) {
                    myArrayLRU = new MyArrayLRU();
                }
            }
        }

        return myArrayLRU;
    }

    /**
     * 0、判断缓存是否为空，如果为空，则直接插入到索引为0的位置
     * 1、先查询该值是否在缓存中，如果存在，则遍历原缓存数组从索引0开始到该值存在的位置，把数组的每一项都后移一位，并把该值写入索引为0的位置
     * 2、如果该值不存在缓存中，则判断缓存是否满了，
     * 如果没满，则遍历原缓存数组，把数组的每一项都后移一位，并把该值写入索引为0的位置,
     * 如果满了，那么就把数组前size-1项往后移一位（size为数组大小），再把该值写入索引为0的位置。
     * @param token
     * @return
     */
    public String useCache(String token) {
        if(cacheArr[0] == null) {
            cacheArr[0] = token;
            currentCount++;
            return token;
        }
        int index = findIndexInCacheArray(token);
        if(index == 0) {
            // 如果该值已经是第0个索引，那么也不用继续放到最前面。
            return token;
        }
        if(index != -1) {
            forwardArrayOneStep(0, index);
            cacheArr[0] = token;
            return token;
        }

        // 因为该值不在缓存中，所以缓存数量+1
        if(currentCount < maxSize) {
            currentCount++;
        }

        // 该值不在缓存中的情况
        if(currentCount < maxSize) {
            // 缓存没满
            forwardArrayOneStep(0, currentCount-1);
            cacheArr[0] = token;
        } else {
            // 缓存满了
            forwardArrayOneStep(0, maxSize-1);
            cacheArr[0] = token;
        }
        return token;
    }

    private void forwardArrayOneStep(int start, int end) {
        if(start >= end || end <= 0) {
            return;
        }
        for(; end > start; end--) {
            // 这里需要从后往前遍历，否则前面的会把后面的值覆盖了
            // 把前面的值放到后一位
            cacheArr[end] = cacheArr[end-1];
        }
    }

    /**
     * 返回-1表示没找到，否则返回索引值
     * @param token
     * @return
     */
    private int findIndexInCacheArray(String token) {
        for(int i = 0; i < cacheArr.length; i++) {
            if(cacheArr[i] == token) {
                return i;
            }
        }

        return -1;
    }

    public String[] getCacheArr() {
        return this.cacheArr;
    }
}
