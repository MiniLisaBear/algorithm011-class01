package com.swpu.jike.TopK;

public class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {4,1,-1,2,-1,2,3};
        int[] r = solution.topKFrequent(nums,2);
        for (int i = 0; i <r.length ; i++) {
            System.out.println(r[i]);
        }
    }
}
