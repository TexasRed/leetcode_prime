package graph_valid_tree;

/*
 261. Graph Valid Tree My Submissions Question
Total Accepted: 7276 Total Submissions: 24222 Difficulty: Medium
Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.

For example:

Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.

Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.

Hint:

Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], what should your return? Is this case a valid tree?
According to the definition of tree on Wikipedia: ¡°a tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.¡±
Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
 */

public class Solution {
    public boolean validTree(int n, int[][] edges) {
        List<List<Integer>> edgeList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            edgeList.add(new ArrayList<>());
        }
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            edgeList.get(u).add(v);
            edgeList.get(v).add(u);
        }
        
        boolean[] visited = new boolean[n];
        if (hasCycle(edgeList, 0, -1, visited))
            return false;
        
        for (int i = 0; i < n; i++) {
            if (!visited[i])
                return false;
        }
        return true;
    }
    
    // currNode, currParent
    public boolean hasCycle(List<List<Integer>> edgeList, int u, int pred, boolean[] visited) {
        visited[u] = true;
        for (int v : edgeList.get(u)) {
            if (v == pred) continue;
            if (visited[v]) {
                return true;
            } else {
                if (hasCycle(edgeList, v, u, visited))
                    return true;
            }
        }
        return false;
    }
    
    public boolean validTree2(int n, int[][] edges) {
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            makeSet(parent, i);
        }
        for (int i = 0; i < edges.length; i++) {
            if (union(parent, edges[i][0], edges[i][1]))
                return false;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(find(parent, i));
        }
        
        return set.size() == 1;
    }
    
    public int find(int[] parent, int u) {
        if (parent[u] == u) return u;
        int root = find(parent, parent[u]);
        parent[u] = root;
        return root;
    }
    
    public boolean union(int[] parent, int u, int v) {
        int ru = find(parent, u);
        int rv = find(parent, v);
        if (ru == rv) {
            return true;
        } else {
            parent[ru] = rv;
            return false;
        }
    }
    
    public void makeSet(int[] parent, int u) {
        parent[u] = u;
    }
    
}
