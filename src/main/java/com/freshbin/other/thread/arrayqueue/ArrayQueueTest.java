package com.freshbin.other.thread.arrayqueue;

/**
 * @author freshbin
 * @date 2020/5/12 15:05
 */
public class ArrayQueueTest {
    public static void main(String[] arg) throws InterruptedException {
        ArrayQueue<String> arrayQueue = new ArrayQueue(99);
        Thread t1 = new Thread(() -> {
            for(int i = 0; i < 100; i++) {
                arrayQueue.put(i + "");
            }
        });
        t1.start();
        Thread.sleep(100);
        System.out.println("队列剩余未消费数：" + arrayQueue.size()); // 应该有99个

        Thread t2 = new Thread(() -> {
            for(int i = 0; i < 90; i++) {
                arrayQueue.get();
            }
        });
        t2.start();
        t2.join();
        System.out.println("队列剩余未消费数：" + arrayQueue.size()); // 应该剩余10个

        Thread t3 = new Thread(() -> {
            for(int i = 0; i < 30; i++) {
                arrayQueue.put(i + "");
            }
        });
        Thread t4 = new Thread(() -> {
            for(int i = 0; i < 30; i++) {
                arrayQueue.put(i + "");
            }
        });
        Thread t5 = new Thread(() -> {
            for(int i = 0; i < 30; i++) {
                arrayQueue.put(i + "");
            }
        });
        Thread t6 = new Thread(() -> {
            for(int i = 0; i < 10; i++) {
                arrayQueue.get();
            }
        });
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t3.join();
        t4.join();
        t5.join();
        System.out.println("最后剩余的数量：" + arrayQueue.size()); // 应该有90个
    }
}
