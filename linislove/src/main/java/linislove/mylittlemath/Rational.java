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
public class Rational {

    private BigInteger numerator;
    private BigInteger denominator;
    private String origNum;
    private String origDenom;
    public static final Rational ZERO = new Rational(0, 1);

    public Rational(Object numerator, Object denominator) {
        setRational(numerator, denominator);
    }

    public Rational(String number) {
        this(0);
        number = number.replaceAll("\\s", "");
        if (number.matches("\\-?\\d+\\/\\-?\\d+")) {
            String[] ratNumber = number.split("\\/");
            setRational(ratNumber[0], ratNumber[1]);
        } else if (number.matches("\\-?\\d+")) {
            setRational(Integer.parseInt(number), 1);
        } else if (number.matches("\\-?\\d*\\.\\d+")) {
            int digitsBeforePoint = number.indexOf(".");
            number = number.replaceAll("\\.", "");
            int numberOfDigits = number.length();
            BigInteger divider = BigInteger.TEN.pow(numberOfDigits - digitsBeforePoint);
            setRational(number, divider);
        } else {
            throw new RuntimeException("Syöte '" + number
                    + "' ei ole rationaaliluku.");
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

    public final void setRational(Object num, Object denom) {
        setNumerator(num);
        setDenominator(denom);
        simplify();
    }

    public BigInteger getNumerator() {
        return numerator;
    }

    public void setNumerator(Object num) {
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

    public BigInteger getDenominator() {
        return denominator;
    }

    public void setDenominator(Object denom) {
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
        return numerator + "/" + denominator;
    }

    public String original() {
        return origNum + "/" + origDenom;
    }
}
