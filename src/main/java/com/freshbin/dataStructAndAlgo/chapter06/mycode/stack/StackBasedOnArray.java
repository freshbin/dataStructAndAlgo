package com.freshbin.dataStructAndAlgo.chapter06.mycode.stack;

/**
 * 使用数组实现栈
 * @author freshbin
 * @date 2020/4/14 9:45
 */
public class StackBasedOnArray {
    private Integer maxSize;
    private Integer currentCount;
    private String[] array;

    public StackBasedOnArray() {
        this.maxSize = 2;
        array = new String[maxSize];
        currentCount = 0;
    }

    public StackBasedOnArray(Integer maxSize) {
        this.maxSize = maxSize;
        array = new String[maxSize];
        currentCount = 0;
    }

    public void push(String value) {
        String returnValue = null;
        // 如果栈已经满了，那么就扩容2倍
        if(currentCount == maxSize) {
            System.out.println("栈满，当前容量为：" + this.maxSize + ",开始扩容2倍");
            expandArraySize();
            System.out.println("扩容后的容量：" + this.maxSize);
        }
        this.array[currentCount++] = value;
    }

    public String pop() {
        if(currentCount == 0) {
            System.out.println("栈空");
            return null;
        }
        return this.array[--currentCount];
    }

    private void expandArraySize() {
        this.maxSize = this.maxSize * 2;
        String[] newArray = new String[this.maxSize];
        for(int i = 0; i < this.array.length; i++) {
            newArray[i] = this.array[i];
        }
        this.array = newArray;
    }

    public static void main(String[] arg) {
        String[] paramDatas = new String[4];
        for(int i = 0; i < paramDatas.length; i++) {
            paramDatas[i] = "param" + i;
        }

        StackBasedOnArray stackBasedOnArray = new StackBasedOnArray();
        stackBasedOnArray.pop();
        for(String paramData : paramDatas) {
            stackBasedOnArray.push(paramData);
        }

        System.out.println("出栈，打印栈中的值：");
        String value = stackBasedOnArray.pop();
        while(value != null) {
            System.out.print(value + " ");
            value = stackBasedOnArray.pop();
        }
    }
}
