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
        /*
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

        String v = "(1,2,3),(5,7,11),(13,17,19),(23,29,31)";
        
        Matrix matrixE = new Matrix(v);
        System.out.println(matrixE);
        Matrix matrixTr = Count.transpose(matrixE);
        System.out.println("");
        System.out.println(matrixTr);
        Matrix matrix = Count.multiply(matrixTr, matrixE);
        
        Rational detMatrix = Count.det(matrix);
        String text = (detMatrix.equals(new Rational(0))) ? "0, on vektorijono "
                + "lineaarisesti riippuvainen." : "erisuuri kuin 0, on "
                + "vektorijono vapaa.";
        System.out.println("Matriisin determinantti on " + detMatrix + ". "
                + "Koska vektorijonosta muodostetun matriisin determinantti on "
                + text);
        Matrix matrixT = Count.transpose(matrix);
        System.out.println("Transpoosi: \n" + matrixT);
        System.out.println("ja sen determinantti on " + Count.det(matrixT));

         */

        System.out.println("--------------");
        Matrix group = new Matrix("(2,0,-1),(1,5,1),(2,3,0)");
        //Matrix group = new Matrix("(-3, -3, 5),(2,2,-3),(-7,-6,10)");
        Matrix bb = new Matrix("(1),(2),(3)");
        Instant start4 = Instant.now();
        Matrix solve = Count.solveByCramerRule(group, bb);
        Instant end4 = Instant.now();
        System.out.println("Aikaa ratkaisuun kului " + 1.0 * Duration.between(start4, end4).toMillis() / 1000 + "sekuntia.");

        System.out.println("Yhtälöryhmästä muodostettu matriisi A: ");
        System.out.println(group);
        System.out.println("Kaavan Ax=b matriisi b: ");
        System.out.println(bb);
        System.out.println("Yhtälöryhmän ratkaisu: ");
        System.out.println("x_1 = " + solve.getNumber(0, 0));
        System.out.println("x_2 = " + solve.getNumber(1, 0));
        System.out.println("x_3 = " + solve.getNumber(2, 0));

        System.out.println("---");
        Matrix a = new Matrix("(1,2,3),(4/5,5/6,7/8),(1,1,1)");
        Matrix b = new Matrix("(3,5,7),(11,13,17),(19,23,31)");
        Matrix ab = Count.multiply(a, b);
        System.out.println(ab);
        /*
        Matrix group1 = new Matrix("(-3, -3, 5),(2,2,-3),(-7,-6,10)");
        Matrix bb1 = new Matrix("(1),(2),(3)");
        Instant start41 = Instant.now();        
        Matrix solvex = Count.solveByCramerRule(group1, bb1);
        Instant end41 = Instant.now();
        System.out.println("Aikaa ratkaisuun kului "+ 1.0 * Duration.between(start41, end41).toMillis()/1000 + "sekuntia.");
        
        System.out.println("Yhtälöryhmästä muodostettu matriisi A: ");
        System.out.println(group1);
        System.out.println("Kaavan Ax=b matriisi b: ");
        System.out.println(bb1);
        System.out.println("Yhtälöryhmän ratkaisu: ");
        System.out.println("x_1 = " + solvex.getNumber(0, 0));
        System.out.println("x_2 = " + solvex.getNumber(1, 0));
        System.out.println("x_3 = " + solvex.getNumber(2, 0));
         */
 /*
        System.out.println("---------------");
        String vv = "(0.33333333333333333333,1/5),(5,3)";
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
        System.out.println("Matriisin transpoosi on: ");
        Matrix transposeMatrix = Count.transpose(matrixv);
        System.out.println(transposeMatrix);
        System.out.println("Transpoosimatriisin determinantti on:");
        System.out.println(Count.det(transposeMatrix));
        

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

        start = Instant.now();
        Matrix Bpoweredto2 = Count.multiply(B, B);
        end = Instant.now();
        System.out.println("Matriisin korottamiseen toiseen potenssiin kului aikaa " + Duration.between(start, end).toMillis()/1000.00 + " sekuntia.");        

        Instant start2 = Instant.now();
        Rational detC = Count.det(Bpoweredto2);
        Instant end2 = Instant.now();

        System.out.println("Sen korotetun determinantti on " + detC);
        
        System.out.println("Determinantin laskemiseen kului aikaa " + Duration.between(start2, end2).toMillis()/1000.00 + " sekuntia.");
        System.out.println("Osoittajassa numeroita " + detC.getNumerator().toString().length());
        System.out.println("Nimittäjässä numeroita " + detC.getDenominator().toString().length());
        
         */
    }

    public int one() {
        return 1;
    }
}
