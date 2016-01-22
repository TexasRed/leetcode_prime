package paint_house;

/*
 256. Paint House My Submissions Question
Total Accepted: 4979 Total Submissions: 12016 Difficulty: Medium
There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on... Find the minimum cost to paint all houses.

Note:
All costs are positive integers.
 *
 */

public class Solution {
    public int minCost(int[][] costs) {
        if (costs == null || costs.length == 0 || costs[0].length == 0) return 0;
        int n = costs.length;
        int[][] dp = new int[n][3];
        for (int j = 0; j < 3; j++) {
            dp[0][j] = costs[0][j];
        }
        if (n >= 2) {
            for (int j = 0; j < 3; j++) {
                dp[1][j] = Math.min(costs[0][(j + 1) % 3], costs[0][(j + 2) % 3]) + costs[1][j];
            }
            for (int i = 2; i < n; i++) {
                for (int j = 0; j < 3; j++) {
                    int min1 = Math.min(
                        costs[i][j] + costs[i - 1][(j + 1) % 3] + dp[i - 2][j],
                        costs[i][j] + costs[i - 1][(j + 1) % 3] + dp[i - 2][(j + 2) % 3]
                    );
                    int min2 = Math.min(
                        costs[i][j] + costs[i - 1][(j + 2) % 3] + dp[i - 2][j],
                        costs[i][j] + costs[i - 1][(j + 2) % 3] + dp[i - 2][(j + 1) % 3]
                    );
                    dp[i][j] = Math.min(min1, min2);
                }
            }
        }
        return Math.min(Math.min(dp[n - 1][0], dp[n - 1][1]), dp[n - 1][2]);
    }
}
