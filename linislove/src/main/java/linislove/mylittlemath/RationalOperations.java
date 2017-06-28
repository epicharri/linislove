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
public abstract class RationalOperations {

    protected BigInteger numerator;
    protected BigInteger denominator;

    public Rational abs() {
        Rational number = thisNumber();
        if (Count.signum(number) >= 0) {
            return number;
        }
        return Count.opposite(number);
    }

    private Rational thisNumber() {
        return new Rational(numerator, denominator);
    }

    public boolean isEqualTo(Rational other) {
        Rational number = thisNumber();
        return number.equals(other);
    }

    public boolean isZero() {
        return this.numerator.equals(Rational.ZERO);
    }

    public boolean greaterThan(Rational other) {
        Rational number = thisNumber();
        if (number.equals(other)) {
            return false;
        }
        BigInteger denomNumber = number.getDenominator();
        BigInteger denomOther = other.getDenominator();
        BigInteger numNumber = number.getNumerator();
        BigInteger numOther = other.getNumerator();
        BigInteger expandedNumOfNumber = numNumber.multiply(denomOther);
        BigInteger expandedNumOfOther = numOther.multiply(denomNumber);
        return expandedNumOfNumber.equals(expandedNumOfNumber.max(expandedNumOfOther));
    }

    public boolean lessThan(Rational other) {
        return other.greaterThan(thisNumber());
    }

    public boolean greaterOrEqual(Rational other) {
        Rational number = thisNumber();
        return (number.equals(other) || number.greaterThan(other));
    }

    public boolean lessThanOrEqual(Rational other) {
        Rational number = thisNumber();
        return number.equals(other) || number.lessThan(other);
    }

    public Rational multiply(Rational b) {
        Rational a = thisNumber();
        if (a.isEqualTo(Rational.ZERO) || b.isEqualTo(Rational.ZERO)) {
            return Rational.ZERO;
        }
        BigInteger upstairs = a.getNumerator().multiply(b.getNumerator());
        BigInteger downstairs = a.getDenominator().multiply(b.getDenominator());
        return new Rational(upstairs, downstairs);
    }

    /**
     * Palauttaa rationaaliluvun käänteisalkion kertolaskun suhteen.
     *
     * @return käänteisalkio.
     */
    public Rational reciprocal() {
        if (!this.numerator.equals(BigInteger.ZERO)) {
            return new Rational(denominator, numerator);
        } else {
            throw new NumberFormatException("Nollalla ei ole käänteislukua.");
        }
    }

    /**
     * Metodi palauttaa rationaaliluvun käänteisalkion yhteenlaskun suhteen.
     *
     * @return Käänteisalkio yhteenlaskun suhteen.
     */
    public Rational opposite() {
        return new Rational(numerator.negate(), denominator);
    }

    /**
     *
     * @param b
     * @return
     */
    public Rational plus(Rational b) {
        if (denominator.equals(b.getDenominator())) {
            return new Rational(numerator.add(b.getNumerator()),
                    denominator);
        }
        if (numerator.equals(BigInteger.ZERO)) {
            return b;
        }
        if (b.getNumerator().equals(BigInteger.ZERO)) {
            return thisNumber();
        }
        BigInteger upstairs = numerator.multiply(b.getDenominator())
                .add(b.getNumerator().multiply(denominator));
        BigInteger downstairs = denominator.multiply(b.getDenominator());
        return new Rational(upstairs, downstairs);
    }

    /**
     * Metodi palauttaa kahden rationaaliluvun (Rational -luokan olio)
     * erotuksen.
     *
     * @param b Luku joka vähennetään.
     * @return Luku joka on edellisten erotus eli a-b.
     */
    public Rational minus(Rational b) {
        return thisNumber().plus(b.opposite());
    }

    /**
     * Metodi palauttaa rationaalilukujen jakolaskun tuloksen.
     *
     * @param b Jakaja
     * @return Jakolaskun tulos: a.divide(b) palauttaa a / b. Metodi palauttaa
     * poikkeuksen, jos jakaja on nolla.
     */
    public Rational divide(Rational b) throws IllegalArgumentException {
        if (b.getNumerator().equals(BigInteger.ZERO)) {
            throw new IllegalArgumentException("Nollalla ei voi jakaa.");
        }
        return thisNumber().multiply(b.reciprocal());
    }

    /**
     * Metodi palauttaa signumin.
     *
     * @return -1 jos luku < 0, 0 jos luku == 0 ja 1 jos luku > 0.
     */
    public int signum() {
        return thisNumber().signumOfRational().intValue();
    }
}
