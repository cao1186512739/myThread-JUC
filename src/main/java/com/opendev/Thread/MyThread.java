package com.opendev.Thread;

import java.util.Map;

public class MyThread extends Thread {

    /**
     * 继承Thread方式
     *
     *
     *  继承Thread类，需要覆盖方法 run()方法，
     *  在创建Thread类的子类时需要重写 run()，加入线程所要执行的代即可
     *
     *
     */

    private String acceptStr;

    public MyThread(String acceptStr) {
        this.acceptStr = acceptStr;
    }


    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("这个传给我的值：" + acceptStr + ",加上一个变量，看看是什么效果：" + i);
        }
    }


    public static void main(String[] args) {
        new MyThread("Thread测试").start();
        new MyThread("Thread测试").start();
    }
}
