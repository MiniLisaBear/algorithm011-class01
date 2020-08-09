package com.swpu.jike.week07;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList_) {
        Set<String> wordList = new HashSet<>(wordList_);//换成hashset,查询O(1)
        Set<String> beginSet = new HashSet<>();//从beginWord扩撒
        Set<String> endSet = new HashSet<>();//从endWord扩散
        if(!wordList.contains(endWord)) return 0;

        int len = 1;
        int strLen = beginWord.length();
        HashSet<String> visited = new HashSet<>();//bfs过程中标记节点被访问

        beginSet.add(beginWord);
        endSet.add(endWord);
        while(!beginSet.isEmpty()&&!endSet.isEmpty()) {//这里的queue用set表示的
            //每次扩散只扩散一个set，所以优先选择小的set
            //beginSet和endSet交换
            if(beginSet.size()>endSet.size()) {
                Set<String> set = beginSet;
                beginSet = endSet;
                endSet = set;
            }
            Set<String> temp = new HashSet<String>();
            for(String word : beginSet) {
                char[] chs = word.toCharArray();
                for(int i = 0; i < chs.length; i++) {//分别对每一个字符进行替换判断
                    for (char c = 'a'; c <= 'z'; c++) {
                        char old = chs[i];
                        chs[i] = c;
                        String target = String.valueOf(chs);

                        if (endSet.contains(target)) {//相交了
                            return len + 1;
                        }

                        //没有遇到
                        if (!visited.contains(target) && wordList.contains(target)) {//当前单词没有用过，来自wordList
                            temp.add(target);//加入下一次扩散的队列
                            visited.add(target);
                        }
                        chs[i] = old;//单词换成原来的
                    }
                }
            }
            beginSet = temp;
            len++;//新增一个单词，深度加1
        }
        return 0;
    }
}
//双向BFS
//粗略一看，好像每次都只在扩散beginSet，其实不是，当beginSet的size大于了endSize时，就进行了交换
//代码里面虽然扩散的仍然是beginSet，但是实际是endSet在变化了