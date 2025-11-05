package MatrixProcessing;

public final class Inverse {
    private Inverse() {}

    // Повертає null, якщо сингулярна або не квадратна
    public static Matrix inverseOrNull(Matrix A) {
        int n = A.rows(), m = A.cols();
        if (n != m) return null;

        double[][] M = new double[n][2 * n];
        double[][] a = A.toArray();
        for (int i = 0; i < n; i++) {
            System.arraycopy(a[i], 0, M[i], 0, n);
            M[i][n + i] = 1.0;
        }

        for (int col = 0; col < n; col++) {
            int pivotRow = col;
            double maxAbs = Math.abs(M[col][col]);
            for (int r = col + 1; r < n; r++) {
                double v = Math.abs(M[r][col]);
                if (v > maxAbs) { maxAbs = v; pivotRow = r; }
            }
            if (maxAbs < 1e-12) return null;
            if (pivotRow != col) {
                double[] tmp = M[col]; M[col] = M[pivotRow]; M[pivotRow] = tmp;
            }
            double invPivot = 1.0 / M[col][col];
            for (int c = 0; c < 2 * n; c++) M[col][c] *= invPivot;

            for (int r = 0; r < n; r++) {
                if (r == col) continue;
                double factor = M[r][col];
                if (factor == 0.0) continue;
                for (int c = 0; c < 2 * n; c++) M[r][c] -= factor * M[col][c];
            }
        }
        double[][] inv = new double[n][n];
        for (int i = 0; i < n; i++) System.arraycopy(M[i], n, inv[i], 0, n);
        return new Matrix(inv);
    }
}
