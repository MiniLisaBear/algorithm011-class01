package com.swpu.jike.Cookies;

import java.util.Arrays;

public class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int j = 0;
        int count = 0;
        int i = 0;
        while(i<g.length&&j<s.length) {
            if(s[j]>=g[i]) {//饼干满足
                i++;
                j++;
                count++;
            } else {//饼干不满足
                j++;
            }
        }
        return count;
    }
}
//把胃口排序，饼干排序
//依次用小胃口和小饼干去匹配
//同时满足饼干大于等于胃口