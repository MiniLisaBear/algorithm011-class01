package com.swpu.jike.week07;

public class ParentCircle {
    public int findCircleNum(int[][] M) {
        //针对n个学生创建了n个并查集
        int n = M.length;
        UnionFind uf = new UnionFind(n);
        for (int i=0;i<n-1;i++) {
            for (int j=i+1;j<n;j++) {
                //当两个学生有关系时，就合并两个并查集
                if (M[i][j]==1) uf.union(i,j);
            }
        }
        return uf.returnCount();
    }

    public static void main(String[] args) {
        int[][] M = {{1,1,0},{1,1,0},{0,0,1}};
        ParentCircle pc = new ParentCircle();
        int num = pc.findCircleNum(M);
        System.out.println(num);
    }
}
