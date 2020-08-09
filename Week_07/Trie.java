package com.swpu.jike.week07;

public class Trie {
    Trie[] next;
    boolean isEnd;
    public Trie(){
        next = new Trie[26];
        isEnd = false;
    }
    public void insert(String word) {
        Trie cur = this;
        for(char ch: word.toCharArray()) {
            if (cur.next[ch-'a']==null) {
                cur.next[ch-'a'] = new Trie();
            }
            cur = cur.next[ch-'a'];
        }
        cur.isEnd = true;
    }
    public boolean search(String word) {
        Trie cur = this;
        for (char ch:word.toCharArray()) {
            if (cur.next[ch-'a']==null) return false;
            cur =cur.next[ch-'a'];
        }
        return cur.isEnd;//查找，必须到end才返回true
    }
    public boolean searchPreix(String word) {
        Trie cur = this;
        for (char ch:word.toCharArray()) {
            if (cur.next[ch-'a']==null) return false;
            cur =cur.next[ch-'a'];
        }
        return true;//找前缀，不用到end，只要正常循环完输入的word就返回true
    }
}
