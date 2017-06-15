package linislove.mylittlemath;

import java.math.BigInteger;

/**
 * Rationaalilukujen luokka. Rationaaliluku muodostuu kahdesta kokonaisluvusta,
 * osoittajasta ja nimittäjästä. Kokonaisluvut ovat BigInteger -luokan olioita.
 * Sen ansiosta laskutoimitukset voidaan tehdä rajattoman suuruisilla luvuilla.
 *
 */
public class Rational {

    private BigInteger numerator;
    private BigInteger denominator;
    private String origNum;
    private String origDenom;
    public static final Rational ZERO = new Rational(0, 1);
    public static final Rational ONE = new Rational(1, 1);

    /**
     * Konstruktori, luo osoittajasta ja nimittäjästä rationaaliluvun.
     *
     * @param numerator nimittäjä
     * @param denominator osoittaja
     */
    public Rational(Object numerator, Object denominator) {
        setRational(numerator, denominator);
    }

    public Rational(String number) {
        this(0);
        number = number.replaceAll("\\s", "");
        if (number.matches("(\\+|\\-)?\\(?(\\+|\\-)?\\d+\\/(\\+|\\-)?\\d+\\)?")) {
            String[] ratNumber = number.split("\\/");
            ratNumber[0] = removeRedundantMinuses(ratNumber[0].replaceAll("\\(", ""));
            ratNumber[1] = ratNumber[1].replaceAll("\\)", "");
            setRational(ratNumber[0], ratNumber[1]);
        } else if (number.matches("(\\+|\\-)?\\d+")) {
            setRational(Integer.parseInt(number), 1);
        } else if (number.matches("(\\-|\\+)?\\d*\\.\\d+")) {
            int digitsBeforePoint = number.indexOf(".");
            number = number.replaceAll("\\.", "");
            int numberOfDigits = number.length();
            BigInteger divider = BigInteger.TEN.pow(numberOfDigits - digitsBeforePoint);
            setRational(number, divider);
        } else {
            throw new RuntimeException("Syöte '" + number
                    + "' ei ole sallittu luku.");
        }
    }

    public Rational(Object numerator) {
        this(numerator, 1);
    }

    public Rational() {
        this(0);
    }

    public void simplify() {
        if (numerator.compareTo(BigInteger.ZERO) == 0) {
            denominator = BigInteger.ONE;
            return;
        }
        if (denominator.compareTo(BigInteger.ONE) == 0) {
            return;
        }
        BigInteger divider = numerator.gcd(denominator);
        numerator = numerator.divide(divider);
        denominator = denominator.divide(divider);
        numerator = signumOfRational().multiply(numerator.abs());
        denominator = denominator.abs();
    }

    public BigInteger signumOfRational() {
        int signN = this.numerator.compareTo(BigInteger.ZERO);
        int signD = this.denominator.compareTo(BigInteger.ZERO);
        return BigInteger.valueOf(signN * signD);
    }

    /**
     *
     * @param num
     * @param denom
     */
    private void setRational(Object num, Object denom) {
        if (!decentElementsOfFraction(num, denom)) {
            throw new RuntimeException("Rationaaliluvun muodostaminen "
                    + "mahdollinen vain luokista Integer, String ja "
                    + "BigInteger");
        }
        setNumerator(num);
        setDenominator(denom);
        simplify();
    }

    private boolean decentElementsOfFraction(Object num, Object denom) {
        Class n = num.getClass();
        Class d = denom.getClass();
        return isDecent(n) && isDecent(d);
    }

    private boolean isDecent(Object x) {
        return x == BigInteger.class
                || x == String.class
                || x == Integer.class;
    }

    public BigInteger getNumerator() {
        return numerator;
    }

    private void setNumerator(Object num) {
        if (num.getClass() == BigInteger.class) {
            this.numerator = (BigInteger) num;
            this.origNum = num.toString();
        }
        if (num.getClass() == String.class) {
            this.numerator = new BigInteger((String) num);
            this.origNum = (String) num;
        }
        if (num.getClass() == Integer.class) {
            this.numerator = BigInteger.valueOf((Integer) num);
            this.origNum = num.toString();
        }
    }

    private String removeRedundantMinuses(String number) {
        int numberOfMinuses = 0;
        for (int i = 0; i < number.length(); i++) {
            if (number.charAt(i) == '-') {
                numberOfMinuses++;
            }
        }
        number = number.replaceAll("-", "");
        if (numberOfMinuses % 2 == 1) {
            return "-" + number;
        }
        return number;
    }

    public BigInteger getDenominator() {
        return denominator;
    }

    private void setDenominator(Object denom) {
        if (denom.getClass() == BigInteger.class) {
            this.denominator = (BigInteger) denom;
            this.origDenom = denom.toString();
        }
        if (denom.getClass() == String.class) {
            this.denominator = new BigInteger((String) denom);
            this.origDenom = (String) denom;
        }
        if (denom.getClass() == Integer.class) {
            this.denominator = BigInteger.valueOf((Integer) denom);
            this.origDenom = denom.toString();
        }
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
    public String toString() {
        if (numerator.equals(BigInteger.ZERO)) {
            return "" + numerator;
        }
        if (denominator.equals(BigInteger.ONE)) {
            return "" + numerator;
        }
        return "(" + numerator + "/" + denominator + ")";
    }

    public String original() {
        return origNum + "/" + origDenom;
    }
}
