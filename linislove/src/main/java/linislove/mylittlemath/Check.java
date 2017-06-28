/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linislove.mylittlemath;

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
     * @param x Matriisi x eli ratkaisumatriisi yhtälöstä Ax=b
     * @return Teksti joka kertoo onko yhtälöryhmän ratkaisu oikein vai väärin.
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

    /**
     * Metodi tarkastaa onko yhtälöryhmän ratkaisu oikein.
     *
     * @param system LinearSystem -olio
     * @param answers Rational[] -olio, joka sisältää ratkaisut.
     * @return
     */
    public static String checkAnswer(LinearSystem system, Rational[] answers) {
        return checkAnswer(system.getA(), answers, system.getB());
    }
}
