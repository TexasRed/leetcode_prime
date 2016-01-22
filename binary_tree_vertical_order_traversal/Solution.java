package binary_tree_vertical_order_traversal;


/*
 *314. Binary Tree Vertical Order Traversal My Submissions Question
Total Accepted: 2037 Total Submissions: 6771 Difficulty: Medium
Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).

If two nodes are in the same row and column, the order should be from left to right.

Examples:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its vertical order traversal as:
[
  [9],
  [3,15],
  [20],
  [7]
]
Given binary tree [3,9,20,4,5,2,7],
    _3_
   /   \
  9    20
 / \   / \
4   5 2   7
return its vertical order traversal as:
[
  [4],
  [9],
  [3,5,2],
  [20],
  [7]
]
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
    int level;
    public ResultType(TreeNode root, int level) {
        this.root = root;
        this.level = level;
    }
}

public class Solution {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();
        
        dfs(root, map, 0);
        bfs(root, map);
        
        for (List<Integer> list : map.values()) {
            result.add(list);
        }
        return result;
    }
    
    public void dfs(TreeNode root, Map<Integer, List<Integer>> map, int level) {
        if (root == null) return;
        if (!map.containsKey(level)) {
            map.put(level, new ArrayList<>());
        }
        dfs(root.left, map, level - 1);
        dfs(root.right, map, level + 1);
    }
    
    public void bfs(TreeNode root, Map<Integer, List<Integer>> map) {
        if (root == null) return;
        Queue<ResultType> queue = new LinkedList<>();
        queue.offer(new ResultType(root, 0));
        while (!queue.isEmpty()) {
            ResultType rt = queue.poll();
            TreeNode crt = rt.root;
            int level = rt.level;
            map.get(level).add(crt.val);
            if (crt.left != null) {
                queue.offer(new ResultType(crt.left, level - 1));
            }
            if (crt.right != null) {
                queue.offer(new ResultType(crt.right, level + 1));
            }
        }
    }
}
