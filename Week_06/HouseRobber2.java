package com.swpu.jike.week06;

import java.util.Arrays;

public class HouseRobber2 {
    public int rob(int[] nums) {
        if(nums.length == 0) return 0;
        if(nums.length == 1) return nums[0];
        return Math.max(getRob(Arrays.copyOfRange(nums,0,nums.length-1)),getRob(Arrays.copyOfRange(nums,1,nums.length)));
    }
    private int getRob(int[] nums) {
        int preOne = 0,preTwo = 0,cur=0;
        for(int num:nums) {
            cur = Math.max(preOne,preTwo+num);
            preTwo = preOne;
            preOne = cur;
        }
        return cur;
    }
}
//房屋是环状的
//第一个房间和最后一个房间只能选择一个
//计算选第一个房间时得到的最大值
//计算选最后一个房间时得到的最大值
//取两者中最大的
//当前i个房间的最大值
//dp[i]=max(dp[i-1],dp[i-2]+nums[i])
//为了节省空间，可以不用保存dp[i-1]和dp[i-2]
//用中间变量存起来
