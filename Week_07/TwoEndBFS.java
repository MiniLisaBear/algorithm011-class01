package com.swpu.jike.week07;

import java.util.HashSet;

public class TwoEndBFS {
    public int ladderLength(){
        //定义2个set，set查询O(1)，也可用Queue
        HashSet<String> beginSet = new HashSet<>();//从前往后扩散
        HashSet<String> endSet = new HashSet<>();//从后往前

        //定义bfs搜索的层
        int len = 1;
        //定义节点访问标记,访问了就加进去
        HashSet<String> visited = new HashSet<>();
        //初始值加入两个set
        beginSet.add("");
        endSet.add("");
        //不空时进行搜索
        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            //每次扩散只操作一个set，优先选择小的
            //实际是两个set都在变化，只是将变化的过程交给begin
            if (beginSet.size() > endSet.size()) {
                HashSet<String> set = new HashSet<>();
                set = beginSet;
                beginSet = endSet;
                endSet = set;
            }
            //变量存储变化过程中得到的中间结果
            HashSet<String> temp = new HashSet<>();
            //针对beginSet的每个值进行操作
            for (String word : beginSet) {
                //逻辑操作得到一个中间节点值
                //这个值同时在endSet找到了，表明相遇了
                if (endSet.contains("")) {
                    return len + 1;
                }
                //没有相遇时，判断当前节点是否访问及其他判断
                if (!visited.contains("")) {
                    temp.add("");//加入下一次扩散的队列中
                    visited.add("");//标记访问
                }
            }
            //beginSet操作完成后，得到一个新的扩散队列，重新赋给beginSet
            beginSet = temp;
            //进入下一层搜索
            len++;
        }
        return 0;
    }
}
