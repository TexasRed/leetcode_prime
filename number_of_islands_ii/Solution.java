package number_of_islands_ii;

/*
 *305. Number of Islands II My Submissions Question
Total Accepted: 3017 Total Submissions: 9268 Difficulty: Hard
A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand operation which turns the water at position (row, col) into a land. Given a list of positions to operate, count the number of islands after each addLand operation. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example:

Given m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]].
Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).

0 0 0
0 0 0
0 0 0
Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.

1 0 0
0 0 0   Number of islands = 1
0 0 0
Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.

1 1 0
0 0 0   Number of islands = 1
0 0 0
Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.

1 1 0
0 0 1   Number of islands = 2
0 0 0
Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.

1 1 0
0 0 1   Number of islands = 3
0 1 0
We return the result as an array: [1, 1, 2, 3]

Challenge:

Can you do it in time complexity O(k log mn), where k is the length of the positions?
 */

public class Solution {
    
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> result = new ArrayList<>();
        int[] parent = new int[m * n];
        Arrays.fill(parent, -1);
        
        boolean[] islands = new boolean[m * n];
        int count = 0;    
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};
        for (int i = 0; i < positions.length; i++) {
            int x = positions[i][0];
            int y = positions[i][1];
            int pos = x * n + y;
            if (!islands[pos]) {
                count = makeSet(parent, pos, count);
                islands[pos] = true;
                for (int k = 0; k < 4; k++) {
                    int nx = x + dx[k];
                    int ny = y + dy[k];
                    int newPos = nx * n + ny;
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n && islands[newPos]) {
                        count = union(parent, pos, newPos, count);
                    }
                }
            }
            result.add(count);
        }
        return result;
    }
    
    public int makeSet(int[] parent, int u, int count) {
        parent[u] = u;
        count++;
        return count;
    }
    
    public int union(int[] parent, int u, int v, int count) {
        int ru = find(parent, u);
        int rv = find(parent, v);
        if (ru != rv) {
            parent[ru] = rv;
            count--;
        }
        return count;
    }
    
    public int find(int[] parent, int u) {
        if (u == parent[u]) {
            return u;
        } else {
            int root = find(parent, parent[u]);
            parent[u] = root;
            return root;
        }
    }
}
