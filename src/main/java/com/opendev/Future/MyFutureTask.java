package com.opendev.Future;


public class MyFutureTask {
    public static void main(String[] args) {
        /**
         *
         *
         * FutureTask实现了RunnableFuture接口，提供了即可以使用Runnable来执行任务，
         * 又可以使用Future执行任务并取得结果的构造器，
         * 所以可以利用FutureTask去封装Runnable或Callable对象，之后再submit任务
         *
         *  FutureTask(Callable callable)
         *  FutureTask(Runnable runnable, V result)
         */
    }
}
