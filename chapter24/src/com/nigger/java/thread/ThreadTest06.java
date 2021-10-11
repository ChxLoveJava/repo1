package com.nigger.java.thread;

/**
 *    关于线程的sleep方法：
 *        static void	sleep(long millis)
 *        1. 静态方法    Thread.sleep(1000);
 *        2. 参数是毫秒
 *        3. 作用： 让当前线程进入休眠 进入”阻塞状态“ 放弃占用CPU时间片 让给其他线程使用
 *               这行代码出现在A线程中 A线程就会进入休眠
 *               出现在B线程中 B线程就会进入休眠
 *        4. Thread.sleep()方法可以做到：
 *             间隔特定的时间 执行一段特定的代码 每隔多久执行一次
 */
public class ThreadTest06 {
    public static void main(String[] args) {
//        // 让当前线程进入休眠 睡眠5秒
//        //当前线程是主线程！！！
//        try {
//            Thread.sleep(1000*5);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        // 五秒之后执行这里的代码
//        System.out.println("hello nigger");

        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "---->" + i);
            // 睡眠一秒钟
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}
