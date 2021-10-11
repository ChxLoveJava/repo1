package com.nigger.java.threadsafe3;


public class Account {
    private String actno;
    private double balance;


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
    /*
        在实例方法上也可以使用 synchronized
           synchronized出下载实例方法上 一定锁的是this
           没得挑 只能是this 不能是其他对象了
           所以这种方式不灵活

           另外还有一个缺点： synchronized出现在实例方法上
           表示整个方法体都需要同步 可能会无辜扩大同步的范围
           导致程序的执行效率降低 所以这种方式不常用

           synchronized 使用在实例方法上有什么优点？
              代码少了

            如果共享的对象就是this 并且需要同步的代码块是整个方法体
            建议使用这种方式
     */
    public synchronized void withdraw(double money){
        double before = this.getBalance();
        double after = before - money;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.setBalance(after);
       }
}
