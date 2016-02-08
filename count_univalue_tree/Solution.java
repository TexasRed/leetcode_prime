package count_univalue_tree;

/*
 *
 250. Count Univalue Subtrees My Submissions Question
Total Accepted: 3923 Total Submissions: 11189 Difficulty: Medium
Given a binary tree, count the number of uni-value subtrees.

A Uni-value subtree means all nodes of the subtree have the same value.

For example:
Given binary tree,
              5
             / \
            1   5
           / \   \
          5   5   5
return 4.
 *
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
    int count;
    boolean isUnival;
    public ResultType(int count, boolean isUnival) {
        this.count = count;
        this.isUnival = isUnival;
    }
}

public class Solution {
    public int countUnivalSubtrees(TreeNode root) {
        return helper(root).count;   
    }
    
    public ResultType helper(TreeNode root) {
        if (root == null)
            return new ResultType(0, true);
        ResultType rt1 = helper(root.left);
        ResultType rt2 = helper(root.right);
        int count = rt1.count + rt2.count;
        if (rt1.isUnival && rt2.isUnival) {
            boolean isUnival = false;
            if (root.left == null && root.right == null) {
                count++;
                isUnival = true;
            } else if (root.left == null) {
                if (root.val == root.right.val) {
                    count++;
                    isUnival = true;
                }
            } else if (root.right == null) {
                if (root.val == root.left.val) {
                    isUnival = true;
                    count++;
                }
            } else {
                if (root.val == root.left.val && root.val == root.right.val) {
                    isUnival = true;
                    count++;
                }
            }
            return new ResultType(count, isUnival);
        } else {
            return new ResultType(count, false);
        }
    }
}


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
    boolean isUnivalue;
    int count;
    public ResultType(boolean isUnivalue, int count) {
        this.isUnivalue = isUnivalue;
        this.count = count;
    }
}
 
public class Solution {
    public int countUnivalSubtrees(TreeNode root) {
        return helper(root).count;
    }
    
    public ResultType helper(TreeNode root) {
        if (root == null) return new ResultType(true, 0);
        ResultType rt1 = helper(root.left);
        ResultType rt2 = helper(root.right);
        int count = rt1.count + rt2.count;
        boolean isUnivalue = false;
        if (rt1.isUnivalue && rt2.isUnivalue) {
           isUnivalue = checkUnivalue(root);
           if (isUnivalue) count++;
        }
        return new ResultType(isUnivalue, count);
    }
    
    public boolean checkUnivalue(TreeNode root) {
        boolean isUnivalue = false;
        if (root == null) return true;
        if (root.left == null && root.right == null)
            isUnivalue = true;
        else if (root.right == null) {
            if (root.val == root.left.val)
                isUnivalue = true;
        } else if (root.left == null) {
            if (root.val == root.right.val)
                isUnivalue = true;
        } else {
            if (root.val == root.left.val && root.val == root.right.val)
                isUnivalue = true;
        }
        return isUnivalue;
    }
}
