package bst_longest_consecutive_sequence;

/*
 *298. Binary Tree Longest Consecutive Sequence My Submissions Question
Total Accepted: 4074 Total Submissions: 11782 Difficulty: Medium
Given a binary tree, find the length of the longest consecutive sequence path.

The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The longest consecutive path need to be from parent to child (cannot be the reverse).

For example,
   1
    \
     3
    / \
   2   4
        \
         5
Longest consecutive sequence path is 3-4-5, so return 3.
   2
    \
     3
    / 
   2    
  / 
 1
Longest consecutive sequence path is 2-3,not3-2-1, so return 2.
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
    int maxLenLocal;
    int maxLenGlobal;
    public ResultType(int maxLenLocal, int maxLenGlobal) {
        this.maxLenLocal = maxLenLocal;
        this.maxLenGlobal = maxLenGlobal;
    }
}
 
public class Solution {
    public int longestConsecutive(TreeNode root) {
        ResultType res = helper(root);
        return res.maxLenGlobal;
    }
    
    public ResultType helper(TreeNode root) {
        if (root == null)
            return new ResultType(0, 0);
        if (root.left == null && root.right == null)
            return new ResultType(1, 1);
        
        ResultType left = helper(root.left);
        ResultType right = helper(root.right);
        
        int maxLenLocal = 1;
        if (root.left != null && root.left.val == root.val + 1) {
            maxLenLocal = Math.max(maxLenLocal, left.maxLenLocal + 1);
        }
        if (root.right != null && root.right.val == root.val + 1) {
            maxLenLocal = Math.max(maxLenLocal, right.maxLenLocal + 1);
        }
        
        int maxLenGlobal = Math.max(Math.max(left.maxLenGlobal, right.maxLenGlobal), maxLenLocal);
        
        return new ResultType(maxLenLocal, maxLenGlobal);
    }
}
