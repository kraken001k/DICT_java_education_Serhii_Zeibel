package MatrixProcessing;

public final class Matrix {
    private final int n;         // rows
    private final int m;         // cols
    private final double[][] a;  // immutable copy

    public Matrix(double[][] data) {
        if (data == null || data.length == 0 || data[0].length == 0)
            throw new IllegalArgumentException("Empty matrix");
        this.n = data.length;
        this.m = data[0].length;
        this.a = new double[n][m];
        for (int i = 0; i < n; i++) {
            if (data[i] == null || data[i].length != m)
                throw new IllegalArgumentException("Matrix must be rectangular");
            System.arraycopy(data[i], 0, this.a[i], 0, m);
        }
    }

    public int rows() { return n; }
    public int cols() { return m; }
    public double get(int i, int j) { return a[i][j]; }

    public double[][] toArray() { // deep copy
        double[][] out = new double[n][m];
        for (int i = 0; i < n; i++) System.arraycopy(a[i], 0, out[i], 0, m);
        return out;
    }

    // ---- базові операції повертають нові об'єкти ----
    public Matrix add(Matrix b) {
        require(n == b.n && m == b.m, "Size mismatch for addition");
        double[][] c = new double[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                c[i][j] = a[i][j] + b.a[i][j];
        return new Matrix(c);
    }

    public Matrix times(double k) {
        double[][] c = new double[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                c[i][j] = k * a[i][j];
        return new Matrix(c);
    }

    public Matrix multiply(Matrix b) {
        require(m == b.n, "Size mismatch for multiplication");
        double[][] c = new double[n][b.m];
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < m; k++) {
                double aik = a[i][k];
                if (aik == 0) continue;
                for (int j = 0; j < b.m; j++) c[i][j] += aik * b.a[k][j];
            }
        }
        return new Matrix(c);
    }

    public Matrix transpose(Transposition kind) {
        return Transposition.apply(kind, this);
    }

    // детермінант і обернена винесені в окремі класи:
    public double det() { return Determinant.det(this); }
    public Matrix inverseOrNull() { return Inverse.inverseOrNull(this); }

    private static void require(boolean ok, String msg) {
        if (!ok) throw new IllegalArgumentException(msg);
    }
}
