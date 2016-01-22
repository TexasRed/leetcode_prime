package inorder_successor_in_bst;

/*
 *285. Inorder Successor in BST My Submissions Question
Total Accepted: 4865 Total Submissions: 14078 Difficulty: Medium
Given a binary search tree and a node in it, find the in-order successor of that node in the BST.

Note: If the given node has no in-order successor in the tree, return null.
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
public class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode successor = null;
        while (root != null && root.val != p.val) {
            if (root.val > p.val) {
                successor = root;
                root = root.left;
            } else {
                root = root.right;
            }
        }
        if (root == null) {
            return null;
        }
        
        if (root.right == null) {
            return successor;
        }
        
        root = root.right;
        while (root.left != null) {
            root = root.left;
        }
        return root;
        
    }
}