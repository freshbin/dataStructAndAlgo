package com.freshbin.other.thread.Semaphore;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author freshbin
 * @date 2020/5/12 22:05
 */
public class SemaphoreTest {
    private static Random rand= new Random(3000);
    public static void main(String[] arg) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10,
                10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10));
        Semaphore semaphore = new Semaphore(3);
        for(int i = 0; i < 10; i++) {
            threadPoolExecutor.execute(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(semaphore.availablePermits() + "==进入==");
                    TimeUnit.MILLISECONDS.sleep(rand.nextInt(10000));
                    System.out.println(semaphore.availablePermits() + "==退出==");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            });
        }
        threadPoolExecutor.shutdown();
    }
}
