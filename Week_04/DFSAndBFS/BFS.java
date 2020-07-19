package com.swpu.jike.DFSAndBFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS {//也可以理解为层次遍历
    public List<List<Integer>> levelOrder(TreeNode root){
        List<List<Integer>> allResults = new ArrayList<>();
        if(root==null) return  allResults;
        Queue<TreeNode> nodes = new LinkedList<>();
        nodes.offer(root);
        while (!nodes.isEmpty()) {
            int size = nodes.size();//当前层size
            List<Integer> temp = new ArrayList<>();
            for (int i=0;i<size;i++) {
                TreeNode node = nodes.poll();
                temp.add(node.val);
                if (node.left!=null) nodes.offer(node.left);
                if (node.right!=null) nodes.offer(node.right);
            }
            allResults.add(temp);
        }
        return allResults;
    }
}
