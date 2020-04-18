package com.freshbin.dataStructAndAlgo.utils;

/**
 * @author freshbin
 * @date 2020/4/17 17:21
 */
public class MyArrayUtil {
    public static int[] initArray(int size, int range) {
        int[] arr = new int[size];
        for(int i = 0; i < size; i++) {
            arr[i] = (int)(Math.random()*range);
        }
        return arr;
    }

    public static void display(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
