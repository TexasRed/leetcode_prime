package maximum_size_subarray_sum_equals_to_k;

/*
 *325. Maximum Size Subarray Sum Equals k My Submissions Question
Total Accepted: 1842 Total Submissions: 4592 Difficulty: Easy
Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.

Example 1:
Given nums = [1, -1, 5, -2, 3], k = 3,
return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)

Example 2:
Given nums = [-2, -1, 2, 1], k = 1,
return 2. (because the subarray [-1, 2] sums to 1 and is the longest)

Follow Up:
Can you do it in O(n) time?
 */

public class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        int n = nums.length;
        int[] preSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }
        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (preSum[i + 1] - preSum[j] == k)
                    maxLen = Math.max(maxLen, i - j + 1);
            }
        }
        return maxLen;
    }
}

// O(n) time
public class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        if (nums==null || nums.length==0) return 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, -1);
        int sum = 0;
        int maxLen = Integer.MIN_VALUE;
        for (int i=0; i<nums.length; i++) {
            sum += nums[i];
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
            if (map.containsKey(sum-k)) {
                int index = map.get(sum-k);
                maxLen = Math.max(maxLen, i-index);
            }
        }
        return maxLen==Integer.MIN_VALUE? 0 : maxLen;
    }
}
