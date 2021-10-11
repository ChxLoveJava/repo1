package com.nigger.java.thread;

/**
 *    在Java中怎么强行终止一个线程
 *        这种方式存在很大的缺点：容易丢失数据 因为这种方式是直接将线程杀死了
 *        线程没有保存的数据将会丢失 不建议使用
 */
public class ThreadTest09 {
    public static void main(String[] args) {
         Thread t = new Thread(new MyRunnable3());
         t.setName("t");
         t.start();

         // 模拟五秒钟
        try {
            Thread.sleep(1000*5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 五秒之后强行终止t线程
        t.stop();   // 已过时 （不建议使用）

    }
}

class MyRunnable3 implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "--->" + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
