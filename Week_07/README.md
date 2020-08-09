学习笔记
## 字典树Trie ##
**出现原因**<br>
例如模糊查询，需要根据第一个字搜索出开头为这个字的所有词语，即前缀感应出整个单词<br>
**数据结构**<br>
字典树，即Trie树，又称单词查找树或键树，是一种树形结构。典型应用是用于统计和排序大量的字符串（但不仅限于字符串），所以经常被搜索引擎系统用于文本词频统计<br>
**基本性质**<br>
节点本身不存完整单词<br>
从根节点到某一节点，路径上经过的字符连起来，为该节点对应的字符串<br>
每个节点的所有子节点路径代表的字符都不相同<br>
可以用节点存储额外的信息，如频次<br>
**核心思想**<br>
Trie树的核心思想是空间换时间<br>
利用字符串的公共前缀来降低查询时间的开销以达到提高效率的目的.<br>
**业务功能**<br>
例如，实现搜索引擎中，输入部分字符会显示出同类的一堆字符串，可以用带频次的trie树存储，然后直接根据输入的前缀查询，返回频次前10的就可以满足条件。<br>
**Trie树代码**<br>

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

## 并查集Disjoint Set ##
**适用场景**<br>
组团、配对问题<br>
社交网络里面你和他是不是朋友<br>
判断两个群组是不是一个群组/快速合并群组<br>
**基本操作**<br>
makeSet(s)：建立一个新的并查集，其中包含s个单元素集合<br>
unionSet(x,y)：把元素x和元素y所在的集合合并，要求x和y所在的集合不相交，如果相交则不合并<br>
find(x)：找到元素x所在的集合的代表，该操作也可以用于判断两个元素是否位于同一集合，只要将它们各自的代表比较一下就可以了<br>
**代码实现**<br>
初始化:每个元素拥有一个parent的数组指向自己，表示它自己就是自己的集合<br>
查询:查询就看元素的parent，一直往上，直到parent为自己，就找到了这个集合<br>
合并:找出两个集合的领头元素（自己指向自己的元素），然后把其中的一个parent指向另外一个<br>
路径压缩:调用find时，将路径上所有的元素都指向a<br>

	public class UnionFind {
    private int count = 0;
    private int[] parent;
    public UnionFind(int n){
        count = n;
        parent = new int[count];
        for(int i=0;i<count;i++) {
            parent[i] = i;
        }
    }
    public int find(int p) {
        while (p!=parent[p]) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }
    public void union(int p,int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP==rootQ) return;
        parent[rootP] = rootQ;
        count--;
    }
    public int returnCount(){
        return count;
    }
	}

## 高级搜索 ##
**Two-End-BFS双向搜索模板**<br>
从两头分别开始走，在某个层相遇时，得到的就是需要的结果<br>

	public int twoEndBFS(){
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