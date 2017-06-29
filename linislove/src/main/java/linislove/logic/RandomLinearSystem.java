/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linislove.logic;

import linislove.mylittlemath.LinearSystem;
import linislove.mylittlemath.Rational;

/**
 * Luokka satunnaisen kvadraattisen yhtälöryhmän luomista varten.
 *
 * @author harrikah
 */
public class RandomLinearSystem {

    /**
     * Luo satunnaisen kvadraattisen yhtälöryhmän.
     *
     * @param x Tuntemattomien ja samalla yhtälöiden määrä.
     * @param maxNum Suurin arvo osoittajalle
     * @param maxDenom Suurin arvo nimittäjälle
     * @return LinearSystem -luokan olio.
     * @throws java.lang.Exception jos rationaalilukua luodessa tulee ongelma.
     *
     */
    public static LinearSystem create(int x, int maxNum, int maxDenom) throws Exception {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < x; j++) {
                int subscript = j + 1;
                str.
                        append(randomPlusOrMinus()).
                        append(randomRational(maxNum, maxDenom).toString()).
                        append('x').
                        append(subscript);
            }
            str.append('=').append(randomPlusOrMinus()).
                    append(randomRational(maxNum, maxDenom).toString()).
                    append(';');
        }
        return new LinearSystem(str.toString());
    }

    private static Rational randomRational(int maxNum, int maxDenom) {
        maxNum = randomInt(maxNum);
        maxDenom = randomInt(maxDenom) + 1;
        return new Rational(maxNum, (maxDenom));
    }

    private static int randomInt(int maxInt) {
        return (int) (Math.random() * maxInt);
    }

    private static String randomPlusOrMinus() {
        return (Math.random() >= 0.5) ? "-" : "+";
    }

}
