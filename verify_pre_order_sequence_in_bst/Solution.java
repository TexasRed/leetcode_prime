package verify_pre_order_sequence_in_bst;

/*
 
 255. Verify Preorder Sequence in Binary Search Tree My Submissions Question
Total Accepted: 5098 Total Submissions: 14362 Difficulty: Medium
Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.

You may assume each number in the sequence is unique.

Follow up:
Could you do it using only constant space complexity?
 
 */

public class Solution {
    // O(n) time O(1) space
    public boolean verifyPreorder(int[] preorder) {
        if (preorder == null || preorder.length == 0) return true;
        int low = Integer.MIN_VALUE, i = -1;
        for (int p : preorder) {
            if (p < low)
                return false;
            while (i >= 0 && p > preorder[i])
                low = preorder[i--];
            preorder[++i] = p;
        }
        return true;
    }
    // O(n) time O(h) space
    public boolean verifyPreorder2(int[] preorder) {
        int low = Integer.MIN_VALUE;
        Stack<Integer> path = new Stack();
        for (int p : preorder) {
            if (p < low)
                return false;
            while (!path.empty() && p > path.peek())
                low = path.pop();
            path.push(p);
        }
        return true;
    }
    
    // O(n^2) time O(1) space - brute force
    public boolean validTree(int[] preorder, int start, int end) {
        if (start >= end) return true;  
        int i = start + 1;  
        while (i <= end && preorder[i] < preorder[start])  
            i++;  
        if (!validTree(preorder, start + 1, i - 1))  
            return false;  
        int rightStart = i;  
        while (i <= end && preorder[i] > preorder[start])  
            i++;  
        if (i <= end) return false;  
        return validTree(preorder, rightStart, end);  
    }

}
