/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linislove.mylittlemath;

import static linislove.mylittlemath.Count.difference;
import static linislove.mylittlemath.Count.minusOnePoweredTo;
import static linislove.mylittlemath.Count.product;

/**
 * Luokka sisältää matriisien laskentaoperaatioita, joita käytetään Count-luokan
 * kautta.
 *
 * @author harrikah
 */
public abstract class MatrixOperations {

    /**
     * Metodilla saa selville matriisin determinantin.
     *
     * @param matrix Matrix -luokan olio
     * @return Matriisin determinantti Rational -lukuna.
     */
    public static Rational det(Matrix matrix) {
        if (matrix.getM() != matrix.getN() || matrix.getN() == 0) {
            throw new IllegalArgumentException("Vain neliömatriisista voi laskea determinantin.");
        }
        return determinant(matrix.getMatrixArray(), matrix.getM(), matrix.getN());
    }

    private static Rational determinant(Rational[][] matrixA, int m, int n) {
        Rational detA = new Rational(0, 1);
        if (n == 1) {
            detA = matrixA[0][0];
        } else if (n == 2) {
            detA = difference(product(matrixA[0][0], matrixA[1][1]),
                    product(matrixA[1][0], matrixA[0][1]));
        } else {
            for (int j1 = 0; j1 < n; j1++) {
                Rational[][] c = new Rational[n - 1][];
                for (int k = 0; k < (n - 1); k++) {
                    c[k] = new Rational[n - 1];
                }
                for (int i = 1; i < n; i++) {
                    int j2 = 0;
                    for (int j = 0; j < n; j++) {
                        if (j == j1) {
                            continue;
                        }
                        c[i - 1][j2] = matrixA[i][j];
                        j2++;
                    }
                }
                detA = Count.sum(detA, product(product(new Rational(minusOnePoweredTo(1 + j1 + 1)),
                        matrixA[0][j1]), determinant(c, n - 1, n - 1)));
            }
        }
        return detA;
    }

    /**
     * Metodi luo matriisista transpoosin.
     *
     * @param matrixA Matrix -luokan olio, matriisi josta transpoosi luodaan
     * @return Matrix -luokan olion, joka on matrixA:n transpoosi.
     */
    public static Matrix transpose(Matrix matrixA) {
        int m = matrixA.getM();
        int n = matrixA.getN();
        Rational[][] arrayT = new Rational[n][m];
        Rational[][] arrayA = matrixA.getMatrixArray();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arrayT[j][i] = arrayA[i][j];
            }
        }
        return new Matrix(arrayT);
    }

    /**
     * Matriisikertolasku.
     *
     * @param matrixA Matrix -luokan matriisi jolla kerrotaan.
     * @param matrixB Matrix -luokan matriisi joka kerrotaan.
     * @return Matrix -luokan matriisi, matriisitulo matrixA*matrixB.
     */
    public static Matrix multiply(Matrix matrixA, Matrix matrixB) {
        Rational[][] a = matrixA.getMatrixArray();
        Rational[][] b = matrixB.getMatrixArray();
        int aN = matrixA.getN();
        int aM = matrixA.getM();
        int bN = matrixB.getN();
        int bM = matrixB.getM();
        if (aN != bM) {
            throw new IllegalArgumentException("Matriisikertolasku ei ole määritelty näillä matriiseilla.");
        }
        Matrix matrixAb = new Matrix(aM, bN);
        Rational[][] ab = matrixAb.getMatrixArray();

        for (int i = 0; i < matrixAb.getM(); i++) {
            for (int j = 0; j < matrixAb.getN(); j++) {
                for (int k = 0; k < aN; k++) {
                    if (ab[i][j] == null) {
                        ab[i][j] = new Rational(0);
                    }
                    matrixAb.setNumber(
                            Count.sum(
                                    ab[i][j],
                                    Count.product(a[i][k], b[k][j])
                            ),
                            i, j);
                }
            }
        }
        return matrixAb;
    }
}
