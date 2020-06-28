package com.swpu.jike.jieyushui;

public class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] h = {0,1,0,2,1,0,1,3,2,1,2,1};
        int x = solution.trap(h);
        System.out.println(x);
    }
}
