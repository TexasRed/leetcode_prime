package walls_and_gates;

/*
 *286. Walls and Gates My Submissions Question
Total Accepted: 4994 Total Submissions: 14132 Difficulty: Medium
You are given a m x n 2D grid initialized with these three possible values.

-1 - A wall or an obstacle.
0 - A gate.
INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.

For example, given the 2D grid:
INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
  0  -1 INF INF
After running your function, the 2D grid should be:
  3  -1   0   1
  2   2   1  -1
  1  -1   2  -1
  0  -1   3   4
 */

public class Solution {
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0) return;
        
        int m = rooms.length;
        int n = rooms[0].length;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0) {
                    boolean[][] visited = new boolean[m][n];
                    bfs(rooms, i, j, visited);
                }
            }
        }
    }
    
    public void bfs(int[][] rooms, int i, int j, boolean[][] visited) {
        int m = rooms.length; int n = rooms[0].length;
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};
        
        int level = 0;
        
        if (!visited[i][j]) {
            Queue<Integer> queue = new LinkedList<>();
            visited[i][j] = true;
            queue.offer(i * n + j);
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int p = 0; p < size; p++) {
                    int pos = queue.poll();
                    int x = pos / n;
                    int y = pos % n;
                    rooms[x][y] = Math.min(rooms[x][y], level);
                    for (int k = 0; k < 4; k++) {
                        int nx = x + dx[k];
                        int ny = y + dy[k];
                        if (nx >= 0 && nx < m && ny >= 0 && ny < n && !visited[nx][ny] 
                            && rooms[nx][ny] != -1 && rooms[nx][ny] != 0) {
                            visited[nx][ny] = true;
                            queue.offer(nx * n + ny);
                        }
                    }
                }
                level++;
            }
            
        }
    }
}
