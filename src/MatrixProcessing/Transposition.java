package MatrixProcessing;

public enum Transposition {
    MAIN,        // головна діагональ
    SIDE,        // побічна діагональ
    VERTICAL,    // відносно вертикалі
    HORIZONTAL;  // відносно горизонталі

    static Matrix apply(Transposition t, Matrix A) {
        return switch (t) {
            case MAIN -> transposeMain(A);
            case SIDE -> transposeSide(A);
            case VERTICAL -> reflectVertical(A);
            case HORIZONTAL -> reflectHorizontal(A);
        };
    }

    private static Matrix transposeMain(Matrix M) {
        int n = M.rows(), m = M.cols();
        double[][] r = new double[m][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                r[j][i] = M.get(i, j);
        return new Matrix(r);
    }

    private static Matrix transposeSide(Matrix M) {
        int n = M.rows(), m = M.cols();
        double[][] r = new double[m][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                r[m - 1 - j][n - 1 - i] = M.get(i, j);
        return new Matrix(r);
    }

    private static Matrix reflectVertical(Matrix M) {
        int n = M.rows(), m = M.cols();
        double[][] r = new double[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                r[i][j] = M.get(i, m - 1 - j);
        return new Matrix(r);
    }

    private static Matrix reflectHorizontal(Matrix M) {
        int n = M.rows(), m = M.cols();
        double[][] r = new double[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                r[i][j] = M.get(n - 1 - i, j);
        return new Matrix(r);
    }
}
