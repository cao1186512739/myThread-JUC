package com.opendev.Lock;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyLock {

    public static void main(String[] args) {

        /**
         *  Java多线程加锁的方式总结及对比
         *
         * ① synchronized关键字
         * ② Java.util.concurrent包中的lock接口和ReentrantLock实现类
         * 2、synchronized关键字加锁的缺陷：
         * 如果一个代码块被synchronized修饰了，当一个线程获取了对应的锁，并执行改代码块时，其他线程便只能一直等待，等待获取锁的线程释放锁，而这里获取锁的线程释放锁只会有两种情况：
         * ① 获取锁的线程执行完了该代码块，然后线程释放对锁的占有；
         * ② 线程执行发生异常，此时JVM会让线程自动释放锁。
         * 那么如果这个获取锁的线程由于要等待IO或者其他原因（比如调用sleep方法）被阻塞了，但是又没有释放锁，其他线程便只能干巴巴地等待，试想一下，这多么影响程序执行效率。
         * 　因此就需要有一种机制可以不让等待的线程一直无期限地等待下去（比如只等待一定的时间或者能够响应中断），通过Lock就可以办到。
         * 　　再举个例子：当有多个线程读写文件时，读操作和写操作会发生冲突现象，写操作和写操作会发生冲突现象，但是读操作和读操作不会发生冲突现象。
         * 　　但是采用synchronized关键字来实现同步的话，就会导致一个问题：
         * 　　如果多个线程都只是进行读操作，所以当一个线程在进行读操作时，其他线程只能等待无法进行读操作。
         * 　　因此就需要一种机制来使得多个线程都只是进行读操作时，线程之间不会发生冲突，通过Lock就可以办到。
         * 　　另外，通过Lock可以知道线程有没有成功获取到锁。这个是synchronized无法办到的。
         * 3、对比
         * ① Lock不是Java语言内置的，synchronized是Java语言的关键字，因此是内置特性。Lock是一个类，通过这个类可以实现同步访问
         * ② Lock和synchronized有一点非常大的不同，采用synchronized不需要用户去手动释放锁，当synchronized方法或者synchronized代码块执行完之后，系统会自动让线程释放对锁的占用；而Lock则必须要用户去手动释放锁，如果没有主动释放锁，就有可能导致出现死锁现象。
         * 4、Lock和synchronized的选择
         * 总结来说，Lock和synchroized有以下几点不同：
         * ① Lock是一个接口， 而synchronized是Java中的关键字，synchronized是内置的语言实现；
         * ②  synchronized在发生异常时， 会自动释放线程占有的锁，因此 不会导致死锁现象发生； 而Lock在发生异常时，如果没有主动通过 unLock()去释放锁，则很可能造成死锁现象，因此使用Lock时需要在finally块中释放锁；
         * ③ Lock可以让等待锁的线程响应中断，而synchronized却不行，使用synchronized时，等待的线程会一直等待下去，不能够响应中断；（I/O和Synchronized都能相应中断，即不需要处理interruptionException异常）
         * ④ 通过Lock可以知道有没有成功获取锁，而synchronized却无法办到。
         * ⑥ Lock可以提高多个线程进行读操作的效率。
         * 在性能上来说，如果竞争资源不激烈，两者的性能是差不多的，而当竞争资源非常激烈时（即有大量线程同时竞争），此时Lock的性能要远远优于synchronized。所以说，在具体使用时要根据适当情况选择。
         *
         * 作者：头脑之外
         * 链接：https://www.jianshu.com/p/6558d652935f
         * 来源：简书
         * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
         *
         *
         */
        Lock lock = new ReentrantLock(); //TODO 重入锁（独占锁）
        Condition cond = lock.newCondition();
        for (int i = 0; i < 1000; i++) {
            new MyLockThread(lock, cond).start();
        }
    }
}

class MyLockThread extends Thread{

    private Lock lock;

    private Condition condition;

    public MyLockThread(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        lock.lock(); //todo  上锁
        try {
            System.out.println("Frist.... 当前线程的名称: " + Thread.currentThread().getName());
            Thread.sleep(2000);
            System.out.println("Second.... 当前线程的名称: " + Thread.currentThread().getName());
            condition.signalAll();  //todo  通知已经堵的线程，准备抢资源了
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();  //todo  解锁
        }
    }
}