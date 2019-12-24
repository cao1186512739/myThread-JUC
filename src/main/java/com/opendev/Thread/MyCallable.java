package com.opendev.Thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class MyCallable implements Callable<String> {

    /**
     * 实现 Callable 接口的方法
     * @return
     * @throws Exception
     *
     * 介绍：
     *如果你希望任务在完成的能返回一个值，那么可以实现Callable接口而不是Runnable接口。
     *在Java SE5中引入的Callable是一种具有类型参数的泛型，
     *它的参数类型表示的是从方法call()(不是run())中返回的值。
     *
     *
     */

    private String acceptStr;

    public MyCallable(String acceptStr) {
        this.acceptStr = acceptStr;
    }


    @Override
    public String call() throws Exception {
        // 任务阻塞1秒，并且增加一些信息返回
        Thread.sleep(1000);
        return this.acceptStr + " 增加一些字符并返回";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<String> callable = new MyCallable("Callable测试");
        FutureTask<String> task = new FutureTask<String>(callable);
        // 创建线程
        new Thread(task).start();
        long beginTime = System.currentTimeMillis();
        // 调用get()阻塞主线程，反之，线程不会阻塞
        String result = task.get();
        long endTime = System.currentTimeMillis();
        System.out.println("hello : " + result);

        // endTime 和 beginTime是不一样的，因为阻塞了主线程
        System.out.println("cast : " + (endTime - beginTime) / 1000 + " second!");
    }
}
