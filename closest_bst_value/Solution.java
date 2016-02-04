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
    
    // Recursive BST search O(h) time, O(h) space
    public int closestValue(TreeNode root, double target) {
        TreeNode subtree = (target > root.val) ? root.right : root.left;
        if (subtree == null) return root.val;
        int subClosest = closestValue(subtree, target);
        return Math.abs((double) root.val - target) < Math.abs((double) subClosest - target) ? root.val : subClosest;
    }
    
    // Iterative BST search O(h) time, O(h) space
    public int closestValueIterative(TreeNode root, double target) {
       double closest = Double.MAX_VALUE;
       while (root != null) {
           double currDiff = Math.abs((double) root.val - target);
           if (currDiff < Math.abs(closest - target)) {
               closest = (double) root.val;
           }
           if ((double) root.val == target) {
               return root.val;
           } else if ((double) root.val < target) {
               root = root.right;
           } else {
               root = root.left;
           }
       }
       return (int) closest;
    } 
    
    // Iterate the tree by stack O(n) time O(lgn) space
    public int closestValueNaive(TreeNode root, double target) {
        TreeNode result = null;
        TreeNode top = root;
        double minDiff = Double.MAX_VALUE;
        Stack<TreeNode> stack = new Stack<>();
        while (top != null || !stack.isEmpty()) {
            while (top != null) {
                stack.push(top);
                top = top.left;
            }
            top = stack.pop();
            double diff = Math.abs((double) top.val - target);
            if (diff == 0) return top.val;
            if (diff < minDiff) {
                minDiff = diff;
                result = top;
            }
            top = top.right;
        }
        return result.val;
    }

    // pre-order traversal O(n) time O(n) space
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