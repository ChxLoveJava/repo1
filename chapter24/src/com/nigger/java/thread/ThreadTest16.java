package com.nigger.java.thread;

import java.util.ArrayList;
import java.util.List;

/**
 *   1. 使用wait方法和notify方法实现 生产者和消费者模式
 *
 *   2. 什么是生产者消费者模式？
 *      生产线程负责生产 消费线程负责消费
 *      生产线程和消费线程达到均衡
 *      这是一个特殊的业务需求 在这种特殊的情况下需要使用wait方法和notify方法
 *
 *   3. wait和notify方法不是线程对象的方法 是普通的java对象都有的方法
 *
 *   4. wait方法和notify方法建立在线程同步的基础上 因为多线程要同时操作一个仓库 有线程安全问题
 *
 *   5. wait方法的作用：o.wait()方法让正在o对象上获取的线程t进入等待状态 并且释放掉t线程之前占有的o对象的锁
 *
 *   6. o.notify() 让正在o对象上等待的线程唤醒 + 只是通知 不会释放o对象上之前占有的锁
 *
 *   7. 模拟这样一个需求：
 *        仓库采用List集合
 *        List集合中假设只能存储一个元素
 *        一个元素就表示仓库满了
 *        如果List集合中元素个数是0 就表示仓库空了
 *        保证List集合中永远都是最多存储一个元素
 *        必须做到：生产一个消费一个
 */
public class ThreadTest16 {
    public static void main(String[] args) {
        //创建一个共享仓库对象
        List list = new ArrayList();
        // 创建生产者线程
        Thread t1 = new Thread(new Producer(list));
        // 消费者线程
        Thread t2 = new Thread(new Consumer(list));

        t1.setName("生产者线程");
        t2.setName("消费者线程");

        t1.start();
        t2.start();

    }
}

// 生产线程
class Producer implements Runnable{
    // 仓库
    private List list;

    public Producer(List list){
        this.list = list;
    }
    @Override
    public void run() {
        // 一直生产(使用死循环)
        while(true){
            // 给仓库对象list加锁
            synchronized (list){
                if(list.size() > 0){  //大于0说明仓库中已经有一个元素了
                    try {
                        // 当前线程进入等待状态，并且释放Producer之前占有的list集合的锁
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 程序能够执行到这里说明 仓库是空的 可以生产
                Object obj = new Object();
                list.add(obj);
                System.out.println(Thread.currentThread().getName() + "--->" + obj);
                // 唤醒消费者进行消费
                list.notify();
            }
        }
    }
}

// 消费线程
class Consumer implements Runnable{
    // 仓库
    private List list;

    public  Consumer(List list){
        this.list = list;
    }
    @Override
    public void run() {
        // 一直消费
        while(true){
            synchronized (list){
                if(list.size() == 0){
                    try {
                        // 仓库已经空了 消费者线程等待 释放掉list集合的锁
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 程序能够执行到此处 说明仓库中有数据 进行消费
                Object obj = list.remove(0);
                System.out.println(Thread.currentThread().getName() + "--->" + obj);
                // 唤醒生产者生产
                list.notify();
            }
        }
    }
}

