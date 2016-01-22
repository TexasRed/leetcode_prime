package paint_fence;

/*
 *276. Paint Fence My Submissions Question
Total Accepted: 5305 Total Submissions: 18068 Difficulty: Easy
There is a fence with n posts, each post can be painted with one of the k colors.

You have to paint all the posts such that no more than two adjacent fence posts have the same color.

Return the total number of ways you can paint the fence.

Note:
n and k are non-negative integers.
 */

public class Solution {
    public int numWays(int n, int k) {
        if (n == 0 || k == 0) return 0;
        if (n == 1) return k;
        
        // D[n] = (k - 1) * (D[n - 1] + S[n - 1])
        // S[n] = D[n - 1]
        // T[n] = S[n] + D[n]
        // T[n] = (k - 1) * (T[n - 1] + T[n - 2])
        
        int prev1 = k, prev2 = k * k;
        for (int i = 2; i < n; i++) {
            int temp = prev2;
            prev2 = (k - 1) * (prev1 + prev2);
            prev1 = temp;
        }
        return prev2;
    }
}
