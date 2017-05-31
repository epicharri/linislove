/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linislove.mylittlemath;

import java.math.BigInteger;

/**
 *
 * @author harrikah
 */
public class Count {

    public static Rational product(Rational a, Rational b) {
        BigInteger upstairs = a.getNumerator().multiply(b.getNumerator());
        BigInteger downstairs = a.getDenominator().multiply(b.getDenominator());
        return new Rational(upstairs, downstairs);
    }

    public static Rational reciprocal(Rational a) {
        return new Rational(a.getDenominator(), a.getNumerator());
        // tarkastettava ettei ole 0.
    }

    public static Rational opposite(Rational a) {
        return new Rational(a.getNumerator().negate(), a.getDenominator());
    }

    public static Rational sum(Rational a, Rational b) {
        if (a.getDenominator().equals(b.getDenominator())){
            return new Rational(a.getNumerator().add(b.getNumerator()),
                    a.getDenominator());
        }
        BigInteger upstairs = a.getNumerator().multiply(b.getDenominator())
                .add(b.getNumerator().multiply(a.getDenominator()));
        BigInteger downstairs = a.getDenominator().multiply(b.getDenominator());
        return new Rational(upstairs, downstairs);
    }

    public static Rational difference(Rational a, Rational b) {
        return sum(a, opposite(b));
    }

    public static int signum(Rational a) {
        return a.signumOfRational().intValue();
    }

    public static int powint(int a, int x) {
        if (x < 0) {
            throw new RuntimeException("Exponentti ei ole positiivinen");
        }
        if (x == 0) {
            return 1;
        }
        if (x == 1) {
            return a;
        }
        if (x % 2 == 0) {
            return powint(a * a, x / 2);
        } else {
            return a * powint(a * a, x / 2);
        }
    }

    public static int minusOnePoweredTo(int n){
        return (n%2 == 0) ? 1 : -1;
    }
    
    public static Rational det(Matrix matrix) {
        if (matrix.getM() != matrix.getN() || matrix.getN() == 0) return null;
        return determinant(matrix.getMatrixArray(), matrix.getM(), matrix.getN());
    }

    public static Rational determinant(Rational[][] A, int m, int n) {
        Rational detA = new Rational(0, 1);
        if (n == 1) {
            detA = A[0][0];
        } else if (n == 2) {
            detA = difference(product(A[0][0], A[1][1]),
                    product(A[1][0], A[0][1]));
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
                        c[i - 1][j2] = A[i][j];
                        j2++;
                    }
                }
                detA = Count.sum(detA, product(product(new Rational(minusOnePoweredTo(1 + j1 + 1)),
                        A[0][j1]), determinant(c, n - 1, n - 1)));
            }
        }
        return detA;
    }

    public static Matrix transpose(Matrix A){
        int n = A.getM();
        int m = A.getN();
        Rational[][] arrayT = new Rational[n][m];
        Rational[][] arrayA = A.getMatrixArray();
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                arrayT[j][i] = arrayA[i][j];
            }
        }
        return new Matrix(arrayT, m, n, A.getLongest());        
    }

    public static Matrix multiply(Matrix A, Matrix B){
        Rational[][] a = A.getMatrixArray();
        Rational[][] b = B.getMatrixArray();
        int aN = A.getN();
        int aM = A.getM();
        int bN = B.getN();
        int bM = B.getM();
        if (aN != bM) throw new RuntimeException("Matriisikertolasku ei ole määritelty näillä matriiseilla.");
        Matrix AB = new Matrix(aM, bN);
        Rational[][] ab = AB.getMatrixArray();
        
        for (int i = 0; i < AB.getM(); i++){
            for (int j = 0; j < AB.getN(); j++){
                for (int k = 0; k < aN; k++){
                    if (ab[i][j] == null) ab[i][j] = new Rational(0);
                    AB.setNumber(Count.sum(ab[i][j], 
                        Count.product(a[k][i], b[j][k])), j, i);
                }
            }
        }
        return AB;       
    }

    public static Matrix solveByCramerRule(Matrix A, Matrix b){
        if (A.getM() != A.getN()) throw new RuntimeException(""
                + "Cramerin säännöllä voi ratkaista vain kvadraattisia "
                + "yhtälöryhmiä.");
        if (!(A.getM() == b.getM())) {
            throw new RuntimeException("Matriisit annettava siten että "
                    + "A:ssa ja b:ssä on saman verran rivejä.");
        }
        Matrix valuesOfX = new Matrix(A.getM(), 1);
        Rational detA = det(A);
        Rational detAreciprocal = reciprocal(detA);
        for (int i = 0; i < b.getM(); i++){
            Rational deti = det(substitute(A, b, i));
            Rational x = product(deti, detAreciprocal);
            valuesOfX.setNumber(x, i, 0);
        }
        return valuesOfX;
    }
    
    public static Matrix substitute(Matrix A, Matrix b, int j){
        Matrix replaced = createCopy(A);
        for (int i = 0; i < b.getM(); i++){
            Rational number = b.getNumber(i, 0);
            replaced.setNumber(number, i, j);
        }
        return replaced;
    }
    
    public static Matrix createCopy(Matrix A){
        Matrix copy = new Matrix(A.getM(), A.getN());
        for (int i = 0; i < A.getM(); i++){
            for (int j = 0; j < A.getN(); j++){
                Rational number = A.getNumber(i, j);
                copy.setNumber(number, i, j);
            }
        }
        return copy;
        
    }
    
    
    public static int gcd(int num, int denom) {
        if (denom == 0) {
            return num;
        }
        return gcd(denom, num % denom);
    }
}
