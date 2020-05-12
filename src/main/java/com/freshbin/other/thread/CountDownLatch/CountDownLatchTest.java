package com.freshbin.other.thread.CountDownLatch;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author freshbin
 * @date 2020/5/12 20:54
 */
public class CountDownLatchTest {
    public static void main(String[] arg) throws InterruptedException {
        int threadCount = 10;
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        ThreadPoolExecutor threadPoolExecutor =  new ThreadPoolExecutor(5, 5,
                10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10));
        for(int i = 0; i < threadCount; i++) {
            threadPoolExecutor.execute(() -> {
                System.out.print("run ");
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        System.out.println("所有线程执行完毕！");
        threadPoolExecutor.shutdown();
    }
}
