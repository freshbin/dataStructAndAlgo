package com.freshbin.other.thread.CyclicBarrier;

import java.util.concurrent.*;

/**
 * @author freshbin
 * @date 2020/5/12 21:47
 */
public class CycliBarrierTest {
    public static void main(String[] arg) {
        int threadNum = 10;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(threadNum);
        ThreadPoolExecutor threadPoolExecutor =  new ThreadPoolExecutor(10, 10,
                10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10));
        for(int i = 0; i < threadNum; i++) {
            threadPoolExecutor.execute(() -> {
                System.out.print("before..");
                try {
                    cyclicBarrier.await(); // 如果线程池的可用线程太少，而这时候就会卡住不动，后面的线程无法进入队列，前面的线程还没出队
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.print("after..");
            });
        }
        threadPoolExecutor.shutdown();
    }
}
