/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linislove.linislove;

import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import linislove.mylittlemath.Count;
import linislove.mylittlemath.Matrix;
import linislove.mylittlemath.Rational;

/**
 *
 * @author harrikah
 */
public class LinisLove {

    public static void main(String[] args) throws InterruptedException {
        // Tässä main -metodissa toistaiseksi vain testaillaan logiikan
        // toimivuutta. Tämä on siis tavallaan hiekkalaatikkona nyt.
        // Luokkakaavion url: http://yuml.me/diagram/scruffy;dir:LR/class/edit/%2F%2F Luokkakaavio, [LinisLove(main)]-[Gui], [Gui]-[Logic], [Logic]-[Matrix], [Matrix]-[Count], [Matrix]-[Rational], [Rational]-[Count]
        Rational a = new Rational(55, -200);
        Rational b = new Rational(-100, -45);
        Rational zero = new Rational(0, 20);
        Rational wholeNumber = new Rational(4);
        System.out.println("a = " + a.original() + " = " + a);
        System.out.println("b = " + b.original() + " = " + b);
        System.out.println("nolla = " + zero.original() + " = " + zero);
        System.out.println("a * b = " + Count.product(a, b));
        System.out.println("a * " + wholeNumber.original() + " = " + Count.product(a, wholeNumber));
        System.out.println("a + b = " + Count.sum(a, b));
        System.out.println("a + " + wholeNumber.original() + " = " + Count.sum(a, wholeNumber));
        System.out.println("a - b = " + Count.difference(a, b));

        String v = "(1,0,0),(0,1,0),(0,0,1)";
        Matrix matrix = new Matrix(v);
        System.out.println("");
        System.out.println(matrix);
        Rational detMatrix = Count.det(matrix);
        String text = (detMatrix.equals(new Rational(0))) ? "0, on vektorijono "
                + "lineaarisesti riippuvainen." : "erisuuri kuin 0, on "
                + "vektorijono vapaa.";
        System.out.println("Matriisin determinantti on " + detMatrix + ". "
                + "Koska vektorijonosta muodostetun matriisin determinantti on "
                + text);

        System.out.println("--------------");
        String vv = "(10,-11/17,7/113),(17/33,1/3,2/5),(.333333333333333333,324523424523452.244444,45265677456745.344)";
        Matrix matrixv = new Matrix(vv);
        System.out.println("");
        System.out.println(matrixv);
        Rational detMatrixv = Count.det(matrixv);
        String textv = (detMatrixv.equals(new Rational(0))) ? "0, on vektorijono "
                + "lineaarisesti riippuvainen." : "erisuuri kuin 0, on "
                + "vektorijono vapaa.";
        System.out.println("Matriisin determinantti on " + detMatrixv + ". "
                + "Koska vektorijonosta muodostetun matriisin determinantti on "
                + textv);

        System.out.println("--------------");

        //String w = "(1,1,1),(-1/2,-2,-2/3),(0,1,1)";
        String w = "";
        int n = 6;
        for (int i = 0; i < n; i++) {
            w += "(";
            for (int j = 0; j < n; j++) {
            
                BigInteger num = BigInteger.ONE;
                BigInteger denom = BigInteger.ONE;
                for (int k = 0; k < 10; k++){
                    BigInteger num1 = BigInteger.valueOf((int) (Math.random() * Integer.MAX_VALUE));
                    BigInteger denom1 = BigInteger.valueOf((int) (Math.random() * Integer.MAX_VALUE) + 1);
                    num = num.multiply(num1);
                    denom = denom.multiply(denom1);
                }
                
                w += num+"13/"+denom+"17";
                if (j < n - 1) {
                    w += ",";
                } else {
                    w += ")";
                }
            }
            if (i < n - 1) {
                w += ",";
            }
        }
        Matrix B = new Matrix(w);
        System.out.println("");
        System.out.println(B);
        System.out.println("");
        
        Instant start = Instant.now();
        
        Rational detB = Count.det(B);
        
        Instant end = Instant.now();

        
        String textB = (detB.equals(new Rational(0))) ? "0, on vektorijono "
                + "lineaarisesti riippuvainen." : "erisuuri kuin 0, on "
                + "vektorijono vapaa.";
        System.out.println("Matriisin determinantti on " + detB + ". "
                + "Koska vektorijonosta muodostetun matriisin determinantti on "
                + textB);
        System.out.println("Osoittajassa numeroita " + detB.getNumerator().toString().length());
        System.out.println("Nimittäjässä numeroita " + detB.getDenominator().toString().length());
        System.out.println("Determinantin laskemiseen kului aikaa " + Duration.between(start, end).toMillis()/1000.00 + " sekuntia.");

        

    }

    public int one() {
        return 1;
    }
}
