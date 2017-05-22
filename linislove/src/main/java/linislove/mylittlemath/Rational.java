/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linislove.mylittlemath;

/**
 *
 * @author harrikah
 */
public class Rational {
    
    private int numerator;
    private int denominator;
    
    public Rational(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }
    
    public Rational(int numerator) {
        this(numerator, 1);
    }
    
    public Rational(){
        this(0);
    }
    
    
    public int getNumerator() {
        return numerator;
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public void setDenominator(int denominator) {
        this.denominator = denominator;
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
        if (this.numerator != other.numerator) {
            return false;
        }
        int upstairs = this.denominator * other.numerator;
        int downstairs = this.numerator * other.denominator;
        if (upstairs != downstairs) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }
    
    
    
}
