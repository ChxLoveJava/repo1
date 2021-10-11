package com.nigger.java.threadsafe;

/**
 *   银行账户
 */
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
    //不适应线程同步机制 多线程对同一个账户进行取款 出现线程安全问题
    public void withdraw(double money){
        // t1和t2并发这个方法（t1和t2是两个栈，两个栈操作堆中同一个对象）
        // 取款之前的余额
        double before = this.getBalance();
        //取款之后的余额
        double after = before - money;
        // 更新余额
        // 思考： 假设t1执行到这里了 但是还没有来得及执行这行代码 t2线程进来withdraw方法了 此时一定出问题
        this.setBalance(after);
    }
}
