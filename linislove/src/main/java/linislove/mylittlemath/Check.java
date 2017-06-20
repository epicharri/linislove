/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linislove.mylittlemath;

import java.math.BigInteger;

/**
 *
 * Luokka tarkastaa laskujen tuloksia.
 */
public class Check {

    /**
     * Metodi palauttaa kerroinmatriisin ja tulosmatriisin mukaisen yhtälöryhmän
     * tulostettavassa muodossa.
     *
     * @param a Matriisi a eli kerroinmatriisi yhtälöstä Ax=b
     * @param b Matriisi b eli tulosmatriisi yhtälöstä Ax=b
     * @return Palauttaa yhtälöryhmän tulostettavassa muodossa, esimerkki: x_1 +
     * 2x_2 = 3 -(4/5)x_1 + 17x_2 = 9775/12005777
     *
     */

    public static String checkAnswer(Rational[][] a, Rational[] x, Rational b[][]) {
        int m = a.length;
        int n = a.length;
        if (b.length != m) {
            return "Matriisit A ja b eri "
                    + "korkuisia. A:ssa rivejä " + m + " ja b:ssä " + b.length;
        }
        for (int i = 0; i < m; i++) {
            Rational numberB = b[i][0];
            Rational sumOfRow = new Rational(0);
            for (int j = 0; j < n; j++) {
                Rational numberA = a[i][j];
                Rational numberX = x[j];
                Rational ax = Count.product(numberA, numberX);
                sumOfRow = Count.sum(sumOfRow, ax);
            }
            if (!sumOfRow.equals(numberB)) {
                return "Yhtälöryhmän ratkaisu ei ole oikein";
            }
        }
        return "Yhtälöryhmän ratkaisu on tarkistettu ja on täsmälleen oikein.";
    }
     
    public static String checkAnswer(LinearSystem system, Rational[] answers){
        return checkAnswer(system.getA(), answers, system.getB());
    }
}
