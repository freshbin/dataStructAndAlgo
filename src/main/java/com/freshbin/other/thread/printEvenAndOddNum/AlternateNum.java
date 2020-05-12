package com.freshbin.other.thread.printEvenAndOddNum;

/**
 * 交替打印奇偶数
 *
 * @author freshbin
 * @date 2020/5/12 9:41
 */
public class AlternateNum {
    private volatile int startNum = 0;

    public int getStartNum() {
        return this.startNum;
    }

    public void setStartNum(int startNum) {
        this.startNum = startNum;
    }

    public static void main(String[] arg) throws InterruptedException {
        AlternateNum alternateNum = new AlternateNum();
        Thread threadEvenNum = new Thread(new EvenNum(alternateNum));
        threadEvenNum.setName("evenThread");
        Thread threadOddNum = new Thread(new OddNum(alternateNum));
        threadOddNum.setName("oddThread");
        threadEvenNum.start();
        threadOddNum.start();

//        threadEvenNum.join();
        threadOddNum.join();
        System.out.println("main执行完毕");
    }
}
