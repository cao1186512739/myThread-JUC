package com.opendev.Thread;

import java.util.concurrent.Semaphore;

public class MySemaphore {

    public static void main(String[] args) {
        /**
         *
         * Semaphore semaphore = new Semaphore(1);
         *同步关键类，构造方法传入的数字是多少
         *则同一个时刻，只运行多少个进程同时运行制定代码
         *
         */
        Semaphore semaphore = new Semaphore(1);
        for (int i = 0; i < 1000; i++) {
            new mySemaphoreThread(semaphore).start();
        }
    }
}

class mySemaphoreThread extends Thread {

    private Semaphore semaphore;

    public mySemaphoreThread(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            /**
             * 在 semaphore.acquire() 和 semaphore.release()之间的代码，同一时刻只允许制定个数的线程进入，
             * 因为semaphore的构造方法是1，则同一时刻只允许一个线程进入，其他线程只能等待。
             *
             */
            semaphore.acquire();
            System.out.println("Frist.... 当前线程的名称: " + Thread.currentThread().getName() + " 端口为：" + Thread.currentThread().getId());
            Thread.sleep(2000);
            System.out.println("Second.... 当前线程的名称: " + Thread.currentThread().getName() + " 端口为：" + Thread.currentThread().getId());
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}