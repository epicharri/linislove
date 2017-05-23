/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linislove.mylittlemath;

import static linislove.mylittlemath.Count.GCD;

/**
 *
 * @author harrikah
 */
public class Rational {
    
    private int numerator;
    private int denominator;
    private int origNum;
    private int origDenom;
    
    public Rational(int numerator, int denominator) {
        setRational(numerator, denominator);
    }
    
    public Rational(String number){
        this(0);
        if (number.matches("\\-?\\d+\\/\\d+")){
            String[] ratNumber = number.split("\\/");
            setRational(Integer.parseInt(ratNumber[0]), Integer.parseInt(ratNumber[1]));
        } else if (number.matches("\\-?\\d+")) {
            setRational(Integer.parseInt(number), 1);
        } else {
            throw new RuntimeException("Syöte '" + number +
                    "' ei ole kokonaisluku eikä rationaaliluku.");
        }
    }
    
    public Rational(int numerator) {
        this(numerator, 1);
    }
    
    public Rational(){
        this(0);
    }
    
    private void simplify() {
        if (numerator == 0) {
            denominator = 1;
            return;
        }
        if (denominator == 1) return;
        int a = Count.GCD(numerator, denominator);
        numerator = numerator / a; 
        denominator = denominator / a;
        numerator = Math.abs(numerator) * signum();
        denominator = Math.abs(denominator);
    }
    
    public int signum(){
        int signN = -1;
        if (numerator >= 0) signN = 1;
        if (numerator == 0) signN = 0;
        int signD = -1;
        if (denominator > 0) signD = 1;
        return signN * signD;        
    }
    
    public final void setRational(int numerator, int denominator){
        this.numerator = numerator;
        this.denominator = denominator;
        this.origNum = numerator;
        this.origDenom = denominator;        
        simplify();
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
        return upstairs == downstairs;
    }

    @Override
    public String toString() {
        if (numerator == 0) return ""+numerator;
        if (denominator == 1) return ""+numerator;
        return numerator + "/" + denominator;
    }
    
    public String original() {
        return origNum + "/" + origDenom;
    }
    
    
    
}
