package com.freshbin.dataStructAndAlgo.chapter11.mycode;

/**
 * 线性排序
 * @author freshbin
 * @date 2020/4/16 18:16
 */
public class MyLineSorts {
    /**
     * 桶排序
     * 1、首先判断需要多少个桶；
     * 那么就要确定待排序数组的范围，假设最大值为maxValue，最小值为minValue，
     * 假设设定每个桶的范围是10，那么就需要,(maxValue-minValue)/10+1个桶，
     * 2、将待排数组分别放入相应的桶中；
     * 3、对每个桶使用快排排好序；
     * 4、将每个桶中的数据按照顺序放入原数组中。
     *
     * 遇到问题：每个桶的大小如何确定，上面假定10大小为10，那么多出来的空位就会用0补充
     * 如果用0补充，那么快排之后0就会都在前面.
     * 解决：一个桶装好数据之后，把桶的容量再修改一下，修改成实际装的数据容量。
     * 改进解决：在第2步时，用一个数组记录每个桶的数据量，如果为0那么就不进行快排，
     * 如果不为0，表示有数据，那么传入快排的数据范围为0到（该桶的数据量大小-1）
     *
     * 总结：1、一开始将原数组分到桶里的这步，我是先遍历每个桶，其实是可以直接遍历原数组
     * 然后用数学知识，判断这个数是属于哪个桶，那这样就可以知道当前桶索引，
     * 而且桶需要扩容的话，直接定位到该桶索引进行扩容即可。
     * 2、将桶快排这一步，需要在把原数组分到桶里这一步，用一个数组记录每个桶实际数据量
     * 之后快排的时候就可以知道start和end了，快排完毕即可将桶元素搬回原数组。
     *
     * @param arr
     */
    public static void bucketSort(int[] arr, int oneBucketSize) {
        int bucketNum = 0;
//        int oneBucketSize = 10;
        int bucketRange = 10;
        int maxValue = arr[0];
        int minValue = arr[0];
        for(int value : arr) {
            if(value > maxValue) {
                maxValue = value;
            } else if(value < minValue) {
                minValue = value;
            }
        }
        ///1、桶的个数
        bucketNum = (maxValue - minValue) / bucketRange + 1;

        /*
        // 2、将数组数据放入对应的桶中
        int[][] bucketArrs = new int[bucketNum][];
        int bucketIndex = 0;
        while(bucketIndex < bucketNum) {
            // 设置一个桶能装多少数据,如果装不下，后面还要扩容
            int[] bucketArr = new int[oneBucketSize];
            int oneBucketIndex = 0;
            for(int i = 0; i < arr.length; i++) {
                if(arr[i] >= minValue + bucketIndex*bucketRange && arr[i] < minValue + (bucketIndex+1)*bucketRange) {
                    if(oneBucketIndex >= oneBucketSize) {
                        // 桶扩容
                        bucketArr = dilatationBucketSize(bucketArr, oneBucketSize);
                    }
                    bucketArr[oneBucketIndex++] = arr[i];
                }

            }
            // 将桶的容量修改为实际容量
            bucketArr = modifyBucketSize(bucketArr, oneBucketIndex);


            // 3、对每个桶使用快排,这里直接用之前写过的快排方法了
            MySimpleSorts.quickSort(bucketArr, 0, bucketArr.length-1);

            bucketArrs[bucketIndex++] = bucketArr;
        }

        // 4、将每个桶的数组都取出来放入原数组
        int arrIndex = 0;
        for(int i = 0; i < bucketNum; i++) {
            int[] bucketArr = bucketArrs[i];

            for(int j = 0; j < bucketArr.length; j++) {
                if(bucketArr[j] == 0) {
                    break;
                }
                arr[arrIndex++] = bucketArr[j];
            }
        }
        */

        // 2、将数组数据放入对应的桶中
        int[][] bucketArrs = new int[bucketNum][oneBucketSize];
        int[] oneBucketRealSize = new int[bucketNum];
        for(int i = 0; i < arr.length; i++) {
            // 判断该数是放入哪个桶里，数学知识
            int currentBucketIndex = (arr[i]-minValue)/oneBucketSize;
            if(oneBucketRealSize[currentBucketIndex] == bucketArrs[currentBucketIndex].length) {
                // 扩容该桶的容量
                ensureBucketCapacity(bucketArrs, currentBucketIndex);
            }
            bucketArrs[currentBucketIndex][oneBucketRealSize[currentBucketIndex]++] = arr[i];
        }

        // 3、遍历每个桶使用快排，
        // 4、并把快排后的数据直接写回原数组
        int arrIndex = 0;
        for(int i = 0; i < bucketNum; i++) {
            if(oneBucketRealSize[i] == 0) {
                continue;
            }
            // 对有数据的桶进行快排
            MySimpleSorts.quickSort(bucketArrs[i], 0, oneBucketRealSize[i] - 1);
            for(int j = 0; j < oneBucketRealSize[i]; j++) {
                arr[arrIndex++] = bucketArrs[i][j];
            }
        }
    }

