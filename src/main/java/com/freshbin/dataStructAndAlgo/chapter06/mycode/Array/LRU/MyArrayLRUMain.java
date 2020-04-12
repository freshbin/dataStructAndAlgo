package com.freshbin.dataStructAndAlgo.chapter06.mycode.Array.LRU;

/**
 * @author freshbin
 * @date 2020/4/12 10:10
 */
public class MyArrayLRUMain {
    public static void main(String[] arg) {
        String token01 = "token01";
        String token02 = "token02";
        String token03 = "token03";
        String token04 = "token04";

        MyArrayLRU myArrayLRU = MyArrayLRU.getMyArrayLRU();
        myArrayLRU.useCache(token01);
        myArrayLRU.useCache(token01);

        String[] cacheArr = myArrayLRU.getCacheArr();
        System.out.print("缓存值：");
        for(int i = 0; i < cacheArr.length; i++) {
            if(cacheArr[i] != null) {
                System.out.print(cacheArr[i] + " ");
            }
        }

        System.out.println();

        System.out.print("缓存值：");
        myArrayLRU.useCache(token02);
        myArrayLRU.useCache(token03);
        myArrayLRU.useCache(token04);
        myArrayLRU.useCache(token02);
        for(int i = 0; i < cacheArr.length; i++) {
            if(cacheArr[i] != null) {
                System.out.print(cacheArr[i] + " ");
            }
        }
    }
}
