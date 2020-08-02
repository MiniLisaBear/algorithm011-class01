package com.swpu.jike.week06;

public class HouseRobber1 {
    public int rob(int[] nums) {
        if(nums.length==0) return 0;
        if(nums.length==1) return nums[0];
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0],nums[1]);
        for(int i=2;i<nums.length;i++) {
            dp[i] = Math.max(dp[i-1],dp[i-2]+nums[i]);
        }
        return dp[nums.length-1];
    }
    //中间状态用变量存储
    public int rob2(int[] nums) {
        if(nums.length==0) return 0;
        if(nums.length==1) return nums[0];
        int[] dp = new int[nums.length];
        int preOne = nums[0];
        int preTwo = Math.max(nums[0],nums[1]);
        for(int i=2;i<nums.length;i++) {
            int temp = preTwo;
            preTwo = Math.max(preTwo,preOne+nums[i]);
            preOne = temp;
        }
        return preTwo;
    }
}
