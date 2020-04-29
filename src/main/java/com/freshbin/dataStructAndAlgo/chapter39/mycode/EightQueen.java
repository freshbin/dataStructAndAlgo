package com.freshbin.dataStructAndAlgo.chapter39.mycode;

import java.util.ArrayList;
import java.util.List;

/**
 * 八皇后问题
 *
 * @date 2020/4/25 8:53
 */
public class EightQueen {

    private int n; // n个皇后
    int[] result;
    int count; // 总的可能摆放结果数

    public EightQueen(int n) {
        this.n = n;
        // n行n列的仿二维数组，数组中的值表示是第n列（从0开始数），比如a[0]=4，就表示第1行第5列已经放了皇后
        // 这里也可以用实际的二维数组，可能如果用二维数组会比较好理解一点，不过用一位数组比较取巧，代码少写一点
        result = new int[n];
        count = 0;
    }

    private void cal8queens() {
        diguiCal(0); // 从第1行开始放皇后
    }

    private void diguiCal(int row) {
        if (row == n) {
            printQueen();
            return;
        }

        // 从第一列往右边判断是否可以放置皇后，可以就直接放了，然后继续下一行，
        // 当下一行不满足放置条件的话，再不断回溯列，再回溯行
        for (int col = 0; col < n; col++) {
            if (canPlace(row, col)) {
                result[row] = col; // 如果满足条件，直接放置在该行该列
                diguiCal(row + 1); // 直接跳去下一行继续走流程
            }
        }

    }

    /**
     * 判断当前行和列是否可以放置皇后
     * 纵向比较，比较该行该列上方的行是否已经存在皇后，再比较该行该列左上角和右上角是否存在皇后
     *
     * @param row
     * @param col
     * @return
     */
    private boolean canPlace(int row, int col) {
        int leftUp = col - 1; // 左上角的列
        int rightUp = col + 1; // 右上角的列

        for (row = row - 1; row >= 0; row--) {
            if (result[row] == col) {
                return false;
            }
            if (leftUp >= 0 && result[row] == leftUp) {
                return false;
            }
            if (rightUp < n && result[row] == rightUp) {
                return false;
            }
            leftUp--;
            rightUp++;
        }
        return true;
    }

    private void printQueen() {
        // 打印结果
        System.out.println("=======第" + ++count + "种结果===========");

        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < n; col++) {
                if (result[row] == col) {
                    System.out.print("Q ");
                } else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] arg) {
        int n = 8;
        EightQueen eightQueen = new EightQueen(n);
//        eightQueen.cal8queens();

        int count = 0;
        List<List<String>> returnList = eightQueen.solveNQueens(n);
        for (List<String> queens : returnList) {
            System.out.println("===========第" + count + "种情况==========");
            for (String queen : queens) {
                System.out.println(queen);
            }
            count++;
            System.out.println();
        }
    }

    private List<String> initQueen(int n) {
        StringBuffer sb = new StringBuffer();
        List<String> queen = new ArrayList<>(n);
        for(int i = 0; i < n; i++) {
            sb.append(".");
        }
        for(int i = 0; i < n; i++) {
            queen.add(sb.toString());
        }
        return queen;
    }

    public int num = 0;

    /**
     * leetcode1489
     *
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> returnList = new ArrayList<>();
        if (n <= 0) {
            return null;
        }

        // 先把第0个添加进去，防止后面get的时候报错
        List<String> queens = initQueen(n);
        returnList.add(0, queens);
        diguiCal2(0, n, returnList);

        // 由于是先假设有符合的情况，初始化数据之后才判断有没有符合的情况，所以需要把最后一次初始化的数据删除
        returnList.remove(num);
        return returnList;
    }

    private void diguiCal2(int row, int n, List<List<String>> returnList) {
        if (row == n) {
            // 如果执行到这步，说明已经找到一个正确的情况
            List<String> queens = initQueen(returnList);
            num++;
            returnList.add(num, queens);
            return;
        }

        for (int col = 0; col < n; col++) {
            if (isOk(row, col, returnList, n)) {
                initQueen(returnList, row, col, n);
                diguiCal2(row + 1, n, returnList);
            }
        }
    }

    private List<String> initQueen(List<List<String>> returnList) {
        List<String> queen = new ArrayList<>();
        for(String s : returnList.get(num)) {
            queen.add(s);
        }
        return queen;
    }

    private void initQueen(List<List<String>> returnList, int row, int col, int n) {
        StringBuffer placeQueenSB = new StringBuffer();
        for (int i = 0; i < n; i++) {
            if (i == col) {
                placeQueenSB.append("Q");
            } else {
                placeQueenSB.append(".");
            }
        }
        List<String> queens = returnList.get(num);
        if(queens.size() == 0) {
            queens = initQueen(n);
        }
        queens.add(row, placeQueenSB.toString());
        if (queens.size() > n) {
            // 因为add方法是将旧数据往后推，但是我们是要新数据替换旧数据，所以这里把旧数据删除
            queens.remove(row + 1);
        }
    }

    private boolean isOk(int row, int col, List<List<String>> returnList, int n) {
        int leftUp = col - 1;
        int rightUp = col + 1;
        for (row = row - 1; row >= 0; row--) {
            List<String> queens = returnList.get(num);
            if(queens.size() == 0) {
                queens = initQueen(n);
            }
            String placeQueen = queens.get(row);
            int queenIndex = placeQueen.indexOf("Q");
            if (queenIndex == col) {
                return false;
            }
            if (leftUp >=0 && queenIndex == leftUp) {
                return false;
            }
            if (rightUp < n && queenIndex == rightUp) {
                return false;
            }

            leftUp--;
            rightUp++;
        }

        return true;
    }
}
