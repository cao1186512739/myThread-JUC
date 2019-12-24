package com.opendev.Future;

public class MyFuture {
    /**
     *
     *
     *Future保存异步计算的结果,可以在我们执行任务时去做其他工作，并提供了以下几个方法
     *
     * * cancel(boolean mayInterruptIfRunning)：试图取消执行的任务，参数为true时直接中断正在执行的任务，否则直到当前任务执行完成，成功取消后返回true，否则返回false
     *
     * * isCancel()：判断任务是否在正常执行完前被取消的，如果是则返回true
     *
     * * isDone()：判断任务是否已完成
     *
     * * get()：等待计算结果的返回，如果计算被取消了则抛出
     *
     * * get(long timeout,TimeUtil unit)：设定计算结果的返回时间，如果在规定时间内没有返回计算结果则抛出TimeOutException
     *
     * 使用Future的好处：
     *
     * 1. 获取任务的结果，判断任务是否完成，中断任务
     *
     * 1. Future的get方法很好的替代的了Thread.join或Thread,join(long millis)
     *
     * 2. Future的get方法可以判断程序代码(任务)的执行是否超时，如：
     *   try{
     *       future.get(60,TimeUtil.SECOND);
     *  }catch(TimeoutException timeout){
     *       log4j.log("任务越野，将被取消！！");
     *       future.cancel();
     *  }
     *
     *
     * 应用：
     *  具有返回值的多线程
     *
     *  eg: 查找包含某关键字的文件个数：每个文件启动一个线程去查找关键字
     *
     *
     */
}
