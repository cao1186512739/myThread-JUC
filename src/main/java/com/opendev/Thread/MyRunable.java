package com.opendev.Thread;


public class MyRunable implements Runnable {


    /**
     * 实现Runnable方式
     * <p>
     * <p>
     * <p>
     * Runnable是可以共享数据的，多个Thread可以同时加载一个Runnable，
     * 当各自Thread获得CPU时间片的时候开始运行Runnable，Runnable里面的资源是被共享的，
     * 所以使用Runnable更加的灵活。PS：需要解决共享之后产生的资源竞争问题
     * <p>
     * <<资源共享，案例 抢火车票>>
     */

    private String acceptStr;

    public MyRunable(String acceptStr) {
        this.acceptStr = acceptStr;
    }

    @Override
    public void run() {
        try {
            // 线程阻塞1秒，此时有异常产生，只能在方法内部消化，无法上抛
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 最终处理结果无法返回
        System.out.println("hello : " + this.acceptStr);
    }

    public static void main(String[] args) {
        Runnable runnable = new MyRunable("Runable测试");
        long beginTime = System.currentTimeMillis();
        new Thread(runnable).start();
        long endTime = System.currentTimeMillis();

        // endTime 和 beginTime是一样的，线程并不会阻塞主线程
        System.out.println("cast : " + (endTime - beginTime) / 1000 + " second!");
    }
}
