package com.freshbin.dataStructAndAlgo.chapter15.mycode;

import com.freshbin.dataStructAndAlgo.chapter11.mycode.MySimpleSorts;
import com.freshbin.dataStructAndAlgo.utils.MyArrayUtil;

/**
 * 二分查找
 * @author freshbin
 * @date 2020/4/17 17:14
 */
public class BinarySearch {
    public static int simpleBSearch(int[] arr, int targetValue) {
        if(arr.length < 1) {
            return -1;
        }

        int low = 0, high = arr.length - 1, middle = low + (high - low)/2;
        while(low <= high) {
            if(arr[middle] == targetValue) {
                return middle;
            } else if(arr[middle] < targetValue) {
                low = middle + 1;
            } else {
                high = middle - 1;
            }
            middle = low + (high - low)/2;
        }
        return -1;
    }

    public static int diguiBSearch(int[] arr, int targetValue) {
        if(arr.length < 1) {
            return -1;
        }

        int index = diguiBSearch(arr, 0, arr.length/2, targetValue);

        return index;
    }

    private static int diguiBSearch(int[] arr, int low, int high, int targetValue) {
        if(low > high) {
            return -1;
        }
        int middle = low + (high-low) / 2;
        if(arr[middle] == targetValue) {
            return middle;
        } else if(arr[middle] > targetValue) {
            diguiBSearch(arr, low, middle-1, targetValue);
        } else {
            diguiBSearch(arr, middle+1, high, targetValue);
        }

        return -1;
    }

    /**
     * 查找一个数的平方根，保留6位小数
     *
     * 思路：首先用这个数的一半的平方与该值比较大小
     * 如果相等，那么直接返回，否则看是大了还是小了，大了就取low到middle-1的一半，否则取middle+1到high的一半，循环。
     * @param n
     * @return
     */
    public static double getNumSqrt(double n) {
        double low = 0;
        double high = n;
        double middle = high / 2;

        while(low <= high) {
            double result = middle*middle;
            if(result == n) {
                return middle;
            } else if(result > n) {
                high = middle;
            } else {
                low = middle;
            }
            middle = low + (high - low) / 2;

            // 判断是否6位小数了
            String pointNum = String.valueOf(middle);
            int pointIndex = pointNum.indexOf(".");
            if(pointIndex > 0) {
                pointNum = pointNum.substring(pointIndex+1, pointNum.length());
                if(pointNum.length() > 5) {
                    return middle;
                }
            }
        }
        return 0;
    }

    /**
     * 查找第一个值等于给定值的元素
     * @param arr
     * @param targetValue
     * @return
     */
    public static int searchFirstEqualNum(int[] arr, int targetValue) {
        if(arr.length < 1) {
            return -1;
        }
        int low = 0;
        int high = arr.length-1;

        while(low <= high) {
            int middle = low + ((high - low) >> 1);
            if(arr[middle] == targetValue) {
                // 判断该值是否是第一个元素，或者该值的上一个元素是否等于目标值，如果不等于，说明该值是第一个等于目标值的元素
                if(middle == 0 || arr[middle-1] != targetValue) {
                    return middle;
                }
                // 否则,前面还有等于目标值的数
                high = middle - 1;
            } else if(arr[middle] > targetValue) {
                // 目标值在前半部分
                high = middle - 1;
            } else {
                // 目标值在后半部分
                low = middle + 1;
            }
        }
        return -1;
    }

    /**
     * 查找最后一个值等于给定值的元素
     * @param arr
     * @param targetValue
     * @return
     */
    public static int searchLastEqualNum(int[] arr, int targetValue) {
        if(arr.length < 1) {
            return -1;
        }
        int low = 0;
        int high = arr.length-1;

        while(low <= high) {
            int middle = low + ((high - low) >> 1);
            if(arr[middle] == targetValue) {
                // 判断该值是否是最后一个元素，或者该值的下一个元素是否等于目标值，如果不等于，说明该值是最后一个等于目标值的元素
                if(middle == arr.length-1 || arr[middle+1] != targetValue) {
                    return middle;
                }
                // 否则,后面还有等于目标值的数
                low = middle + 1;
            } else if(arr[middle] > targetValue) {
                // 目标值在前半部分
                high = middle - 1;
            } else {
                // 目标值在后半部分
                low = middle + 1;
            }
        }
        return -1;
    }

