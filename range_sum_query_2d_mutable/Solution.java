package range_sum_query_2d_mutable;

/*
 *308. Range Sum Query 2D - Mutable My Submissions Question
Total Accepted: 1066 Total Submissions: 5179 Difficulty: Hard
Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

Range Sum Query 2D
The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.

Example:
Given matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) -> 8
update(3, 2, 2)
sumRegion(2, 1, 4, 3) -> 10
Note:
The matrix is only modifiable by the update function.
You may assume the number of calls to update and sumRegion function is distributed evenly.
You may assume that row1 ¡Ü row2 and col1 ¡Ü col2.
 */

public class NumMatrix {

    int[][] tree;
    int[][] nums;
    int m;
    int n;

    public NumMatrix(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return;
        m = matrix.length;
        n = matrix[0].length;
        tree = new int[m+1][n+1];
        nums = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                update(i, j, matrix[i][j]);
            }
        }
    }

    public void update(int row, int col, int val) {
        if (m == 0 || n == 0) return;
        int delta = val - nums[row][col];
        nums[row][col] = val;
        for (int i = row + 1; i <= m; i += i & (-i)) {
            for (int j = col + 1; j <= n; j += j & (-j)) {
                tree[i][j] += delta;
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (m == 0 || n == 0) return 0;
        return sum(row2+1, col2+1) + sum(row1, col1) - sum(row1, col2+1) - sum(row2+1, col1);
    }

    public int sum(int row, int col) {
        int sum = 0;
        for (int i = row; i > 0; i -= i & (-i)) {
            for (int j = col; j > 0; j -= j & (-j)) {
                sum += tree[i][j];
            }
        }
        return sum;
    }
}
// time should be O(log(m) * log(n))


// Your NumMatrix object will be instantiated and called as such:
// NumMatrix numMatrix = new NumMatrix(matrix);
// numMatrix.sumRegion(0, 1, 2, 3);
// numMatrix.update(1, 1, 10);
// numMatrix.sumRegion(1, 2, 3, 4);


public class NumMatrix {
    SegmentTreeNode root;    
    int[][] nums;
    
    public NumMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
        int m = matrix.length;
        int n = matrix[0].length;
        nums = matrix;
        root = buildTree(matrix, new Pair(0, 0), new Pair(m - 1, n - 1));
    }

    public void update(int row, int col, int val) {
        int diff = val - nums[row][col];
        if(diff == 0) return;
        nums[row][col] = val;
        modify(row, col, diff, root);
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if(root != null) 
            return query(row1, col1, row2, col2, root);
        else
            return 0;
    }
    
    class Pair {
        int x; int y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    class SegmentTreeNode {
        int sum;
        Pair leftTop;
        Pair rightBottom;
        List<SegmentTreeNode> neighbor;
        public SegmentTreeNode(Pair start, Pair end) {
            this.sum = 0;
            this.leftTop = start;
            this.rightBottom = end;
            this.neighbor = new ArrayList<>(4);
            for (int i = 0; i < 4; i++) {
            	neighbor.add(null);
            }
        }
    }
    
    public SegmentTreeNode buildTree(int[][] matrix, Pair start, Pair end) {
        if (start.x > end.x || start.y > end.y) return null;
        SegmentTreeNode root = new SegmentTreeNode(start, end);
        if (start.x == end.x && start.y == end.y) {
            root.sum = matrix[start.x][start.y];
            return root;
        }
        int midx = (start.x + end.x) / 2;
        int midy = (start.y + end.y) / 2;
        root.neighbor.set(0, buildTree(matrix, start, new Pair(midx, midy)));
        root.neighbor.set(1, buildTree(matrix, new Pair(start.x, midy + 1), new Pair(midx, end.y)));
        root.neighbor.set(2, buildTree(matrix, new Pair(midx + 1, start.y), new Pair(end.x, midy)));
        root.neighbor.set(3, buildTree(matrix, new Pair(midx + 1, midy + 1), end));
        for (int i = 0; i < 4; i++) {
            if(root.neighbor.get(i) != null)
                root.sum += root.neighbor.get(i).sum;
        }
        return root;
    }
    
    public void modify(int row, int col, int diff, SegmentTreeNode root){
        if(row >= root.leftTop.x && row <= root.rightBottom.x && col >= root.leftTop.y && col <= root.rightBottom.y){
            root.sum += diff;
            for (int i = 0; i < 4; i++) {
                if(root.neighbor.get(i) != null)
                    modify(row, col, diff, root.neighbor.get(i));
            }
        }
    }
    
    public int query(int row1, int col1, int row2, int col2, SegmentTreeNode root){
        Pair start = root.leftTop;
        Pair end = root.rightBottom;
        int top = Math.max(start.x, row1);
        int bottom = Math.min(end.x, row2);
        if(bottom < top) return 0;
        
        int left = Math.max(start.y, col1);
        int right = Math.min(end.y, col2);
        if(left > right) return 0;
        
        if(row1 <= start.x && col1 <= start.y && row2 >= end.x && col2 >= end.y){
            return root.sum;
        }
        
        int res = 0;
        for (int i = 0; i < 4; i++) {
            if(root.neighbor.get(i) != null)
                res += query(row1, col1, row2, col2, root.neighbor.get(i));
        }
        return res;
    }
}


// Your NumMatrix object will be instantiated and called as such:
// NumMatrix numMatrix = new NumMatrix(matrix);
// numMatrix.sumRegion(0, 1, 2, 3);
// numMatrix.update(1, 1, 10);
// numMatrix.sumRegion(1, 2, 3, 4);

