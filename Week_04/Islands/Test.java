package com.swpu.jike.Islands;

public class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        char[][] grid = {
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}
        };
        int result = solution.numIslands(grid);
        System.out.println(result);
    }
}
