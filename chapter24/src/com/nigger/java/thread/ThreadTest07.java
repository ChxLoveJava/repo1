package com.nigger.java.thread;

/**
 *    关于Thread.sleep() 方法的一个面试题
 */
public class ThreadTest07 {
    public static void main(String[] args) {
        Thread t = new MyThread3();
        t.setName("t");
        t.start();

        // 调用sleep方法
        try {
            // 问题：这行代码会让 线程t进入休眠状态嘛
            t.sleep(1000 * 5);   // 在执行的时候还是会转换成 Thread.sleep(1000*5)
                                         // 这行代码的作用是：让当前线程进入休眠 也就是让main方法进入休眠
                                         // 这行代码出现在main方法中 所以main线程休眠
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 五秒之后 这里才会执行
        System.out.println("hello nigger");

    }
}

class MyThread3 extends Thread{
    public void run(){
        for (int i = 0; i < 10000; i++) {
            System.out.println(Thread.currentThread().getName() + "--->" + i);
        }

    }
}
