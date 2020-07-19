学习笔记
## 深度搜索和广度搜索 ##
每个节点访问一次且仅访问一次
### DFS模板 ###

	public List<List<Integer>> levelOrder(TreeNode root){
        List<List<Integer>> allResults = new ArrayList<>();
        if(root==null) return  allResults;
        travel(root,0,allResults);
        return allResults;
    }
    private void travel(TreeNode root,int level,List<List<Integer>> results) {
        if(results.size()==level) {
            results.add(new ArrayList<>());//相等，表示到达新的一层
        }
        results.get(level).add(root.val);//往当前层存数
        if(root.left!=null) travel(root.left,level+1,results);
        if (root.right!=null) travel(root.right,level+1,results);
    }

### BFS模板 ###

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

## 贪心算法 ##
每一步都采取当前状态下最优的选择，从而希望达到全局最优，现实不常用，不能保证局部最优就是全局最优。
<br><br>
贪心：当下做局部最优判断<br>
回溯：能够回退<br>
动态规划：最优判断+回溯<br><br>
适用贪心场景:
问题能够分解成求最优子结构

## 二分查找 ##
**前提**<br>
目标函数单调性---单调增或单调减<br>
存在上下界---有范围<br>
能够通过索引访问---数组，通常单链表不行（跳表可以）<br>

**实现代码**

	public int binary1(int[] nums,int target) {
        int low = 0;
        int high = nums.length-1;
        int mid = 0;
        if (nums[low]>target||nums[high]<target) return -1;
        while (low<=high) {
            mid = (low+high)/2;
            if(nums[mid]>target) {
                high = mid - 1;
            } else if (nums[mid]<target) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public int binaryRecur(int[] nums,int target,int low,int high) {
        if(nums[low]>target||nums[high]<target||low>high) return -1;
        int mid = (low+high)/2;
        if (nums[mid]>target) {
            binaryRecur(nums,target,low,mid-1);
        } else if(nums[mid]<target) {
            binaryRecur(nums,target,mid+1,high);
        } else {
            return mid;
        }
    }