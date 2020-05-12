package com.freshbin.other.thread.printEvenAndOddNum;

/**
 * 打印偶数
 *
 * @author freshbin
 * @date 2020/5/12 9:42
 */
public class OddNum implements Runnable {
    private AlternateNum alternateNum;

    OddNum(AlternateNum alternateNum) {
        this.alternateNum = alternateNum;
    }

    @Override
    public void run() {
        while (alternateNum.getStartNum() <= 100) {
            synchronized (alternateNum) {
                if (alternateNum.getStartNum() % 2 == 0) {
                    System.out.println(Thread.currentThread().getName() + "==偶数打印：" + alternateNum.getStartNum());
                    alternateNum.setStartNum(alternateNum.getStartNum() + 1);
                    alternateNum.notify();
                } else {
                    try {
                        alternateNum.wait();
                    } catch (InterruptedException e) {
                        System.out.println("OddNum.run中调用线程等待异常！");
                    }
                }
            }
        }
    }
}
