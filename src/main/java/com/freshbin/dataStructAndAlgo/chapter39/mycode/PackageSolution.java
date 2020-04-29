package com.freshbin.dataStructAndAlgo.chapter39.mycode;

import java.util.HashMap;

/**
 * @author freshbin
 * @date 2020/4/25 12:02
 */
public class PackageSolution {

    public HashMap<String, Integer> cacheMap;
    public int canPackageWeight;
    public int packageCurrentWeight;

    public PackageSolution(int canPackageWeight) {
        cacheMap = new HashMap<>();
        this.canPackageWeight = canPackageWeight;
        this.packageCurrentWeight = 0;
    }

    /**
     * @param currentIndex
     * @param currentWeight
     * @param items
     */
    public void knapsackDigui(int currentIndex, int currentWeight, int[] items) {
       if(currentIndex >= items.length || currentIndex >= canPackageWeight) {
           if(currentWeight <= canPackageWeight) {
               packageCurrentWeight = Math.max(currentWeight, packageCurrentWeight);
               return;
           }
       }

       String key = currentIndex + "-" + currentWeight;
       if(cacheMap.containsKey(key)) {
           return;
       }
       cacheMap.put(key, 1);

        knapsackDigui(currentIndex+1, currentWeight, items);

       if(currentWeight + items[currentIndex] <= canPackageWeight) {
           knapsackDigui(currentIndex+1, currentWeight+items[currentIndex], items);
       }
    }

    public static void main(String[] arg) {
        int[] a = {2, 2, 4, 6, 3};
        PackageSolution packageSolution = new PackageSolution(9);
        packageSolution.knapsackDigui(0, 0, a);
        System.out.println(packageSolution.packageCurrentWeight);

        System.out.println(packageSolution.knapsack(a));
        System.out.println(packageSolution.knapsack02(a));

        int[] values = {10,15,2,1,20};
        System.out.println(packageSolution.knapsack03(a, values));
    }

    public int knapsack(int[] items) {
        boolean[][] status = new boolean[items.length][canPackageWeight+1];
        status[0][0] = true;
        if(items[0] <= canPackageWeight) {
            status[0][1] = true;
        }

        for(int i = 1; i < items.length; i++) {
            for(int j = 0; j <= canPackageWeight; j++) {
                if(status[i-1][j]) {
                    // 不选
                    status[i][j] = status[i-1][j];
                }
            }

            for(int j = 0; j <= canPackageWeight-items[i]; j++) {
                if(status[i-1][j]) {
                    status[i][j + items[i]] = status[i-1][j];
                }
            }
        }

        for(int i = canPackageWeight; i >=0; i--) {
            if(status[items.length-1][i]) {
                return i;
            }
        }

        return 0;
    }

    public int knapsack02(int[] items) {
        boolean[] status = new boolean[canPackageWeight+1];
        status[0] = true;
        if(items[0] <= canPackageWeight) {
            status[items[0]] = true;
        }

        for(int i = 1; i < items.length; i++) {
            for(int j = canPackageWeight-items[i]; j >= 0; j--) {
                if(status[j]) {
                    status[j + items[i]] = true;
                }
            }
        }

        for(int i = canPackageWeight; i >=0; i--) {
            if(status[i]) {
                return i;
            }
        }

        return 0;
    }

    public int knapsack03(int[] items, int[] values) {
        int maxValue = 0;
        int[][] status = new int[items.length][canPackageWeight+1];
        for(int i = 0; i < items.length; i++) {
            for(int j = 0; j < canPackageWeight+1; j++) {
                status[i][j] = -1;
            }
        }

        status[0][0] = 0;
        if(items[0] <= canPackageWeight) {
            status[0][items[0]] = values[0];
        }

        for(int i = 1; i < items.length; i++) {
            for(int j = 0; j <= canPackageWeight; j++) {
                if(status[i - 1][j] >= 0) {
                    status[i][j] = status[i-1][j];
                }
            }

            for(int j = 0; j <= canPackageWeight - items[i]; j++) {
                if(status[i-1][j] >= 0) {
                    int v = status[i-1][j] + values[i];
                    if(v > status[i][j+items[i]]) {
                        status[i][j+items[i]] = v;
                    }
                }
            }
        }

        for(int z = canPackageWeight; z >= 0; z--) {
            if(status[items.length-1][z] > maxValue) {
                maxValue = status[items.length-1][z];
            }
        }
        return maxValue;
    }
}
