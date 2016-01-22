class NumMatrix {
public:
    NumMatrix(vector<vector<int>> &matrix) {
        if (matrix.size() == 0 || matrix[0].size() == 0) return;
        nrow = matrix.size();
        ncol = matrix[0].size();
        nums = matrix;
        BIT = vector<vector<int>> (nrow+1, vector<int>(ncol+1, 0));
        for (int i = 0; i < nrow; i++)
            for (int j = 0; j < ncol; j++)
                add(i, j, matrix[i][j]);

    }

    void update(int row, int col, int val) {
        int diff = val - nums[row][col];
        add(row, col,diff);
        nums[row][col] = val;
    }

    int sumRegion(int row1, int col1, int row2, int col2) {
        int regionL = 0, regionS = 0;
        int regionLeft = 0, regionTop = 0;

        regionL = region(row2, col2);

        if (row1 > 0 && col1 > 0) regionS = region(row1-1, col1-1);

        if (row1 > 0) regionTop  = region(row1-1, col2);

        if (col1 > 0) regionLeft = region(row2, col1-1);       

        return regionL - regionTop - regionLeft + regionS;
    }
private:
    vector<vector<int>> nums;
    vector<vector<int>> BIT;
    int nrow = 0;
    int ncol = 0;
    void add(int row, int col, int val) {
        row++;
        col++;
        while(row <= nrow) {
            int colIdx = col;
            while(colIdx <= ncol) {
                BIT[row][colIdx] += val;
                colIdx += (colIdx & (-colIdx));
            }
            row +=  (row & (-row));
        }
    }

    int region(int row, int col) {
        row++;
        col++;
        int res = 0;
        while(row > 0) {
            int colIdx = col;
            while(colIdx > 0) {
                res += BIT[row][colIdx];
                colIdx -= (colIdx & (-colIdx));
            }
            row -= (row & (-row));
        }
        return res;
    }
};


// Your NumMatrix object will be instantiated and called as such:
// NumMatrix numMatrix(matrix);
// numMatrix.sumRegion(0, 1, 2, 3);
// numMatrix.update(1, 1, 10);
// numMatrix.sumRegion(1, 2, 3, 4);