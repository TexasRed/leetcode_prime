package sparse_matrix_multiplication;

/*
 *311. Sparse Matrix Multiplication My Submissions Question
Total Accepted: 2897 Total Submissions: 6141 Difficulty: Medium
Given two sparse matrices A and B, return the result of AB.

You may assume that A's column number is equal to B's row number.

Example:

A = [
  [ 1, 0, 0],
  [-1, 0, 3]
]

B = [
  [ 7, 0, 0 ],
  [ 0, 0, 0 ],
  [ 0, 0, 1 ]
]


     |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
                  | 0 0 1 |
 */

public class Solution {
    public int[][] multiply(int[][] A, int[][] B) {
        if (A == null || B == null) return null;
        
        int m = A.length;
        int n = A[0].length;
        int k = B.length;
        int p = B[0].length;
        
        int[][] res = new int[m][p];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < p; j++) {
                for (int r = 0; r < n; r++) {
                    if (A[i][r] == 0 || B[r][j] == 0) {
                        continue;
                    } else {
                        res[i][j] += A[i][r] * B[r][j];
                    }
                }
            }
        }
        
        return res;
    }
}
