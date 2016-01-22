package three_sum_smaller;

/*
 *259. 3Sum Smaller My Submissions Question
Total Accepted: 5786 Total Submissions: 15535 Difficulty: Medium
Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.

For example, given nums = [-2, 0, 1, 3], and target = 2.

Return 2. Because there are two triplets which sums are less than 2:

[-2, 0, 1]
[-2, 0, 3]
Follow up:
Could you solve it in O(n2) runtime?
 */

public class Solution {
    public int threeSumSmaller(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;
        Arrays.sort(nums);
        int n = nums.length;
        
        int count = 0;
        for (int i = 0; i < n - 1; i++) {
            count += twoSumSmaller(nums, i + 1, n - 1, target - nums[i]);
        }
        return count;
    }
    
    public int twoSumSmaller(int[] A, int start, int end, int target) {
        int count = 0;
        for (int i = start, j = end; i < j;) {
            if (A[i] + A[j] < target) {
                count += j - i;
                i++;
            } else {
                j--;
            }
        }
        return count;
    }
    
}