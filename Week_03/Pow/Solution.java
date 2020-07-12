package com.swpu.jike.Pow;

/**
 * 功能描述:
 * 计算x的n次方，模拟pow(x,n)
 * -100.0<x<100.0
 * n是32位有符号整数，属于[-2^31,2^31-1]
 */
public class Solution {
    public double myPow(double x,int n) {
        return n>0?fastPow(x,n):fastPow(x,-n);
    }
    double fastPow(double x,int n) {
        if(n==0) return 1.0;
        double result = fastPow(x,n/2);
        return n%2==0?result*result:result*result*x;
    }
}
