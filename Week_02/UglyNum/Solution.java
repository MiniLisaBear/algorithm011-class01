package com.swpu.jike.UglyNum;

public class Solution {
    public int nthUglyNumber(int n) {
        int[] a = new int[n];
        a[0]=1;
        int p2=0,p3=0,p5=0;
        for(int i=1;i<n;i++) {
            a[i] = Math.min(a[p2]*2,Math.min(a[p3]*3,a[p5]*5));
            if(a[i]==a[p2]*2) p2++;
            if(a[i]==a[p3]*3) p3++;
            if(a[i]==a[p5]*5) p5++;
        }
        return a[n-1];
    }
}
