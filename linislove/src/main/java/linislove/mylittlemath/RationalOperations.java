/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linislove.mylittlemath;

import java.math.BigInteger;

/**
 * Tämä luokka sisältää Rational -luokan tarvitsemia laskenta- ja
 * vertailuoperaatioita.
 *
 * @author harrikah
 */
public abstract class RationalOperations {

    protected BigInteger numerator;
    protected BigInteger denominator;

    /**
     * Selvittää Rational -luvun itseisarvon.
     *
     * @return Rationaaliluvun itseisarvon.
     */
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

    /**
     * Selvittää onko kaksi rationaalilukua yhtäsuuret.
     *
     * @param other b yhtälöstä a=b.
     * @return true, jos a=b. Käyttö: boolean areEqual = a.isEqualTo(b);.
     */
    public boolean isEqualTo(Rational other) {
        Rational number = thisNumber();
        return number.equals(other);
    }

    /**
     * Selvittää onko rationaaliluku 0.
     *
     * @return true jos nolla, false muutoin.
     */
    public boolean isZero() {
        return this.numerator.equals(BigInteger.ZERO);
    }

    /**
     * Selvittää onko a suurempi kuin b.
     *
     * @param other b yhtälöstä a suurempi kuin b.
     * @return true, jos s suurempi kuin b, muutoin false. Käyttö: boolean greater =
     * a.greaterThan(b);.
     */
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

    /**
     * Selvittää onko a aidosti pienempi kuin b.
     *
     * @param other Luku b epäyhtälöstä a pienempi kuin b
     * @return True, jos a pienempi kuin b, muutoin false. Käyttö: boolean less
     * = a.lessThan(b);.
     */
    public boolean lessThan(Rational other) {
        return other.greaterThan(thisNumber());
    }

    /**
     * Selvittää onko a suurempi tai yhtäsuuri kuin b.
     *
     * @param other Luku b epäyhtälöstä a suurempi tai yhtäsuuri kuin b.
     * @return true, jos a suurempi tai yhtäsuuri kuin b, muulloin false.
     */
    public boolean greaterOrEqual(Rational other) {
        Rational number = thisNumber();
        return (number.equals(other) || number.greaterThan(other));
    }

    /**
     * Selvittää onko luku yhtäsuuri tai pienempi kuin.
     *
     * @param other Luku johon verrataan.
     * @return Palauttaa true tai false. Selvitettää onko a pienempi tai
     * yhtäsuuri kuin b muodossa a.lessThanOrEqual(b).
     */
    public boolean lessThanOrEqual(Rational other) {
        Rational number = thisNumber();
        return number.equals(other) || number.lessThan(other);
    }

    /**
     * Metodi kertoo rationaaliluvut.
     *
     * @param b Luku jolla rationaaliluku kerrotaan.
     * @return Kertolaskun tuloksen a * b. Metodia kutsutaan a.multiply(b),
     * jossa a ja b ovat Rational -luokan olioita.
     */
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
     * @return Käänteisalkio kertolaskun suhteen, Rational -luokan olio.
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
     * Metodi palauttaa rationaalilukujen (luokka Rational) yhteenlaskun
     * tuloksen.
     *
     * @param b Luku joka lisätään.
     * @return Palauttaa summan rationaalilukuna.
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
     * @throws java.lang.IllegalArgumentException
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
