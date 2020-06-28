package com.swpu.jike.MyCircularDeque;

public class Test {
    public static void main(String[] args) {
        MyCircularDeque obj = new MyCircularDeque(3);
        boolean param_1 = obj.insertLast(1);
        System.out.println(param_1);
        boolean param_2 = obj.insertLast(2);
        System.out.println(param_2);
        boolean param_3 = obj.insertFront(3);
        System.out.println(param_3);
        boolean param_4 = obj.insertFront(4);
        System.out.println(param_4);
        int param_5 = obj.getRear();
        System.out.println(param_5);
        boolean param_6 = obj.isFull();
        System.out.println(param_6);
        boolean param_7 = obj.deleteLast();
        System.out.println(param_7);
        boolean param_8 = obj.insertFront(4);
        System.out.println(param_8);
        int param_9 = obj.getFront();
        System.out.println(param_9);
    }
}
