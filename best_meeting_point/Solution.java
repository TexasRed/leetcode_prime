package best_meeting_point;

/*
 *296. Best Meeting Point My Submissions Question
Total Accepted: 2743 Total Submissions: 6129 Difficulty: Hard
A group of two or more people wants to meet and minimize the total travel distance. You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group. The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.

For example, given three people living at (0,0), (0,4), and (2,2):

1 - 0 - 0 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0
The point (0,2) is an ideal meeting point, as the total travel distance of 2+2+2=6 is minimal. So return 6.

Hint:

Try to solve it in one dimension first. How can this solution apply to the two dimension case?
 */

public class Solution {
    public int minTotalDistance(int[][] grid) {
        if (grid == null) return 0;
        int m = grid.length;
        int n = grid[0].length;
        if (m == 0 || n == 0) return 0;
        int minDistance = 0;
        
        List<Integer> rows = new ArrayList<>();
        List<Integer> cols = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    rows.add(i);
                    cols.add(j);
                }
            }
        }
        Collections.sort(rows);
        Collections.sort(cols);
        
        int bestRow = rows.get(rows.size() / 2);
        int bestCol = cols.get(cols.size() / 2);
        for (Integer row : rows) {
            minDistance += Math.abs(row - bestRow);
        }
        for (Integer col : cols) {
            minDistance += Math.abs(col - bestCol);
        }
        return minDistance;
    }
}

public class Solution {
    public int minTotalDistance(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return Integer.MAX_VALUE;
        int m = grid.length; int n = grid[0].length;
        List<Integer> rows = new ArrayList<>();
        List<Integer> cols = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    rows.add(i);
                    cols.add(j);
                }
            }
        }
        Collections.sort(rows);
        Collections.sort(cols);
        int x = rows.get(rows.size() / 2);
        int y = cols.get(cols.size() / 2);
        int distance = 0;
        for (Integer row : rows) {
            distance += Math.abs(x - row);
        }
        for (Integer col : cols) {
            distance += Math.abs(y - col);
        }
        // for (int i = 0; i < m; i++) {
        //     for (int j = 0; j < n; j++) {
        //         if (grid[i][j] == 1) {
        //             distance += Math.abs(x - i) + Math.abs(y - j);
        //         }
        //     }
        // }
        return distance;
    }
}
