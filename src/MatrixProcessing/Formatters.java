package MatrixProcessing;

import java.text.DecimalFormat;

public final class Formatters {
    private Formatters() {}

    // Загальне форматування: якщо всі входи були цілі — друкуємо без .0
    public static String fmt(double v, boolean forceDecimal) {
        if (!forceDecimal) {
            long r = Math.round(v);
            if (Math.abs(v - r) < 1e-12) return Long.toString(r);
        }
        return new DecimalFormat("0.0############").format(v);
    }

    // Для inverse: до 2 знаків, із відкиданням зайвих нулів
    public static String fmt2(double v) {
        double r = Math.round(v * 100.0) / 100.0;
        long asInt = Math.round(r);
        if (Math.abs(r - asInt) < 1e-12) return Long.toString(asInt);
        return new DecimalFormat("0.0#").format(r);
    }

    public static void printMatrix(Matrix M, boolean forceDecimal) {
        double[][] a = M.toArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                if (j > 0) sb.append(' ');
                sb.append(fmt(a[i][j], forceDecimal));
            }
            if (i + 1 < a.length) sb.append('\n');
        }
        System.out.println(sb);
    }

    public static void printMatrix2dp(Matrix M) {
        double[][] a = M.toArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                if (j > 0) sb.append(' ');
                sb.append(fmt2(a[i][j]));
            }
            if (i + 1 < a.length) sb.append('\n');
        }
        System.out.println(sb);
    }
}
