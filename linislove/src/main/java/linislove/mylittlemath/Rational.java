package linislove.mylittlemath;

import java.math.BigInteger;
import java.util.Objects;
import java.util.InputMismatchException;

/**
 * Rationaalilukujen luokka. Rationaaliluku muodostuu kahdesta kokonaisluvusta,
 * osoittajasta ja nimittäjästä. Kokonaisluvut ovat BigInteger -luokan olioita.
 * Sen ansiosta laskutoimitukset voidaan tehdä rajattoman suuruisilla luvuilla.
 *
 */
public class Rational extends RationalOperations {

    /**
     * Rationaaliluku 0.
     */
    public static final Rational ZERO = new Rational(BigInteger.ZERO, BigInteger.ONE);
    /**
     * Rationaaliluku 1.
     */
    public static final Rational ONE = new Rational(BigInteger.ONE, BigInteger.ONE);

    /**
     * Konstruktori. Luo rationaaliluvun.
     *
     * @param numerator Luvun osoittaja.
     * @param denominator Luvun nimittäjä.
     */
    public Rational(int numerator, int denominator) {
        this(BigInteger.valueOf(numerator), BigInteger.valueOf(denominator));
    }

    /**
     * Konstruktori. Luo rationaaliluvun.
     *
     * @param numerator Luvun osoittaja.
     * @param denominator Luvun nimittäjä.
     */
    public Rational(int numerator, BigInteger denominator) {
        this(BigInteger.valueOf(numerator), denominator);
    }

    /**
     * Konstruktori. Luo rationaaliluvun.
     *
     * @param numerator Luvun osoittaja.
     * @param denominator Luvun nimittäjä.
     */
    public Rational(BigInteger numerator, int denominator) {
        this(numerator, BigInteger.valueOf(denominator));
    }

    /**
     * Konstruktori. Luo rationaaliluvun.
     *
     * @param numerator Luvun osoittaja.
     * @param denominator Luvun nimittäjä.
     */
    public Rational(BigInteger numerator, BigInteger denominator) {
        setRational(numerator, denominator);
    }

    /**
     * Konstruktori. Luo rationaaliluvun joka on kokonaisluku.
     *
     * @param numerator Luvun osoittaja. Nimittäjäksi sijoitetaan
     * automaattisesti 1.
     */
    public Rational(BigInteger numerator) {
        this(numerator, BigInteger.ONE);
    }

    /**
     * Konstruktori. Luo rationaaliluvun joka on kokonaisluku.
     *
     * @param numerator Luvun osoittaja. Nimittäjäksi sijoitetaan
     * automaattisesti 1.
     */
    public Rational(int numerator) {
        this(BigInteger.valueOf(numerator));
    }

    /**
     * Konstruktori. Luo rationaaliluvun merkkijonosta.
     *
     * @param number Rationaaliluku merkkijonona.
     * @throws NumberFormatException jos annetusta syötteestä ei voi muodostaa
     * rationaalilukua.
     */
    public Rational(String number) throws NumberFormatException {
        number = number.replaceAll("\\s", "");
        if (isRational(number)) {
            parseRationalFromFractionString(number);
        } else if (isWholeNumber(number)) {
            parseRationalFromWholeNumber(number);
        } else if (isDecimalNumber(number)) {
            parseRationalFromDecimalString(number);
        } else {
            throw new InputMismatchException("Syöte on virheellinen: " + number + " ei ole rationaaliluku, "
                    + "kokonaisluku tai desimaaliluku.\n"
                    + "Muista syöttää desimaaliluvut käyttämällä pistettä erottimena.");
        }
    }

    /**
     * Metodi lukee merkkijonon ja asettaa osoittajan ja nimittäjän. Tätä
     * metodia kutsutaan vain kun on ensin testattu että merkkijono on
     * kelvollinen muunnettavaksi.
     *
     * @param number Merkkijono esimerkiksi muodossa -(-2/-7) tai 3/9. Regex:
     * "(\\+|\\-)?\\(?(\\+|\\-)?\\d+\\/(\\+|\\-)?\\d+\\)?".
     */
    private void parseRationalFromFractionString(String number) {
        String[] ratNumber = number.split("\\/");
        ratNumber[0] = removeRedundantMinusesAndPluses(ratNumber[0].replaceAll("\\(", ""));
        ratNumber[1] = ratNumber[1].replaceAll("\\)", "");
        setRational(new BigInteger(ratNumber[0]), new BigInteger(ratNumber[1]));
    }

