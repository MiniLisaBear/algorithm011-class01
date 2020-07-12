package com.swpu.jike.Pow;

public class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        double y1 = solution.fastPow(2.00000, 10);
        double y2 = solution.fastPow(2.10000, 3);
        double y3 = solution.fastPow(2.00000, -2);
        System.out.println(y1);
        System.out.println(y2);
        System.out.println(y3);
    }
}
