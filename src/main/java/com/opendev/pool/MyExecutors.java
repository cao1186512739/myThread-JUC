package com.opendev.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyExecutors {


    /**
     *
     * Executor :接口
     * Executors ：类
     * ExecutorService ：接口 继承了Executor类
     *
     * 参考：https://juejin.im/post/5d1882b1f265da1ba84aa676#heading-45
     *
     * @param args
     */
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            threadPool.submit(() -> {
                System.out.println("current thread name" + Thread.currentThread().getName());
                Object object = null;
                System.out.print("result## "+object.toString());
            });
        }
    }
}
