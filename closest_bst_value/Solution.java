package closest_bst_value;

/*
 * 
 270. Closest Binary Search Tree Value My Submissions Question
Total Accepted: 7250 Total Submissions: 22297 Difficulty: Easy
Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

Note:
Given target value is a floating point.
You are guaranteed to have only one unique value in the BST that is closest to the target.
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class ResultType {
    double min;
    int val;
    public ResultType(double min, int val) {
        this.min = min;
        this.val = val;
    }
}
public class Solution {
    
    public int closestValue(TreeNode root, double target) {
       ResultType rt = helper(root, target, Double.MAX_VALUE, -1);
       return rt.val;
    }
    
    public ResultType helper(TreeNode root, double target, double currMin, int val) {
        if (root == null)
            return new ResultType(currMin, val);
            
        double diff = Math.abs((double)(root.val) - target);
        if (diff < currMin) {
            currMin = diff;
            val = root.val;
        }
        
        ResultType rt1 = helper(root.left, target, currMin, val);
        ResultType rt2 = helper(root.right, target, currMin, val);
        
        if (Math.min(rt1.min, rt2.min) < currMin) {
            if (rt1.min < rt2.min) {
                currMin = rt1.min;
                val = rt1.val;
            } else {
                currMin = rt2.min;
                val = rt2.val;
            }
        }
        
        return new ResultType(currMin, val);
    }
    
}