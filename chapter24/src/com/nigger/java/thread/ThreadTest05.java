package com.nigger.java.thread;

/**
 *   1. 怎么获取当前线程对象
 *         Thread t = Thread.currentThread()
 *         返回值t就是当前线程
 *
 *  2. 获取线程对象的名字
 *         线程对象.getName()
 *
 *  3. 修改线程对象的名字
 *         线程对象.setName("名字")
 *
 *  4. 当线程没有设置名字的时候 会有默认名字
 *          Thread-0
 *          Thread-1
 *          Thread-2
 *          ..........
 */
public class ThreadTest05 {
    public static void main(String[] args) {
        // currentThread就是当前线程对象
        // 这个代码出现在main方法当中 所以当前线程就是主线程
        Thread currentThread = Thread.currentThread();
        System.out.println(currentThread.getName());   // main

        MyThread2 t1 = new MyThread2();
        // 设置线程的名字
        t1.setName("t1");
        // 获取线程的名字
        String tName = t1.getName();
        System.out.println(tName);  // 默认Thread-0
        // 启动线程
        t1.start();

        MyThread2 t2 = new MyThread2();
        t2.setName("t2");
        System.out.println(t2.getName());  // 默认名字 Thread-1
        t2.start();



    }
}

class MyThread2 extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            // currentThread就是当前线程对象 当前线程是谁呢？
            // 当t1线程执行run方法 当前线程就是t1
            // 当t2线程执行run方法 当前线程就是t2
            Thread currentThread = Thread.currentThread();
            System.out.println(currentThread.getName()+ "---->" + i);
        }
    }
}
