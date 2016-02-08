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


public class Solution {
    
    // O(log(n)) time O(1) space
    //    2(successor)
    //  p    3
    // 
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        //if (root == null) return null;
        TreeNode successor = null;
        while (root != null && root.val != p.val) {
            if (root.val > p.val) {
                successor = root; // record the lowest upper bound
                root = root.left;
            } else {
                root = root.right;
            }
        }
        if (root == null) return null; // if p cannot be found in tree
        if (root.right == null) return successor;
        return findLeftMostChild(root.right);
    }
    
    
    // Generic Binary tree solution:
    // Solution 1:  O(n) time O(n) space - naive list solution
    public TreeNode inorderSuccessorNaive(TreeNode root, TreeNode p) {
        if (root == null || p == null) return null;
        List<TreeNode> list = new ArrayList<>();
        inOrder(root, list);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == p && i < list.size() - 1) {
                return list.get(i + 1);
            }
        }
        return null;
    }
    
    public void inOrder(TreeNode root, List<TreeNode> list) {
        if (root == null) return;
        inOrder(root.left, list);
        list.add(root);
        inOrder(root.right, list);
    }
    
    // Solution 2: O(n) time O(lgn) space - slightly better, stack iterator solution
    public TreeNode inorderSuccessorStack(TreeNode root, TreeNode p) {
        TreeNode top = root;
        Stack<TreeNode> stack = new Stack<>();
        while (top != null || !stack.isEmpty()) {
            while (top != null) {
                stack.push(top);
                top = top.left;
            }
            top = stack.pop();
            if (top == p) {
                if (top.right != null) {
                    return findLeftMostChild(top.right);
                } else {
                    if (stack.isEmpty())
                        return null;
                    else 
                        return stack.peek();
                }
            } else {
                top = top.right;
            }
        }
        return null;
    }
    
    public TreeNode findLeftMostChild(TreeNode root) {
        TreeNode curr = root;
        while (curr != null && curr.left != null) {
            curr = curr.left;
        }
        return curr;
    }
    
}