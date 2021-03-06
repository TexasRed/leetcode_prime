package paint_house_ii;

/*
 265. Paint House II My Submissions Question
Total Accepted: 4600 Total Submissions: 13597 Difficulty: Hard
There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on... Find the minimum cost to paint all houses.

Note:
All costs are positive integers.

Follow up:
Could you solve it in O(nk) runtime?
 */

public class Solution {
    public int minCostII(int[][] costs) {
        if(costs != null && costs.length == 0) return 0;
        int prevMin = 0, prevSec = 0, prevIdx = -1;
        for(int i = 0; i < costs.length; i++){
            int currMin = Integer.MAX_VALUE, currSec = Integer.MAX_VALUE, currIdx = -1;
            for(int j = 0; j < costs[0].length; j++){
                costs[i][j] = costs[i][j] + (prevIdx == j ? prevSec : prevMin);
                if(costs[i][j] < currMin){
                    currSec = currMin;
                    currMin = costs[i][j];
                    currIdx = j;
                } else if (costs[i][j] < currSec){
                    currSec = costs[i][j];
                }
            }
            prevMin = currMin;
            prevSec = currSec;
            prevIdx = currIdx;
        }
        return prevMin;
        
    }
}

public class Solution {
    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0 || costs[0].length == 0) return 0;
        int n = costs.length;
        int k = costs[0].length;
        int prevId = -1;
        int prevFirstMin = 0, prevSecondMin = 0;
        for (int i = 0; i < n; i++) {
            int currFirstMin = Integer.MAX_VALUE;
            int currSecondMin = Integer.MAX_VALUE;
            int currId = -1;
            for (int j = 0; j < k; j++) {
                costs[i][j] = costs[i][j] + (j == prevId ? prevSecondMin : prevFirstMin);
                if (costs[i][j] < currFirstMin) {
                    currSecondMin = currFirstMin;
                    currFirstMin = costs[i][j];
                    currId = j;
                } else if (costs[i][j] < currSecondMin) {
                    currSecondMin = costs[i][j];
                }
            }
            prevFirstMin = currFirstMin;
            prevSecondMin = currSecondMin;
            prevId = currId;
        }
        return prevFirstMin;
    }
}
