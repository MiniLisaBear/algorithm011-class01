学习笔记
## 动态规划 ##
动态规划问题的一般形式就是求最值或最优解<br>
分治+最优子结构<br>
需要状态的存储数组，存储每一步的最优值<br>
**关键点**<br>
动态规划和递归或者分治没有根本上的区别（关键看有无最优子结构）<br>
共性：找到重复子问题<br>
差异性：最优子结构、中途可以淘汰次优解<br>
找最优子结构；存储中间状态；递推公式/状态转移方程<br>
**小结**<br>
打破自己的思维惯性，形成机器思维-----找重复性，机器只会if-else/循环/递归<br>
理解复杂逻辑的关键<br>
也是职业进阶的要点要领<br>
**MIT五步法dp**<br>
define subproblems---定义子问题/分治<br>
guess（part of solution）---猜递推方程<br>
relate subproblem solution---把子问题的解合并起来，merge<br>
recurse&memorize OR build DP table bottom-up--递归&记忆化或把dp的状态表建立起来，自底向上进行递推<br>
solve origin problem---冗余<br>