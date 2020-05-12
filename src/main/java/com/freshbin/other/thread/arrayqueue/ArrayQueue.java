package com.freshbin.other.thread.arrayqueue;

/**
 * 数组实现带锁队列
 * @author freshbin
 * @date 2020/5/12 14:52
 */
public class ArrayQueue<T> {
    private Integer count = 0;
    private Object[] array;
    /**
     * 队满锁
     */
    private Object full = new Object();
    /**
     * 队空锁
     */
    private Object empty = new Object();
    private Integer getIndex = 0;
    private Integer putIndex = 0;
    ArrayQueue(int size) {
        this.array = new Object[size];
    }

    public void put(T value) {
        synchronized (full) {
            while(count == array.length) {
                try{
                    full.wait();
                } catch (InterruptedException e) {
                    System.out.println("put方法中full.wait()异常");
                    break;
                }
            }
        }
        synchronized (empty) {
            array[putIndex] = value;
            putIndex++;
            count++;
            if(putIndex == array.length) {
                putIndex = 0;
            }
            empty.notify();
        }
    }

    public T get() {
        synchronized (empty) {
            while(count == 0) {
                try{
                    empty.wait();
                } catch (InterruptedException e) {
                    System.out.println("get方法中empty.wait()异常");
                    return null;
                }
            }
        }
        synchronized (full) {
            Object value = array[getIndex];
            getIndex++;
            count--;
            if(getIndex == array.length) {
                getIndex = 0;
            }
            full.notify();
            return (T)value;
        }
    }

    public int size() {
        return count;
    }
}
