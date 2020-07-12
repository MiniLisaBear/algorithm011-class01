package com.swpu.jike.SubSets;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1,2,3,4};
        List<List<Integer>> result = solution.subsets(nums);
        for (List<Integer> list:result) {
            System.out.println(list.toString());
        }
    }
}
