package com.nigger.java.homework;

import java.util.ArrayList;
import java.util.List;

/**
 *   使用生产者和消费者模式实现 交替输出：
 *      假设只有两个线程 输出以下结果：
 *          t1 --> 1
 *          t2 --> 2
 *          t1 --> 3
 *          t2 --> 4
 *        要求必须交替 并且t1线程输出奇数  t2线程输出偶数
 *        两个线程共享一个数字 每个线程执行得时候都要对这个数字进行 ++
 */
public class Test {
    public static void main(String[] args) {
        Number number = new Number(1);
        Thread t1 = new Thread(new 奇数(number));
        Thread t2 = new Thread(new 偶数(number));

        t1.setName("t1");
        t2.setName("t2");

        t1.start();
        t2.start();

    }
}

class 奇数 implements Runnable{
    Number number;
    public 奇数(Number number){
        this.number = number;
    }
    @Override
    public void run() {
         while(true){
             synchronized (number){
                 if(number.getI() % 2 == 0){
                     try {
                         number.wait();
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                 }
                 try {
                     Thread.sleep(1000);
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
                 System.out.println(Thread.currentThread().getName() + "---->" + number.getI());
                 number.setI(number.getI() + 1);
                 number.notify();
             }
         }
    }
}

class 偶数 implements Runnable{
    Number number;
    public 偶数(Number number){
        this.number = number;
    }
    @Override
    public void run() {
        while(true){
            synchronized (number){
                if(number.getI() % 2 != 0){
                    try {
                        number.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "---->" +number.getI());
                number.setI(number.getI() + 1);
                number.notify();
            }
        }
    }
}

class Number{
    private int i;

    public Number() {
    }

    public Number(int i) {
        this.i = i;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
}