    /**
     * 桶扩容
     * @param bucketArrs
     * @param currentBucketIndex
     */
    private static void ensureBucketCapacity(int[][] bucketArrs, int currentBucketIndex) {
        int[] tempArr = new int[bucketArrs[currentBucketIndex].length*2];
        for(int i = 0; i < bucketArrs[currentBucketIndex].length; i++) {
            tempArr[i] = bucketArrs[currentBucketIndex][i];
        }
        bucketArrs[currentBucketIndex] = tempArr;
    }

    private static int[] modifyBucketSize(int[] bucketArr, int oneBucketIndex) {
        int[] realBucketSizeArr = new int[oneBucketIndex];
        for(int i = 0; i < oneBucketIndex; i++) {
            realBucketSizeArr[i] = bucketArr[i];
        }
        return realBucketSizeArr;
    }

    /**
     * 桶扩容
     * @param bucketArr
     * @param oneBucketSize
     * @return
     */
    private static int[] dilatationBucketSize(int[] bucketArr, int oneBucketSize) {
        oneBucketSize = oneBucketSize * 2;
        int[] tempArr = new int[oneBucketSize];
        for(int i = 0; i < bucketArr.length; i++) {
            tempArr[i] = bucketArr[i];
        }
        return tempArr;
    }

    /**
     * 计数排序
     *
     * 1、判断待排序数据的范围，比如从0-100，那么就需要101个桶，每个桶代表装一样的数；
     * 2、将一样数据的个数记录在桶中；比如数据为0的有6个，那么第0个桶就记录6；
     * 3、遍历所有的桶，将桶里装的数据个数一直往后叠加；
     * 比如第0个桶记录6，第1个桶记录2，第2个桶记录0，第3个桶记录5，
     * 那么经过遍历叠加之后，第0个桶记录6，第1个桶记录为6+2=8，第2个桶记录为6+2+0=8，第3个桶记录为6+2+0+5=13
     * 4、开辟一个新数组空间，用来保存有序数组；
     * 从后往前遍历原数组，将其值与桶的值对应起来，比如该数为3，那么就取桶[3]里的值，取到为13，
     * 那么就把该数放入有序数组下标值为13的位置，同时桶[3]的值-1；
     * 5、将有序数组的数据全部搬回原数组，那么原数组就是排好序后的数组了
     *
     * @param arr
     */
    public static void countingSort(int[] arr) {
        // 1、确定需要多少个桶
        int maxValue = arr[0];
        int minValue = arr[1];
        for(int value : arr) {
           if(value > maxValue) {
               maxValue = value;
           } else if(value < minValue) {
               minValue = value;
           }
        }
        // 桶的个数
        int bucketNum = maxValue + 1;
        int[] bucketArrs = new int[bucketNum];

        /*
        // 2、记录每个桶里数据的个数
        int bucketIndex = 0;
        for(int i = 0; i < bucketNum; i++) {
            int[] bucketArr = new int[1];
            int count = 0;
            for(int j = 0; j < arr.length; j++) {
                if(arr[j] == i) {
                    count++;
                }
            }
            bucketArrs[bucketIndex++] = count;
        }
        */

        // 2、改写
        for(int i = 0; i < arr.length; i++) {
            bucketArrs[arr[i]]++;
        }

        // 3、叠加桶里数据个数
        int sum = bucketArrs[0];
        for(int i = 1; i < bucketNum; i++) {
            bucketArrs[i] = bucketArrs[i-1] + bucketArrs[i];
        }

        // 4、保存有序数组,为了保证稳定性，需要从后往前遍历原数组
        int[] sortArr = new int[arr.length];
        for(int i = arr.length - 1; i >= 0; i--) {
            int index = bucketArrs[arr[i]] - 1;
            sortArr[index] = arr[i];
            bucketArrs[arr[i]] = index;
        }

        // 5、将有序数组搬回原数组
        for(int i = 0; i < arr.length; i++) {
            arr[i] = sortArr[i];
        }
    }

