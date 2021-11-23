package com.dp.cuts;
public class MatrixMultiplication {
    // Dimension of matrix[i] = matrixDimensions[i] x matrixDimensions[i + 1]
    // We do not need matrix[] array since all we care about is their
    // dimensions
    public static int computeOptimalMatrixMultiplication(int matrixDimensions[])
    {
        int n = matrixDimensions.length - 1; // number of matrices is 1 less than the length of dimension array
        int memo[][] = new int[n][n]; // memo[i, j] = Minimum number of scalar multiplications
            // needed to compute the matrix
            // A[i]A[i+1]...A[j] = A[i..j] where
            // dimension of A[i] is p[i-1] x p[i]

        for (int i = 0; i < n; i++) {
            memo[i][i] = 0; // cost is zero when multiplying one matrix since we do not need to carry out any  multiplication operation
        }

        for (int len = 2; len <= n; len++)
        {
            for (int beg = 0; beg < n - len + 1; beg++)
            {
                int end = beg + len - 1; // inclusive

                memo[beg][end] = Integer.MAX_VALUE;

                for (int cut = beg; cut <= end - 1; cut++)
                {
                    int cost = memo[beg][cut] + memo[cut + 1][end] + matrixDimensions[beg] * matrixDimensions[cut + 1] * matrixDimensions[end + 1];
                    if (cost < memo[beg][end]) {
                        memo[beg][end] = cost;
                    }
                }
            }
        }

        return memo[0][n - 1];
    }

    public static void main(String[] args) {
        System.out.println(computeOptimalMatrixMultiplication(new int[]{ 40, 20, 30, 10, 30})); // 26000
        System.out.println(computeOptimalMatrixMultiplication(new int[]{ 10, 20, 30, 40, 30})); // 30000
    }
}
