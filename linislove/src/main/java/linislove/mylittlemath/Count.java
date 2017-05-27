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

    public static int gcd(int num, int denom) {
        if (denom == 0) {
            return num;
        }
        return gcd(denom, num % denom);
    }
}