    /**
     * 基数排序
     * 针对比较大的一组数进行排序，比如手机号码这类。
     * 基数排序是把这组数从最低位开始比较，一直比较到最高位，所以低位排序之后需要保证算法的稳定性，因此可以使用计数排序。
     * 思路：首先获取该组数据的最大值，从最低位开始遍历该数组，即从个位开始，到最高位
     * 获取到该组数据同样的位数数据之后，开始使用计数排序比较即可，每次比较完都写回原数组
     *
     * @param arr
     */
    public static void radixSort(int[] arr) {
       // 获取最大值
       int maxValue = getMaxValue(arr);
       // 从最低位往高位进行比较
        for(int exp = 1; maxValue / exp > 0; exp*=10) {
            countingSort(arr, exp);
        }
    }

    /**
     * 计数排序
     * 遇到卡壳的地方：1、需要多少个桶？2、如何确定计数桶的下标值
     * 解决：1、因为是从0-9比较，所以可以假定是10个桶
     * 2、使用a[i]/exp % 10，这个表达式算出来的值就是桶的下标值，
     * 比如数据a[0]为13，exp此时为1，那么结果就为3，如果exp为10，那么结果就是1
     *
     * @param arr
     * @param exp
     */
    private static void countingSort(int[] arr, int exp) {
        if(arr.length <= 1) {
            return;
        }

        // 将数据个数记录在桶中
        int[] bucketArrs = new int[10];
        for(int i = 0; i < arr.length; i++) {
            bucketArrs[arr[i] / exp % 10]++;
        }

        // 叠加桶中记录的个数
        for(int i = 1; i < bucketArrs.length; i++) {
            bucketArrs[i] += bucketArrs[i-1];
        }

        // 使用一个有序数组保存数据,从后往前获取原数组数据，保持稳定性
        int[] sortArr = new int[arr.length];
        for(int i = arr.length - 1; i >= 0; i--) {
            sortArr[--bucketArrs[arr[i] / exp % 10]] = arr[i];
        }

        // 将有序数组写回原数组
        for(int i = 0; i < arr.length; i++) {
            arr[i] = sortArr[i];
        }
    }

    private static int getMaxValue(int[] arr) {
        int maxValue = arr[0];
        for(int value : arr) {
            if(value > maxValue) {
                maxValue = value;
            }
        }
        return maxValue;
    }

    public static void main(String[] arg) {
        int size = 10;
        int[] arr = null;


        System.out.println("桶排序前：");
        arr = initArray(size);
        display(arr);
        bucketSort(arr, 10);
        System.out.println("桶排序后：");
        display(arr);

        System.out.println("===========================");

        System.out.println("计数排序前：");
        arr = initArray(size);
        display(arr);
        countingSort(arr);
        System.out.println("计数排序后：");
        display(arr);
    Math.sqrt(15);
        System.out.println("===========================");
        System.out.println("基数排序前：");
        arr = initArray(size);
        display(arr);
        countingSort(arr);
        System.out.println("基数排序后：");
        display(arr);
    }

    public static int[] initArray(int size) {
        int[] arr = new int[size];
        for(int i = 0; i < size; i++) {
            arr[i] = (int)(Math.random()*100);
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