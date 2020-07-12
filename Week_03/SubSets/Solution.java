package com.swpu.jike.SubSets;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(nums,result,new ArrayList<>(),0);
        return result;
    }
    void dfs(int[] nums,List<List<Integer>> result,List<Integer> temp,int index) {
        if(index==nums.length) {
            result.add(new ArrayList<>(temp));
            return;
        }
        dfs(nums,result,temp,index+1);//不加入新数，得到一个list
        temp.add(nums[index]);//加入一个新数
        dfs(nums,result,temp,index+1);//寻找加入新数可能得到的list

        temp.remove(temp.size()-1);//移除最后一个元素，寻找其他集合
        //如[1],移除1，才能找到[2]，否则就是[1,2]
    }
}
