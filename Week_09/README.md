学习笔记
## 不同路径Ⅱ ##
有障碍物时，当前点路径数为0<br>

	if(obstacleGrid[i][j]==1) {
        dp[i][j] = 0;
    } else {
        dp[i][j] = dp[i-1][j] + dp[i][j-1];
    }

