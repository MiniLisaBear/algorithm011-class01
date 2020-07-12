学习笔记
## 递归模板 ##

	public void recur(int level,int param) {
		//出口
		if(level>MAX_LEVEL) {
			return;
		}
		
		//process current logic
		process(level,param);
		
		//dirll down
		recur(level:level+1,param);
		
		//restore current status
	}

例题：递归方式实现二分查找

	public int binary(int[] nums,int target,int low,int high) {
		//二分出口，low>high，或target小于最小的，大于最大的
		if(target>nums[nums.length-1]||target<nums[0]||low>high) return -1;
		int mid = (low+hign)/2;
		if(nums[mid]<target) {
			//drill down
			return binary(nums,target,mid+1,high);	
		} else if(nums[mid]>target) {
			//drill down
			return binary(nums,target,low,mid-1);
		} else {
			//process current
			return mid;
		}
	}

## 分治模板 ##
找重复性，分解问题，组合每个子问题结果<br>

	def divide_conquer(problems,param1,param2,...):
		#出口
		if problems is None:
			print_result
			return
		# prepare data
		data = prepare(probles)
		subproblem = split_problems(problem,data)
		
		# conquer subproblem
		subresult1 = self.divide_conquer(subproblems[0],p1,...)
		subresult2 = self.divide_conquer(subproblems[1],p1,...)
		subresult3 = self.divide_conquer(subproblems[2],p1,...)

		...
		# process and generate the final result
		result = process_result(subresult1,subresult2,subresult3)
		
		# rever the current level states
		# 改变当前层的状态