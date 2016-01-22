package closet_bst_value_ii;

/*
 *272. Closest Binary Search Tree Value II My Submissions Question
Total Accepted: 3930 Total Submissions: 12811 Difficulty: Hard
Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.

Note:
Given target value is a floating point.
You may assume k is always valid, that is: k ≤ total nodes.
You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
Follow up:
Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?

Hint:

Consider implement these two helper functions:
getPredecessor(N), which returns the next smaller node to N.
getSuccessor(N), which returns the next larger node to N.
Try to assume that each node has a parent pointer, it makes the problem much easier.
Without parent pointer we just need to keep track of the path from the root to the current node using a stack.
You would need two stacks to track the path in finding predecessor and successor node separately.
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
class Number implements Comparable<Number> {
    double diff;
    int val;
    public Number(double diff, int val) {
        this.diff = diff;
        this.val = val;
    }
    @Override
    public int compareTo(Number n) {
        double difference = this.diff - n.diff;
        if ( difference < 0) {
            return -1;
        } else if (difference > 0) {
            return 1;
        } else return 0;
    }
}

public class Solution {
    // O(klgn) time O(klgn) space
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> result = new LinkedList<Integer>();
        Stack<TreeNode> pred = new Stack<TreeNode>();
        Stack<TreeNode> succ = new Stack<TreeNode>();
        TreeNode curr = root;
        while (curr != null) {
            if (target <= curr.val) {
                succ.push(curr);
                curr = curr.left;
            } else {
                pred.push(curr);
                curr = curr.right;
            }
        }
        while (k > 0) {
            if (pred.empty() && succ.empty()) {
                break; 
            } else if (pred.empty()) {
                result.add( getSuccessor(succ) );
            } else if (succ.empty()) {
                result.add( getPredecessor(pred) );
            } else if (Math.abs(target - pred.peek().val) < Math.abs(target - succ.peek().val)) {
                result.add( getPredecessor(pred) );                    
            } else {
                result.add( getSuccessor(succ) );
            }
            k--;
        }
        return result;
     }

    private int getPredecessor(Stack<TreeNode> st) {
        TreeNode popped = st.pop();
        TreeNode p = popped.left;
        while (p != null) {
            st.push(p);
            p = p.right;
        }
        return popped.val;
    }

    private int getSuccessor(Stack<TreeNode> st) {
        TreeNode popped = st.pop();
        TreeNode p = popped.right;
        while (p != null) {
            st.push(p);
            p = p.left;
        }
        return popped.val;
    }
    
    // maxHeap, O(k + nlgk) time, O(k) space
    public List<Integer> sliding_window(TreeNode root, double target, int k) {
        Queue<Integer> klist = new LinkedList<Integer>();
        Stack<TreeNode> stk = new Stack<TreeNode>();
        // 迭代中序遍历二叉搜索树的代码
        while(root != null){
            stk.push(root);
            root = root.left;
        }
        while(!stk.isEmpty()){
            TreeNode curr = stk.pop();
            // 维护一个大小为k的队列
            // 队列不到k时直接加入
            if(klist.size() < k){
                klist.offer(curr.val);
            } else {
            // 队列到k时，判断下新的数是否更近，更近就加入队列并去掉队头
                int first = klist.peek();
                if(Math.abs(first - target) > Math.abs(curr.val - target)){
                    klist.poll();
                    klist.offer(curr.val);
                } else {
                // 如果不是更近则直接退出，后面的数只会更大
                    break;
                }
            }
            // 中序遍历的代码
            if(curr.right != null){
                curr = curr.right;
                while(curr != null){
                    stk.push(curr);
                    curr = curr.left;
                }
            }
        }
        // 强制转换成List，是用LinkedList实现的所以可以转换
        return (List<Integer>)klist;
    }
    
    // minHeap, O(n + klgn) time, O(n) space in-order
    public List<Integer> closestKValues_minHeap(TreeNode root, double target, int k) {
        PriorityQueue<Number> pq = new PriorityQueue<>();
        inOrderTraverse(root, pq, target);
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            result.add(pq.poll().val);
        }
        return result;
    }
    public void inOrderTraverse(TreeNode root, PriorityQueue<Number> pq, double target) {
        if (root == null) return;
        inOrderTraverse(root.left, pq, target);
        double diff = Math.abs((double) root.val - target);
        pq.offer(new Number(diff, root.val));
        inOrderTraverse(root.right, pq, target);
    }
    
    // maxHeap, O(k + nlgk) time, O(k) space pre-order
    public List<Integer> maxHeap(TreeNode root, double target, int k) {  
        PriorityQueue<Double> maxHeap = new PriorityQueue<Double>(k, new Comparator<Double>() {   
            @Override  
            public int compare(Double x, Double y) {  
                return (int)(y-x);  
            }  
        });  
        Set<Integer> set = new HashSet<Integer>();  
          
        rec(root, target, k, maxHeap, set);  
          
        return new ArrayList<Integer>(set);  
    }  
      
    private void rec(TreeNode root, double target, int k, PriorityQueue<Double> maxHeap, Set<Integer> set) {  
        if(root==null) return;  
        double diff = Math.abs(root.val-target);  
        if(maxHeap.size()<k) {  
            maxHeap.offer(diff);  
            set.add(root.val);  
        } else if( diff < maxHeap.peek() ) {  
            double x = maxHeap.poll();  
            if(! set.remove((int)(target+x))) set.remove((int)(target-x));  
            maxHeap.offer(diff);  
            set.add(root.val);  
        } else {  
            if(root.val > target) rec(root.left, target, k, maxHeap,set);  
            else rec(root.right, target, k, maxHeap, set);  
            return;  
        }  
        rec(root.left, target, k, maxHeap, set);  
        rec(root.right, target, k, maxHeap, set);  
    } 
    
}
