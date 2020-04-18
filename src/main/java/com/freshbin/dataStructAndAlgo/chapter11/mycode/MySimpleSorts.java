package com.freshbin.dataStructAndAlgo.chapter11.mycode;

/**
 * 简单排序算法
 * @author freshbin
 * @date 2020/4/15 14:28
 */
public class MySimpleSorts {
    /**
     * 冒泡排序
     * 遍历数组，相邻两个数两两比较
     * @param arr
     */
    public static void bubbleSort(int[] arr) {
        for(int i = 0; i < arr.length - 1; i++) {
            for(int j = 0; j < arr.length - i - 1; j++) {
                if(arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 插入排序
     * 将待排序的数组分为左边已排序数组，和右边未排序数组，一开始已排序数组只有a[0]一个元素
     * 遍历未排序的数组（也即原数组不包括第一个元素），从索引1开始，与左边已经排好序的数组比较，
     * 如果左边数组大于该数，那么就继续遍历左边数组
     * 直到找到比该数小的数，然后把该数插入此位置后面。
     *
     * @param arr
     */
    public static void insertSort(int[] arr) {
        for(int i = 1; i < arr.length; i++) {
            int value = arr[i];
            int j = i - 1;
            for(; j >= 0; j--) {
                if(arr[j] > value) {
                    arr[j+1] = arr[j];
                } else {
                    break;
                }
            }
            arr[j+1] = value;
        }
    }

    /**
     * 选择排序
     * 将原数组分为有序数组和未排序数组，当然一开始有序数组为空
     * 循环未排序数组（即原数组）将最小的数与未排序数组的第一个元素交换
     *
     * @param arr
     */
    public static void chooseSort(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            int miniIndex = i;
            for(int j = i; j < arr.length; j++) {
                if(arr[j] <= arr[miniIndex]) {
                    miniIndex = j;
                }
            }

            int miniValue = arr[miniIndex];
            arr[miniIndex] = arr[i];
            arr[i] = miniValue;
        }
    }

    /**
     * 归并排序
     *
     *
     * @param arr
     */
    public static void mergeSort(int[] arr, int start, int end) {
        if(start >= end) {
            return;
        }

        int middle = (start + end ) / 2;
        mergeSort(arr, start, middle);
        mergeSort(arr, middle + 1, end);
        mergeArr(arr, start, middle, end);
    }

    private static void mergeArr(int[] arr, int start, int middle, int end) {
        int i = start, j = middle+1, k = 0;
        int[] tempArr = new int[arr.length];

        while(i <= middle && j <= end) {
            if(arr[i] <= arr[j]) {
                tempArr[k++] = arr[i++];
            } else {
                tempArr[k++] = arr[j++];
            }
        }

        while (i <= middle) {
            tempArr[k++] = arr[i++];
        }
        while (j <= end) {
            tempArr[k++] = arr[j++];
        }

        k = 0;
        while(start <= end) {
            arr[start++] = tempArr[k++];
        }

    }

    /**
     * 快速排序
     * 思路：将数组分为左右两部分，左边的比右边的小，继续将左边的分成左右两部分，左边的依然比右边的小，
     * 右边的也分为左右两部分，左边的也比右边的小，一直分解下去，直到左边的头指针大于等于右边的头指针，
     * 可见，这个问题可以分解成多个子问题，最底层的子问题有终止条件，所以可以用递归实现
     * qucikSort(0-size) = qucikSort(0-(size/2-1)) + quickSort((size/2+1), size);
     * qucikSort方法中包含一个分区方法。
     * 分区方法思路如下：
     * 将末尾的数定位哨兵，遍历数组
     * 从数组左边0索引开始往右边走
     * 当左边的数比右边大，那么就交换两个数，
     * 停止左边往右边走，右边的数据开始往左边走 ，
     * 当右边的数比左边当前的数小，那么就交换两个数，
     * 当左边指针不小于右边指针时，退出，这时候中间数的左边是比中间数小，右边的比中间数大，
     * 返回该中间数的索引值。
     *
     * @param arr
     */
    public static void quickSort(int[] arr, int start, int end) {
        if(start >= end) {
            return;
        }

        int middele = partition(arr, start, end);
        quickSort(arr, 0, middele-1);
        quickSort(arr, middele+1, end);
    }

    private static int partition(int[] arr, int start, int end) {
        int sentryValue = arr[end];
        while(start < end) {
            while(arr[start] <= sentryValue && start < end) {
                start++;
            }
            arr[end] = arr[start];
            arr[start] = sentryValue;
            while(arr[end] >= sentryValue && end > start) {
                end--;
            }
            arr[start] = arr[end];
            arr[end] = sentryValue;
        }
        return start;
    }


    public static void main(String[] arg) {
        int size = 10;
        int[] arr = null;

        /*
        System.out.println("冒泡排序前：");
        arr = initArray(size);
        display(arr);
        bubbleSort(arr);
        System.out.println("冒泡排序后：");
        display(arr);

        System.out.println("===========================");

        System.out.println("插入排序前：");
        arr = initArray(size);
        display(arr);
        insertSort(arr);
        System.out.println("插入排序后：");
        display(arr);

        System.out.println("===========================");

        System.out.println("选择排序前：");
        arr = initArray(size);
        display(arr);
        chooseSort(arr);
        System.out.println("选择排序后：");
        display(arr);

        System.out.println("===========================");

        System.out.println("快速与排序前：");
        arr = initArray(size);
        display(arr);
        quickSort(arr, 0, arr.length-1);
        System.out.println("快速与排序后：");
        display(arr);
        */

        System.out.println("===========================");

        System.out.println("递归排序前：");
        arr = initArray(size);
        display(arr);
        mergeSort(arr, 0, arr.length-1);
        System.out.println("递归排序后：");
        display(arr);
    }

    private static int[] initArray(int size) {
        int[] arr = new int[size];
        for(int i = 0; i < size; i++) {
            arr[i] = (int)(Math.random()*100);
        }
        return arr;
    }

    private static void display(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
