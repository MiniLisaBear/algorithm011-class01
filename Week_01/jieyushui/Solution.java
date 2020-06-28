package com.swpu.jike.jieyushui;

import java.util.Stack;

/*
 * leetcode-42
 */
public class Solution {
    public int trap(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int current = 0;
        int sum = 0;
//        System.out.println(height.length);
        while(current < height.length) {
//            System.out.println(current);
            while(!stack.empty() && height[current] > height[stack.peek()]) {
                int h = height[stack.peek()];
                stack.pop();
                if (stack.empty()) break;
                int distance = current - stack.peek() - 1;
                int min = Math.min(height[current],height[stack.peek()]);
                sum = sum + distance*(min - h);
            }
            stack.push(current);
            current++;
        }
        return sum;
    }
}
