package com.opendev.atomic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class IntgerAtomicTest {

    private static AtomicInteger ourInstance = new AtomicInteger();

    private IntgerAtomicTest() {
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicReference<Integer> ref = new AtomicReference<>(new Integer(0));

        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Thread t = new Thread(new Task(ref), "Thread-no" + i);
            list.add(t);
            t.start();
        }

        for (Thread t : list) {
            t.join();
        }

        System.out.println(ref.get());    // 打印2000
    }
}


class Task implements Runnable{

    private AtomicReference<Integer> ref;

    Task(AtomicReference<Integer> ref) {
        this.ref = ref;
    }

    public void run() {
        for (; ; ) {    //自旋操作
            Integer oldV = ref.get();
            if (ref.compareAndSet(oldV, oldV + 1))  // CAS操作
                break;
        }
    }
}