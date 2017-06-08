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
     * @param A Matriisi A eli kerroinmatriisi yhtälöstä Ax=b
     * @param b Matriisi b eli tulosmatriisi yhtälöstä Ax=b
     * @return Palauttaa yhtälöryhmän tulostettavassa muodossa, esimerkki: x_1 +
     * 2x_2 = 3 -(4/5)x_1 + 17x_2 = 9775/12005777
     *
     */
    /*
    public static String linearSystem(Matrix A, Matrix b) {
        String print = "";
        int m = A.getM();
        int n = A.getN();
        if (b.getM() != m) {
            return "Matriisit A ja b eri "
                    + "korkuisia. A:ssa rivejä " + m + " ja b:ssä " + b.getM();
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Rational number = A.getNumber(i, j);
                String element = giveCoefficientAndVariableX(number, j);
                print += element + " ";
            }
            print += "= " + b.getNumber(i, 0) + "\n";
        }
        return print;
    }

    private static String giveCoefficientAndVariableX(Rational number, int j) {
        String print = "";

        int sign = Count.signum(number);
        if (sign == 0) {
            return "";
        }
        if (j > 0 && sign > 0) {
            print += "+ ";
        }
        if (sign < 0) {
            print += "-";
        }
        if (sign < 0) {
            number = Count.opposite(number);
        }
        if (number.getDenominator().equals(BigInteger.ONE)) {
            print += number.toString();
        } else {
            print += "(" + number.getNumerator() + "/" + number.getDenominator() + ")";
        }
        print += "x_" + (j + 1);
        return print;
    }

    public static String checkAnswer(Matrix A, Matrix x, Matrix b) {
        int m = A.getM();
        int n = A.getN();
        if (b.getM() != m) {
            return "Matriisit A ja b eri "
                    + "korkuisia. A:ssa rivejä " + m + " ja b:ssä " + b.getM();
        }
        for (int i = 0; i < m; i++) {
            Rational numberB = b.getNumber(i, 0);
            Rational sumOfRow = new Rational(0);
            for (int j = 0; j < n; j++) {
                Rational numberA = A.getNumber(i, j);
                Rational numberX = x.getNumber(j, 0);
                Rational ax = Count.product(numberA, numberX);
                sumOfRow = Count.sum(sumOfRow, ax);
            }
            if (!sumOfRow.equals(numberB)) {
                return "Yhtälöryhmän ratkaisu ei ole oikein";
            }
        }
        return "Yhtälöryhmän ratkaisu on tarkistettu ja on täsmälleen oikein.";
    }
*/
}
