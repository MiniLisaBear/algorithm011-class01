package com.swpu.jike.week08;

public class Sort {
    //选择排序--从后面找一个最小的放当前位置
    public int[] searchSort(int[] nums) {
        int len = nums.length;
        int minIndex,temp;
        for (int i=0;i<len-1;i++) {
            minIndex = i;
            for (int j=i+1;j<len;j++) {//找到后面的一个最小值
                if (nums[j] < nums[minIndex]) {
                    minIndex = j;
                }
            }
            temp = nums[i];
            nums[i] = nums[minIndex];
            nums[minIndex] = temp;
        }
        return nums;
    }
    //插入排序--取出元素从当前元素的前一个往前（前面已经有序）扫描找到插入位置
    public int[] insertSort(int[] nums) {
        int len = nums.length;
        int preIndex,current;
        for (int i=1;i<len;i++) {
            preIndex = i-1;
            current = nums[i];
            while (preIndex>=0 && nums[preIndex]>current) {
                nums[preIndex+1] = nums[preIndex];
                preIndex--;
            }
            nums[preIndex+1] = current;
        }
        return nums;
    }
    //冒泡排序
    public int[] bubbletSort(int[] nums) {
        int len = nums.length;
        int temp;
        for (int i=0;i<len-1;i++) {
            for (int j=0;j<len-1-i;j++) {
                if (nums[j]>nums[j+1]) {
                    temp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = temp;
                }
            }
        }
        return nums;
    }
    //快排
    public int[] quickSortMain(int[] nums) {
        quickSort(nums,0,nums.length-1);
        return nums;
    }
    private void quickSort(int[] nums,int lo,int hi) {
        if (lo>=hi) return;
        int pivot = partitopn(nums,lo,hi);
        quickSort(nums,lo,pivot-1);
        quickSort(nums,pivot+1,hi);
    }
    private int partitopn(int[] nums,int lo,int hi) {
        int temp = nums[lo];
        while (lo<hi){
            while (lo<hi && nums[hi]>=temp) hi--;
            nums[lo] = nums[hi];
            while (lo<hi && nums[lo] <= temp) lo++;
            nums[hi] = nums[lo];
        }
        nums[lo] = temp;
        return lo;
    }
    //归并
    public int[] mergeSortMain(int[] nums) {
        mergeSort(nums,0,nums.length-1);
        return nums;
    }
    private void mergeSort(int[] nums,int lo,int hi) {
        if (lo>=hi) return;
        int mid = (lo+hi)/2;
        mergeSort(nums,lo,mid);
        mergeSort(nums,mid+1,hi);
        mergeOne(nums,lo,mid,hi);
    }
    private void mergeOne(int[] nums,int lo,int mid,int hi) {
        int[] temp = new int[hi-lo+1];
        int i=lo,j=mid+1,k=0;
        while (i<=mid && j<=hi) {
            temp[k++] = nums[i]<=nums[j]?nums[i++]:nums[j++];
        }
        while (i<=mid) temp[k++] = nums[i++];
        while (j<=hi) temp[k++] = nums[j++];
        for (int p=0;p<temp.length;p++) {
            nums[lo+p] = temp[p];
        }
    }
    //堆排序
    public void heapSort(int[] nums) {
        if (nums.length==0) return;
        int len = nums.length;
        for (int i=len/2-1;i>=0;i--)
            heapify(nums,len,i);
        for (int i=len-1;i>=0;i--) {
            int temp = nums[0];
            nums[0] = nums[i];
            nums[i] = temp;
            heapify(nums,i,0);
        }
    }
    public void heapify(int[] nums,int len,int i) {
        int left = 2*i+1,right = 2*i+2;
        int largest = i;
        if (left<len && nums[left] > nums[largest]) {
            largest = left;
        }
        if (right < len && nums[right] > nums[largest]) {
            largest = right;
        }
        if (largest!=i){
            int temp = nums[i];
            nums[i] = nums[largest];
            nums[largest] = temp;
            heapify(nums,len,largest);
        }
    }

}
