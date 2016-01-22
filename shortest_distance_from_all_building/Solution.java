package shortest_distance_from_all_building;

/*
 *317. Shortest Distance from All Buildings My Submissions Question
Total Accepted: 1489 Total Submissions: 5142 Difficulty: Hard
You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:

Each 0 marks an empty land which you can pass by freely.
Each 1 marks a building which you cannot pass through.
Each 2 marks an obstacle which you cannot pass through.
For example, given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2):

1 - 0 - 2 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0
The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal. So return 7.

Note:
There will be at least one building. If it is not possible to build such house according to the above rules, return -1.
 */

public class Solution {
    public int shortestDistance(int[][] grid) {
        int m = grid.length; int n = grid[0].length;
        
        int[][] canAchieve = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                canAchieve[i][j] = grid[i][j];
            }
        }
        
        int[][] total = new int[m][n];
        int numOfBuildings = 0;
        
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int[][] distance = new int[m][n];
                    // boolean[][] visited = new boolean[m][n];
                    // visited[i][j] = true;
                    queue.offer(i * n + j);
                    
                    while (!queue.isEmpty()) {
                        int curr = queue.poll();
                        int currX = curr / n; int currY = curr % n;
                        for (int k = 0; k < 4; k++) {
                            int x = currX + dx[k]; int y = currY + dy[k];
                            if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 0 && canAchieve[x][y] == numOfBuildings /*!visited[x][y]*/) {
                                canAchieve[x][y]--;
                                distance[x][y] = distance[currX][currY] + 1;
                                total[x][y] += distance[x][y];
                                queue.offer(x * n + y);
                                // visited[x][y] = true;
                            }
                        }
                    }
                    numOfBuildings--;
                }
            }
        }
        int minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (canAchieve[i][j] == numOfBuildings) {
                    minDistance = Math.min(minDistance, total[i][j]);
                }
            }
        }
        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }
}