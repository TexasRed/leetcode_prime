package number_of_connected_components_in_an_undirected_graph;

/*
 *323. Number of Connected Components in an Undirected Graph My Submissions Question
Total Accepted: 2479 Total Submissions: 5705 Difficulty: Medium
Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to find the number of connected components in an undirected graph.

Example 1:
     0          3
     |          |
     1 --- 2    4
Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], return 2.

Example 2:
     0           4
     |           |
     1 --- 2 --- 3
Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]], return 1.

Note:
You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.

 */

public class Solution {
    public int countComponents(int n, int[][] edges) {
        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        
        int count = 0;
        for (int i = 0; i < n; i++) {
            count = makeSet(parent, i, count);
        }
        
        int m = edges.length;
        for (int i = 0; i < m; i++) {
            count = union(parent, edges[i][0], edges[i][1], count);
        }
        return count;
    }
    
    public int makeSet(int[] parent, int u, int count) {
        parent[u] = u;
        count++;
        return count;
    }
    
    public int find(int[] parent, int u) {
        if (parent[u] == u) return u;
        int root = find(parent, parent[u]);
        parent[u] = root;
        return root;
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
}
