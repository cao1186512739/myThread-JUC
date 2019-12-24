package com.opendev.collection;

import java.util.concurrent.ConcurrentHashMap;

public class myConcurrentHashMap {
    public static void main(String[] args) {

        /**
         * 介绍：
         * ConcurrentHashMap是Java中的一个线程安全且高效的HashMap实现。
         * 平时涉及高并发如果要用map结构，那第一时间想到的就是它。
         *
         *问题：
         * 那么它到底是如何实现线程安全的？
         * 答案:其中抛弃了原有的Segment 分段锁，
         * 而采用了 CAS + synchronized 来保证并发安全性。
         *
         *参考：https://www.jianshu.com/p/5dbaa6707017
         *     https://www.cnblogs.com/aspirant/p/8623864.html
         *     https://juejin.im/post/5aeeaba8f265da0b9d781d16
         *
         *
         *
         *
         * HashMap、Hashtable、ConccurentHashMap三者的区别
         * HashMap线程不安全，数组+链表+红黑树
         * Hashtable线程安全，锁住整个对象，数组+链表
         * ConccurentHashMap线程安全，CAS+同步锁，数组+链表+红黑树
         * HashMap的key，value均可为null，其他两个不行。
         *
         * 在JDK1.7和JDK1.8中的区别
         * 在JDK1.8主要设计上的改进有以下几点:
         *
         * 1、不采用segment而采用node，锁住node来实现减小锁粒度。
         * 2、设计了MOVED状态 当resize的中过程中 线程2还在put数据，线程2会帮助resize。
         * 3、使用3个CAS操作来确保node的一些操作的原子性，这种方式代替了锁。
         * 4、sizeCtl的不同值来代表不同含义，起到了控制的作用。
         * 采用synchronized而不是ReentrantLock
         *
         *
         */
        ConcurrentHashMap map = new ConcurrentHashMap();

    }
}
