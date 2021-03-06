package two_sum_ii;
/*
 167. Two Sum II - Input array is sorted My Submissions Question
Total Accepted: 8105 Total Submissions: 17464 Difficulty: Medium
Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.

You may assume that each input would have exactly one solution.

Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2
 
 */


public class Solution {
    public int[] twoSum(int[] numbers, int target) {
        
        int[] res = new int[2];
        if (numbers == null || numbers.length == 0)
            return res;
        
        int n = numbers.length;
        for (int i = 0, j = n - 1; i < j;) {
            int sum = numbers[i] + numbers[j];
            if (sum == target) {
                res[0] = i + 1;
                res[1] = j + 1;
                break;
            } else if (sum < target) {
                i++;
            } else {
                j--;
            }
        }
        
        return res;
    }
}

public class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int[] res = new int[2];
        if (numbers == null || numbers.length < 2) return res;
        int n = numbers.length;
        for (int i = 0, j = n - 1; i < j;) {
            int sum = numbers[i] + numbers[j];
            if (sum == target) {
                res[0] = i + 1;
                res[1] = j + 1;
                return res;
            } else if (sum > target) {
                j--;
            } else {
                i++;
            }
        }
        return res;
    }
}
