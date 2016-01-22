package wiggle_sort;

/*
280. Wiggle Sort My Submissions Question
Total Accepted: 6312 Total Submissions: 12973 Difficulty: Medium
Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....

For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].
*/

public class Solution {
    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length <= 1)
            return;
        if (nums.length == 2) {
            if (nums[0] > nums[1])
                swap(nums, 0, 1);
            return;
        }
        
        int n = nums.length;
        for (int i = 1; i < n; i += 2) {
            if (i + 1 < n) {
                if (nums[i] >= Math.max(nums[i - 1], nums[i + 1]))
                    continue;
                else if (nums[i - 1] > nums[i + 1])
                    swap(nums, i - 1, i);
                else
                    swap(nums, i, i + 1);
            } else {
                if (nums[i] < nums[i - 1])
                    swap(nums, i - 1, i);
                    break;
            }
        }
    }
    
    public void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}