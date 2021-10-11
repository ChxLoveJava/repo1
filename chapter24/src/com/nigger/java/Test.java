package com.nigger.java;

public class Test {
    public static void main(String[] args) {
        String path = Thread.currentThread().getContextClassLoader().getResource("com/db.properties").getPath();
        System.out.println(path);
    }
}
