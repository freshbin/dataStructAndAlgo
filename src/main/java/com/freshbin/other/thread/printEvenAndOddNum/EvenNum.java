package com.freshbin.other.thread.printEvenAndOddNum;

/**
 * 打印奇数
 *
 * @author freshbin
 * @date 2020/5/12 9:42
 */
public class EvenNum implements Runnable {

    private AlternateNum alternateNum;

    EvenNum(AlternateNum alternateNum) {
        this.alternateNum = alternateNum;
    }

    @Override
    public void run() {
        while (alternateNum.getStartNum() <= 100) {
            synchronized (alternateNum) {
                if (alternateNum.getStartNum() % 2 != 0) {
                    System.out.println(Thread.currentThread().getName() + "==奇数打印：" + alternateNum.getStartNum());
                    alternateNum.setStartNum(alternateNum.getStartNum() + 1);
                    alternateNum.notify();
                } else {
                    try {
                        alternateNum.wait();
                    } catch (InterruptedException e) {
                        System.out.println("EvenNum.run中调用线程等待异常！");
                    }
                }
            }
        }

        try {
            System.out.println("EvenNum开始等待");
            Thread.sleep(10000);
            System.out.println("EvenNum等待结束");
        } catch (InterruptedException e) {
            System.out.println("EvenNum.run中调用Thread.sleep(10000)异常！");
        }

    }
}
