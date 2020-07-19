package com.swpu.jike.Islands;

public class Solution {
    private int m;
    private int n;
    public int numIslands(char[][] grid) {
        int count=0;
        m = grid.length;
        if(m==0) return count;
        n = grid[0].length;
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(grid[i][j]=='1') {
                    count++;
                    gridMark(grid,i,j);
                }
            }
        }
        return count;
    }
    private void gridMark(char[][] grid,int i,int j) {
        if(i<0||i>=m||j<0||j>=n||grid[i][j]!='1') return;
        grid[i][j]='0';
        gridMark(grid,i-1,j);
        gridMark(grid,i+1,j);
        gridMark(grid,i,j-1);
        gridMark(grid,i,j+1);
    }
}

//两个循环遍历
//碰见一个1，就把它周围及周围的周围的1全部变成0，且岛屿+1