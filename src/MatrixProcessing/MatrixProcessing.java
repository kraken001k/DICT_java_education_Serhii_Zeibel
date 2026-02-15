package MatrixProcessing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static MatrixProcessing.Formatters.*;
import static MatrixProcessing.Transposition.*;

public class MatrixProcessing {

    // швидкий ввід
    static class FastScanner {
        private final BufferedReader br;
        private StringTokenizer st;
        boolean sawFloat = false;

        FastScanner(InputStream is) { br = new BufferedReader(new InputStreamReader(is)); }
        String next() throws IOException {
            while (st == null || !st.hasMoreTokens()) {
                String line = br.readLine();
                if (line == null) return null;
                st = new StringTokenizer(line);
            }
            return st.nextToken();
        }
        Integer nextInt() throws IOException { String s = next(); return s == null ? null : Integer.parseInt(s); }
        Double nextDouble() throws IOException {
            String s = next(); if (s == null) return null;
            if (s.contains(".") || s.contains("e") || s.contains("E")) sawFloat = true;
            return Double.parseDouble(s);
        }
        void resetFloat() { sawFloat = false; }
    }

    private static final boolean PROMPT = true;

    private static void prompt(String s) {
        if (PROMPT) {
            System.out.print(s);
            System.out.flush();
        }
    }

    private static void printMenu() {
        System.out.println("1. Add matrices");
        System.out.println("2. Multiply matrix by a constant");
        System.out.println("3. Multiply matrices");
        System.out.println("4. Transpose matrix");
        System.out.println("5. Calculate a determinant");
        System.out.println("6. Inverse matrix");
        System.out.println("0. Exit");
        System.out.print("Your choice: > ");
        System.out.flush();
    }
    private static void printTransposeMenu() {
        System.out.println();
        System.out.println("1. Main diagonal");
        System.out.println("2. Side diagonal");
        System.out.println("3. Vertical line");
        System.out.println("4. Horizontal line");
        System.out.print("Your choice: > ");
        System.out.flush();
    }

    private static Matrix readMatrix(FastScanner fs, int n, int m) throws Exception {
        double[][] a = new double[n][m];
        for (int i = 0; i < n; i++) {
            prompt("> ");
            for (int j = 0; j < m; j++) {
                a[i][j] = fs.nextDouble();
            }
        }
        return new Matrix(a);
    }


    static void main(String[] ignoredArgs) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        while (true) {
            printMenu();
            fs.resetFloat();
            String tok = fs.next(); if (tok == null) return;
            int choice; try { choice = Integer.parseInt(tok); } catch (NumberFormatException e) { continue; }

            if (choice == 0) return;

            if (choice == 1) {
                System.out.print("Enter size of first matrix: > ");
                prompt("");
                int n1 = fs.nextInt(), m1 = fs.nextInt(); System.out.println();
                System.out.println("Enter first matrix:");
                prompt("");
                Matrix A = readMatrix(fs, n1, m1);

                System.out.print("Enter size of second matrix: > ");
                prompt("");
                int n2 = fs.nextInt(), m2 = fs.nextInt(); System.out.println();
                System.out.println("Enter second matrix:");
                prompt("");
                Matrix B = readMatrix(fs, n2, m2);

                if (n1 != n2 || m1 != m2) {
                    System.out.println("The operation cannot be performed.");
                } else {
                    System.out.println("The result is:");
                    printMatrix(A.add(B), fs.sawFloat);
                }

            } else if (choice == 2) {
                System.out.print("Enter size of matrix: > ");
                prompt("");
                int n = fs.nextInt(), m = fs.nextInt(); System.out.println();
                System.out.println("Enter matrix:");
                prompt("");
                Matrix A = readMatrix(fs, n, m);

                System.out.print("Enter constant: > ");
                prompt("");
                double k = fs.nextDouble(); System.out.println();

                System.out.println("The result is:");
                printMatrix(A.times(k), fs.sawFloat);

            } else if (choice == 3) {
                System.out.print("Enter size of first matrix: > ");
                prompt("");
                int n1 = fs.nextInt(), m1 = fs.nextInt(); System.out.println();
                System.out.println("Enter first matrix:");
                Matrix A = readMatrix(fs, n1, m1);

                System.out.print("Enter size of second matrix: > ");
                int n2 = fs.nextInt(), m2 = fs.nextInt(); System.out.println();
                System.out.println("Enter second matrix:");
                prompt("");
                Matrix B = readMatrix(fs, n2, m2);

                if (m1 != n2) {
                    System.out.println("The operation cannot be performed.");
                } else {
                    System.out.println("The result is:");
                    printMatrix(A.multiply(B), fs.sawFloat);
                }

            } else if (choice == 4) {
                printTransposeMenu();
                String tk = fs.next(); if (tk == null) return;
                int t; try { t = Integer.parseInt(tk); } catch (NumberFormatException e) { continue; }

                System.out.print("Enter matrix size: > ");
                prompt("");
                int n = fs.nextInt(), m = fs.nextInt(); System.out.println();
                System.out.println("Enter matrix:");
                prompt("");
                Matrix A = readMatrix(fs, n, m);

                Transposition kind;
                switch (t) {
                    case 1: kind = MAIN; break;
                    case 2: kind = SIDE; break;
                    case 3: kind = VERTICAL; break;
                    case 4: kind = HORIZONTAL; break;
                    default: continue;
                }
                System.out.println("The result is:");
                printMatrix(A.transpose(kind), fs.sawFloat);

            } else if (choice == 5) {
                System.out.print("Enter matrix size: > ");
                prompt("");
                int n = fs.nextInt(), m = fs.nextInt(); System.out.println();
                System.out.println("Enter matrix:");
                prompt("");
                Matrix A = readMatrix(fs, n, m);

                if (n != m) {
                    System.out.println("The operation cannot be performed.");
                } else {
                    double d = A.det();
                    System.out.println("The result is:");
                    System.out.println(Formatters.fmt(d, fs.sawFloat));
                }

            } else if (choice == 6) {
                System.out.print("Enter matrix size: > ");
                prompt("");
                int n = fs.nextInt(), m = fs.nextInt(); System.out.println();
                System.out.println("Enter matrix:");
                prompt("");
                Matrix A = readMatrix(fs, n, m);

                if (n != m) {
                    System.out.println("This matrix doesn't have an inverse.");
                } else {
                    Matrix inv = A.inverseOrNull();
                    if (inv == null) {
                        System.out.println("This matrix doesn't have an inverse.");
                    } else {
                        System.out.println("The result is:");
                        printMatrix2dp(inv);
                    }
                }
            }
        }
    }
}
