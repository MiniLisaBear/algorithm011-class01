package com.swpu.jike.week08;

public class Test {
    public static void main(String[] args) {
//        MergeIntervals mi = new MergeIntervals();
//        int[][] a = {{1,3},{2,6},{8,10},{15,18}};
//        int[][] merge = mi.merge(a);
//        for (int[] res : merge) {
//            System.out.println(""+res[0]+","+res[1]);
//        }
        int[] num = {3,2,1,4,6,5};
        Sort s = new Sort();
        int[] res = s.mergeSortMain(num);
        for (int x:res) {
            System.out.println(x);
        }
    }
}
