package smallest_rectangle_enclosing_black_pixels;

/*
 *302. Smallest Rectangle Enclosing Black Pixels My Submissions Question
Total Accepted: 2676 Total Submissions: 7105 Difficulty: Hard
An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel. The black pixels are connected, i.e., there is only one black region. Pixels are connected horizontally and vertically. Given the location (x, y) of one of the black pixels, return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.

For example, given the following image:

[
  "0010",
  "0110",
  "0100"
]
and x = 0, y = 2,
Return 6.
 */

public class Solution {
    
    public int minArea(char[][] image, int x, int y) {
        int m = image.length;
        int n = image[0].length;
        
        boolean[][] visited = new boolean[m][n];
        int[][] bounds =  new int[2][2];
        bounds[0][0] = Integer.MAX_VALUE;
        bounds[0][1] = Integer.MAX_VALUE;
        bounds[1][0] = Integer.MIN_VALUE;
        bounds[1][1] = Integer.MIN_VALUE;
        dfs(image, x, y, visited, bounds);
        
        return (bounds[1][0] - bounds[0][0] + 1) * (bounds[1][1] - bounds[0][1] + 1);
    }
    
    public void dfs(char[][] image, int x, int y, boolean[][]visited, int[][]bounds) {
        int m = image.length;
        int n = image[0].length;
        if (!visited[x][y]) {
            visited[x][y] = true;
            bounds[0][0] = Math.min(bounds[0][0], x);
            bounds[0][1] = Math.min(bounds[0][1], y);
            bounds[1][0] = Math.max(bounds[1][0], x);
            bounds[1][1] = Math.max(bounds[1][1], y);
            int[] dx = {-1, 0, 1, 0};
            int[] dy = {0, -1, 0, 1};
            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && !visited[nx][ny] && image[nx][ny] != '0') {
                    dfs(image, nx, ny, visited, bounds);
                }
            }
        }
    }
}
