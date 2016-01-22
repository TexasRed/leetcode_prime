package binary_tree_upside_down;

/*

156. Binary Tree Upside Down My Submissions Question
Total Accepted: 7587 Total Submissions: 20706 Difficulty: Medium
Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node) or empty, flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root.

For example:
Given a binary tree {1,2,3,4,5},
    1
   / \
  2   3
 / \
4   5
return the root of the binary tree [4,5,2,#,#,3,1].
   4
  / \
 5   2
    / \
   3   1  
confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.

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
    TreeNode root;
    TreeNode rightMost;
    public ResultType(TreeNode root, TreeNode rightMost) {
        this.root = root;
        this.rightMost = rightMost;
    }
}

public class Solution {
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null || root.left == null && root.right == null) {
            return root;
        }
        ResultType rt = helper(root);
        return rt.root;
    }
    
    public ResultType helper(TreeNode root) {
        if (root.left == null && root.right == null) {
            return new ResultType(root, root);
        }
        ResultType rt = helper(root.left);
        rt.rightMost.left = root.right;
        root.left = root.right = null;
        rt.rightMost.right = root;
        return new ResultType(rt.root, root);
    }
}