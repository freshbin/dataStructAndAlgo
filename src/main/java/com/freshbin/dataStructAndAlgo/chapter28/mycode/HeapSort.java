package com.freshbin.dataStructAndAlgo.chapter28.mycode;

/**
 * 堆排序
 *
 * 步骤：1、建堆
 *       2、堆化
 *       3、交换堆顶与堆尾元素
 *
 * @author freshbin
 * @date 2020/4/22 9:32
 */
public class HeapSort {

    public static void sort(int[] arr) {
        buildHeap(arr);
        int size = arr.length - 1;
        while(size > 0) {
            swap(arr, 0, size);
            size--;
            heapify(arr, 0, size);
        }
    }

    /**
     * 建堆
     * @param arr
     */
    public static void buildHeap(int[] arr) {
        int size = arr.length - 1;
        if(size < 0) {
            return;
        }

        for(int i = size / 2; i >= 0; i--) {
            heapify(arr, i, size);
        }
    }

    /**
     * 从上往下堆化
     *
     * @param arr
     * @param i
     * @param size
     */
    public static void heapify(int[] arr, int i, int size) {
        while(true) {
            int maxPos = i;
            if(2*i+1 <= size && arr[i] < arr[2*i+1]) maxPos = 2*i+1;
            if(2*i+2 <= size && arr[maxPos] < arr[2*i+2]) maxPos = 2*i+2;
            if(maxPos == i) break;
            swap(arr, i, maxPos);
            i = maxPos;
        }
    }

    /**
     * 交换
     * @param arr
     * @param i
     * @param maxPos
     */
    public static void swap(int[] arr, int i,  int maxPos) {
        int temp = arr[i];
        arr[i] = arr[maxPos];
        arr[maxPos] = temp;
    }

    public static void main(String[] arg) {
        int[] a = new int[]{5,2,3,1,4};
        System.out.println("原数组：");
        display(a);

        System.out.println("排序后的数组：");
        sort(a);
        display(a);
    }

    public static void display(int[] a) {
        for(int value : a) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}
