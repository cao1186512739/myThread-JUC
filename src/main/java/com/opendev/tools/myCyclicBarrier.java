package com.opendev.tools;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class myCyclicBarrier {
    public static void main(String[] args) {


        /**含义：
         * 创建一个新的 CyclicBarrier ，当给定数量的线程（线程）等待时，它将跳闸，
         * 当屏障跳闸时执行给定的屏障动作，由最后一个进入屏障的线程执行。
         *
         *
         *
         *
         * 允许一组线程全部等待彼此达到共同屏障点的同步辅助。 循环阻塞在涉及固定大小的线程方的程序中很有用，这些线程必须偶尔等待彼此。 屏障被称为循环 ，因为它可以在等待的线程被释放之后重新使用。
         *
         * A CyclicBarrier支持一个可选的Runnable命令，每个屏障点运行一次，在派对中的最后一个线程到达之后，但在任何线程释放之前。 在任何一方继续进行之前，此屏障操作对更新共享状态很有用。
         *
         * 　　实现原理：在CyclicBarrier的内部定义了一个Lock对象，每当一个线程调用await方法时，将拦截的线程数减1，然后判断剩余拦截数是否为初始值parties，如果不是，进入Lock对象的条件队列等待。如果是，执行barrierAction对象的Runnable方法，然后将锁的条件队列中的所有线程放入锁等待队列中，这些线程会依次的获取锁、释放锁。
         *
         * 构造方法　
         *
         * CyclicBarrier(int parties)
         * 创建一个新的 CyclicBarrier ，当给定数量的线程（线程）等待它时，它将跳闸，并且当屏障跳闸时不执行预定义的动作。
         * CyclicBarrier(int parties, Runnable barrierAction)
         * 创建一个新的 CyclicBarrier ，当给定数量的线程（线程）等待时，它将跳闸，当屏障跳闸时执行给定的屏障动作，由最后一个进入屏障的线程执行。
         *
         * 方法:
         *
         * int await() 等待所有 parties已经在这个障碍上调用了 await 。
         * int	await(long timeout, TimeUnit unit) 等待所有 parties已经在此屏障上调用 await ，或指定的等待时间过去。
         * int	getNumberWaiting() 返回目前正在等待障碍的各方的数量。
         * int	getParties() 返回旅行这个障碍所需的parties数量。
         * boolean	isBroken() 查询这个障碍是否处于破碎状态。
         * void	reset() 将屏障重置为初始状态。
         *
         *
         *
         *
         *
         * CountDownLatch和CyclicBarrier的比较
         * CountDownLatch是线程组之间的等待，即一个(或多个)线程等待N个线程完成某件事情之后再执行；而CyclicBarrier则是线程组内的等待，即每个线程相互等待，即N个线程都被拦截之后，然后依次执行。
         * CountDownLatch是减计数方式，而CyclicBarrier是加计数方式。
         * CountDownLatch计数为0无法重置，而CyclicBarrier计数达到初始值，则可以重置。
         * CountDownLatch不可以复用，而CyclicBarrier可以复用。
         * 详见本人github：https://github.com/BrokenColor/java-demo 下的 cyclicbarrier-包中的测试
         *
         * 仅做笔记，如有错误之处，望指出
         *
         */
        CyclicBarrier cbRef = new CyclicBarrier(5, new Runnable() {
            public void run() {
                System.out.println("都到了");
            }
        });


        myCyclicBarrierThread[] threads = new myCyclicBarrierThread[5];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new myCyclicBarrierThread(cbRef);
            threads[i].start();
        }
    }
}

class myCyclicBarrierThread extends Thread {

    private CyclicBarrier cbRef;

    public myCyclicBarrierThread(CyclicBarrier cbRef) {
        this.cbRef = cbRef;
    }

    @Override
    public void run() {
        try {
            Thread.sleep((int) (Math.random() * 1000));
            System.out.println(Thread.currentThread().getName() + "到了!" + System.currentTimeMillis());
            // 等待所有 parties已经在这个障碍上调用了 await 。
            cbRef.await();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}