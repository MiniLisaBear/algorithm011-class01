package com.swpu.jike.MyCircularDeque;

/*
 * leetcode-641
 */
public class MyCircularDeque {
    int[] array;
    int size;
    int front;
    int last;
    /** Initialize your data structure here. Set the size of the deque to be k. */
    public MyCircularDeque(int k) {
        this.array = new int[k];
        this.size = 0;
        this.front = 0;
        this.last = 0;
    }

    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
        if(size == array.length) {
            return false;
        }
        if(front == 0){
            array[array.length - 1] = value;
            front = array.length - 1;
        }else {
            array[--front] = value;
        }
        size ++;
        return true;
    }

    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if(size == array.length) {
            return false;
        }
        array[last] = value;
        size ++;
        last = last == array.length - 1 ? 0 : last+1;
        return true;
    }

    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if(size == 0) {
            return false;
        }
        front = front == array.length - 1 ? 0:front+1;
        size--;
        return true;
    }

    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if (size == 0) {
            return false;
        }
        if (last == 0) {
            last = array.length - 1;
        } else {
            last --;
        }
        size --;
        return true;
    }

    /** Get the front item from the deque. */
    public int getFront() {
        if (size == 0) {
            return -1;
        }
        return array[front];
    }

    /** Get the last item from the deque. */
    public int getRear() {
        if (size == 0){
            return -1;
        }
        return last == 0 ? array[array.length - 1]:array[last-1];
    }

    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return size == array.length;
    }
}