    /**
     * 查找第一个值大于等于给定值的元素
     * @param arr
     * @param targetValue
     * @return
     */
    public static int searchFirstGreaterEqualNum(int[] arr, int targetValue) {
        if(arr.length < 1) {
            return -1;
        }
        int low = 0;
        int high = arr.length-1;

        while(low <= high) {
            int middle = low + ((high - low) >> 1);
            if(arr[middle] >= targetValue) {
                // 判断该值是否是第一个元素，或者该值的上一个元素是否小于目标值，如果小于，说明该值是第一个大于等于目标值的元素
                if(middle == 0 || arr[middle-1] < targetValue) {
                    return middle;
                }
                // 否则,前面还有大于等于目标值的数
                high = middle - 1;
            } else {
                // 目标值在后半部分
                low = middle + 1;
            }
        }
        return -1;
    }

    /**
     * 查找最后一个值小于等于给定值的元素
     * @param arr
     * @param targetValue
     * @return
     */
    public static int searchLastLessEqualNum(int[] arr, int targetValue) {
        if(arr.length < 1) {
            return -1;
        }
        int low = 0;
        int high = arr.length-1;

        while(low <= high) {
            int middle = low + ((high - low) >> 1);
            if(arr[middle] <= targetValue) {
                // 判断该值是否是最后一个元素，或者该值的下一个元素是否大于目标值，如果大于，说明该值是最后一个小于等于目标值的元素
                if(middle == arr.length - 1 || arr[middle+1] > targetValue) {
                    return middle;
                }
                // 否则,后面还有小于等于目标值的数
                low = middle + 1;
            } else {
                // 目标值在前半部分
                high = middle - 1;
            }
        }
        return -1;
    }

    /**
     * leetcode 81：
     * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
     * ( 例如，数组 [0,0,1,2,2,5,6] 可能变为 [2,5,6,0,0,1,2] )。
     * 编写一个函数来判断给定的目标值是否存在于数组中。若存在返回 true，否则返回 false
     *
     * 思路：数组从中间切分，通过low与middle值的比较，可以知道
     * 1、如果low=middle,说明数组有重复数据，可以把low一直往high递增，
     * 直到low>high为止
     * 2、那么如果low<middle,说明左边有序，右边循环；
     * 3、如果low>middle，说明左边循环，右边有序，
     * 我们总是在有序部分里面判断目标值是属于左边部分还是右边部分。
     *
     * @param arr
     * @param targetValue
     * @return
     */
    public static Boolean searchCyclicArr(int[] arr, int targetValue) {
        if(arr.length < 1) {
            return false;
        }
        int low = 0;
        int high = arr.length - 1;
        while(low <= high) {
            int middle = low + ((high-low)>>1);
            if(arr[middle] == targetValue) {
                return true;
            }
            if(arr[low] == arr[middle]) {
                low++;
                continue;
            }

            if(arr[low] < arr[middle]) {
                // 说明左边有序，右边循环
                if(targetValue >= arr[low] && targetValue < arr[middle]) {
                    // 目标值在左部分
                    high = middle - 1;
                } else {
                    // 目标值在右部分
                    low = middle + 1;
                }

            } else {
                // 说明左边循环，右边有序
                if(targetValue > arr[middle] && targetValue <= arr[high]) {
                    // 目标值在右部分
                    low = middle + 1;
                } else {
                    high = middle - 1;
                }
            }
        }
        return false;
    }

    public static void main(String[] arg) {
        int size = 10;
        int range = 10;
        int[] arr = MyArrayUtil.initArray(size, range);
        int targetValue = 6;
//        arr[(int)(Math.random()*size)] = targetValue;
        MySimpleSorts.quickSort(arr, 0, arr.length-1);
        System.out.println("数组：");
        MyArrayUtil.display(arr);
        System.out.println("简单二分查找：目标数在数组的下标值为：" + simpleBSearch(arr, targetValue));
        System.out.println("递归二分查找：目标数在数组的下标值为：" + simpleBSearch(arr, targetValue));

        System.out.println("=======================");

        double sqrtValue = getNumSqrt(7);
        System.out.println("7的平方根为：" + sqrtValue + "，该数平方值为:" + sqrtValue*sqrtValue);

        System.out.println("=======================");

        System.out.println("查找第一个值等于给定值的元素：目标数在数组的下标值为：" + searchFirstEqualNum(arr, targetValue));
        System.out.println("查找最后一个值等于给定值的元素：目标数在数组的下标值为：" + searchLastEqualNum(arr, targetValue));
        System.out.println("查找第一个值大于等于给定值的元素：目标数在数组的下标值为：" + searchFirstGreaterEqualNum(arr, targetValue));
        System.out.println("查找最后一个值小于等于给定值的元素：目标数在数组的下标值为：" + searchLastLessEqualNum(arr, targetValue));

        System.out.println("=======================");
        int[] cyclicArr = new int[]{1};
        targetValue = 0;
        System.out.println("数组：");
        MyArrayUtil.display(cyclicArr);
        System.out.println("目标值" + targetValue + "是否存在于数组中：" + searchCyclicArr(cyclicArr, targetValue));
    }
}
