package com.swpu.jike.week09;

public class Solution {
    //转换成小写字母
    public String toLowerCase(String str) {
        String res = "";
        char s;
        for (int i=0; i<str.length();i++) {
            s = str.charAt(i);
            if (str.charAt(i)>='A'&&str.charAt(i)<='Z') {
                s = (char)(str.charAt(i)+32);
            }
            res += s;
        }
        return res;
    }
    //字符串转换整数 (atoi)
    public int myAtoi(String str) {
        int index = 0, sign = 1, total = 0;
        if (str.length() == 0) return 0;
        //去除空格
        while (index < str.length() && str.charAt(index) == ' ') {
            index++;
        }
        if ( index < str.length() && (str.charAt(index) == '+' || str.charAt(index) == '-')) {
            sign = str.charAt(index) == '+' ? 1 : -1;
            index++;
        }
        while (index<str.length()) {
            int digit = str.charAt(index) - '0';
            if (digit < 0 || digit > 9) break;
            if (Integer.MAX_VALUE / 10 < total || Integer.MAX_VALUE / 10==total
            && Integer.MAX_VALUE % 10 < digit)
                return sign == 1 ? Integer.MAX_VALUE:Integer.MIN_VALUE;

            total = 10 * total + digit;
            index++;
        }
        return total * sign;
    }
    //最长公共前缀
    public String longestCommonPrefix(String[] strs) {
        if (strs==null||strs.length==0) return "";
        for (int i=0;i<strs[0].length();i++) {
            for (int j=1;j<strs.length;j++) {
                if (i==strs[j].length() || strs[0].charAt(i)!=strs[j].charAt(i)) {
                    return strs[0].substring(0,i);
                }
            }
        }
        return strs[0];
    }
    //最长上升子序列，子序列可以不连续，子串必须连续
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length+1];
        dp[0] = 1;
        int len = nums.length;
        if(len==0) return 0;
        int maxValue = 1;
        for(int j=1;j<len;j++) {
            int cur = 0;
            for(int i=0;i<j;i++) {
                if(nums[i]<nums[j]) {
                    cur = Math.max(cur,dp[i]);
                }
            }
            dp[j] = cur + 1;
            maxValue = Math.max(dp[j],maxValue);
        }
        return maxValue;
    }
}
