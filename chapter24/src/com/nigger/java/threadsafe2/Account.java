package com.nigger.java.threadsafe2;

/**
 *   银行账户
 *      使用线程同步来解决线程安全问题
 */
public class Account {
    private String actno;
    private double balance;

    // 对象
    Object obj = new Object();  // 实例变量 （Account对象是多线程共享的，Account对象中的实例变量obj也是共享的）

    public Account() {
    }

    public Account(String actno, double balance) {
        this.actno = actno;
        this.balance = balance;
    }

    public String getActno() {
        return actno;
    }

    public void setActno(String actno) {
        this.actno = actno;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    // 取款方法
    public void withdraw(double money){
        // 以下这几行代码必须是线程排队的 不能并发
        // 一个线程把这里的代码全部执行结束之后 另一个线程才能进来
        /* 线程同步机制的语法是：
               synchronized(){
                  // 线程同步代码块
               }
               synchronized后面小括号中穿的这个 数据 是相当关键的
               这个数据必须是多线程共享数据 才能达到多线程排队

               () 中写什么？
                   那要看你想让哪些线程同步
                   假设t1 t2 t3 t4 t5 有五个线程
                   你只希望t1 t2 t3排队 t4 t5不需要排队 怎么办？
                   你一定要在()中写一个t1 t2 t3共享的对象，而这个对象
                   对于t4 t5来说 不是共享的
         */
        // 这里的共享对象是：账户对象
        // 账户对象是共享的 那么this就是账户对象
        // 不一定是this 这里只要是多线程共享的对象就行

        // 在Java语言中 任何一个对象 都有一把锁 其实这把锁就是一个标记

        /* 以下代码执行原理：
             1. 假设t1和t2线程并发 开始执行以下代码的时候 肯定有一个先一个后
             2. 假设线程t1执行了 遇到synchronized 这个时候自动找 后面共享对象的锁
             找到之后 并占有这把锁 然后执行同步代码快中的程序 在程序执行过程一致都是
             占有这把锁的 直到同步代码块代码结束 这把锁才会释放
             3. 假设t1已经占有了这把锁，此时t2也遇到了synchronized关键字 也会去占有
             后面共享对象的这把锁 结果这把锁被t1占有了 t2只能在同步代码块外面等待t1的结束
             直到t1把同步代码块执行结束了 t1会归还这把锁 此时t2终于等到这把锁 然后t2占有这把
             锁之后 进入同步代码块执行程序

             这样就达到了线程排队执行
             这里需要注意的是： 这个共享对象一定要选好了 这个共享对象一定是你需要排队执行的这些
             线程对象所共享的
        */

        //synchronized(this){
        double before = this.getBalance();
        double after = before - money;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.setBalance(after);
       }
   // }
}