    private void parseRationalFromDecimalString(String number) {
        number = removeBracketsAndRedundantMinuses(number);
        int digitsBeforePoint = number.indexOf(".");
        number = number.replaceAll("\\.", "");
        int numberOfDigits = number.length();
        BigInteger divider = BigInteger.TEN.pow(numberOfDigits - digitsBeforePoint);
        setRational(new BigInteger(number), divider);
    }

    private void parseRationalFromWholeNumber(String number) {
        number = removeBracketsAndRedundantMinuses(number);
        setRational(new BigInteger(number), BigInteger.ONE);
    }

    private String removeBrackets(String number) {
        return number.replaceAll("(\\(|\\))", "");
    }

    private String removeBracketsAndRedundantMinuses(String number) {
        number = removeBrackets(number);
        return removeRedundantMinusesAndPluses(number);
    }

    private boolean isRational(String number) {
        return number.matches("(\\+|\\-)?\\(?(\\+|\\-)?\\d+\\/(\\+|\\-)?\\d+\\)?");
    }

    private boolean isWholeNumber(String number) {
        return number.matches("(\\+|\\-)?\\(?(\\+|\\-)?\\d+\\)?");
    }

    private boolean isDecimalNumber(String number) {
        return number.matches("(\\+|\\-)?\\(?(\\-|\\+)?\\d*\\.\\d+\\)?");
    }

    private void simplify() {
        if (numerator.compareTo(BigInteger.ZERO) == 0) {
            denominator = BigInteger.ONE;
            return;
        }
        if (denominator.compareTo(BigInteger.ONE) == 0) {
            return;
        }
        BigInteger divider = numerator.gcd(denominator);
        //if (divider.equals(BigInteger.ONE)) return;
        numerator = numerator.divide(divider);
        denominator = denominator.divide(divider);
        numerator = signumOfRational().multiply(numerator.abs());
        denominator = denominator.abs();
    }

    private void setRational(BigInteger num, BigInteger denom) {
        if (denom.equals(BigInteger.ZERO)) {
            throw new NumberFormatException("Nollalla ei voi jakaa.");
        }
        this.numerator = num;
        this.denominator = denom;
        simplify();
    }

    private String removeRedundantMinusesAndPluses(String number) {
        int numberOfMinuses = 0;
        for (int i = 0; i < number.length(); i++) {
            if (number.charAt(i) == '-') {
                numberOfMinuses++;
            }
        }
        number = number.replaceAll("-|\\+", "");
        if (numberOfMinuses % 2 == 1) {
            return "-" + number;
        }
        return number;
    }

    /**
     * Metodi palauttaa rationaaliluvun merkin.
     *
     * @return BigInteger -oliona rationaaliluvun merkki (-1, 0 tai 1).
     */
    public BigInteger signumOfRational() {
        int signN = this.numerator.compareTo(BigInteger.ZERO);
        int signD = this.denominator.compareTo(BigInteger.ZERO);
        return BigInteger.valueOf(signN * signD);
    }

    public BigInteger getNumerator() {
        return numerator;
    }

    public BigInteger getDenominator() {
        return denominator;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Rational other = (Rational) obj;
        return (this.numerator.equals(other.numerator)
                && this.denominator.equals(other.denominator));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.numerator);
        hash = 89 * hash + Objects.hashCode(this.denominator);
        return hash;
    }

    @Override
    public String toString() {
        if (numerator.equals(BigInteger.ZERO)) {
            return "0";
        }
        if (denominator.equals(BigInteger.ONE)) {
            return numerator.toString();
        }
        return "(" + numerator + "/" + denominator + ")";
    }

}
