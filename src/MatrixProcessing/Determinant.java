package MatrixProcessing;

public final class Determinant {
    private Determinant() {}

    public static double det(Matrix A) {
        int n = A.rows(), m = A.cols();
        if (n != m) throw new IllegalArgumentException("Determinant requires a square matrix");
        double[][] M = A.toArray();

        double det = 1.0;
        int swaps = 0;

        for (int col = 0; col < n; col++) {
            int pivotRow = col;
            double maxAbs = Math.abs(M[col][col]);
            for (int r = col + 1; r < n; r++) {
                double v = Math.abs(M[r][col]);
                if (v > maxAbs) { maxAbs = v; pivotRow = r; }
            }
            if (maxAbs < 1e-12) return 0.0;

            if (pivotRow != col) {
                double[] tmp = M[col]; M[col] = M[pivotRow]; M[pivotRow] = tmp;
                swaps++;
            }
            double pivot = M[col][col];
            for (int r = col + 1; r < n; r++) {
                double factor = M[r][col] / pivot;
                if (factor == 0.0) continue;
                for (int c = col; c < n; c++) M[r][c] -= factor * M[col][c];
            }
        }
        for (int i = 0; i < n; i++) det *= M[i][i];
        if ((swaps & 1) == 1) det = -det;
        return det;
    }
}
