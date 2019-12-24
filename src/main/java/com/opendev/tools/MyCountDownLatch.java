package com.opendev.tools;


import java.util.concurrent.CountDownLatch;

public class MyCountDownLatch {

    public static void main(String[] args) {
        /**
         *CountDownLatch 为 java.util.concurrent 包下的计数器工具类，
         *常被用在多线程环境下，它在初始时需要指定一个计数器的大小，
         *然后可被多个线程并发的实现减 1 操作，并在计数器为 0 后调用 await 方法的线程被唤醒，
         *从而实现多线程间的协作。
         *
         *
         *主要作用： 主要实现多线程之间的执行顺序，
         *
         *
         * 举个例子：1001个人（包括mainx线程）一起去创业，最后公司在上市的时候由公司的老大去敲钟(所有人都在等)
         *
         *
         */
        try {
            CountDownLatch countDownLatch = new CountDownLatch(1000);
            for (int i = 0; i < 1000; i++) {
                new myThread(countDownLatch).start();
            }
            countDownLatch.await();
            System.out.println("Over");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


class myThread extends Thread {

    private CountDownLatch countDownLatch;

    public myThread(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        System.out.println("当前线程的名称: " + Thread.currentThread().getName() + " 端口为：" + Thread.currentThread().getId());
        countDownLatch.countDown();//todo  等待并且计数减一
    }
}
