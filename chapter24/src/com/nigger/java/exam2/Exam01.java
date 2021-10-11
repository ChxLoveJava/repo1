package com.nigger.java.exam2;

/**
 *  面试题   doOther方法执行的时候 需要等待doSome方法的结束嘛
 *       需要
 */
public class Exam01 {
    public static void main(String[] args) throws InterruptedException {
         MyClass mc = new MyClass();

         Thread t1 = new MyThread(mc);
         Thread t2 = new MyThread(mc);

         t1.setName("t1");
         t2.setName("t2");

         t1.start();
         Thread.sleep(1000);  //这个睡眠的作用是保证 t1线程先执行
         t2.start();
    }
}

class MyThread extends Thread{
    private MyClass mc;
    public MyThread(MyClass mc){
        this.mc = mc;
    }
    public void run(){
       if(Thread.currentThread().getName().equals("t1")){
           mc.doSome();
       }
        if(Thread.currentThread().getName().equals("t2")){
            mc.doOther();
        }

    }
}

class MyClass{
    // synchronized出现在实例方法上 锁this
    public synchronized void doSome(){
        System.out.println("doSome begin");
        try {
            Thread.sleep(1000 * 3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("doSome over");
    }
    public synchronized void doOther(){
        System.out.println("doOther begin");
        System.out.println("doOther over");
    }

}