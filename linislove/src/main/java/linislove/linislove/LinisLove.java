/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linislove.linislove;

import java.util.*;
import linislove.mylittlemath.Count;
import linislove.mylittlemath.Matrix;
import linislove.mylittlemath.Rational;

/**
 *
 * @author harrikah
 */
public class LinisLove {
    public static void main(String[] args){
        
    
    
        Rational a = new Rational(55,-200);
        Rational b = new Rational(-100, -45);
        Rational zero = new Rational(0,20);
        Rational wholeNumber = new Rational(4);
        System.out.println("a = " + a.original() + " = " + a);
        System.out.println("b = " + b.original() + " = " + b);
        System.out.println("nolla = " + zero.original() + " = " + zero);
        System.out.println("a * b = " + Count.product(a, b));
        System.out.println("a * " + wholeNumber.original() + " = " + Count.product(a, wholeNumber));
        System.out.println("a + b = " + Count.sum(a, b));
        System.out.println("a + " + wholeNumber.original() + " = " + Count.sum(a, wholeNumber));
        System.out.println("a - b = " + Count.difference(a,b));
        
        String v = "(1,0,0),(0,1,0),(0,0,1)";
        Matrix matrix = new Matrix(v);
        System.out.println("");
        System.out.println(matrix);
        Rational detMatrix = Count.determinant(matrix.getMatrixArray(), 
                matrix.getN(), matrix.getM());
        String text = (detMatrix.equals(new Rational(0))) ? "0, on vektorijono "
                + "lineaarisesti riippuvainen." : "erisuuri kuin 0, on "
                + "vektorijono vapaa.";
        System.out.println("Matriisin determinantti on " + detMatrix + ". " +
                "Koska vektorijonosta muodostetun matriisin determinantti on " +
                text);
        
        System.out.println("--------------");
        String vv = "(10,-11/17,7/113),(17/33,1/3,2/5),(0,0,0)";
        Matrix matrixv = new Matrix(vv);
        System.out.println("");
        System.out.println(matrixv);
        Rational detMatrixv = Count.determinant(matrixv.getMatrixArray(), 
                matrixv.getN(), matrixv.getM());
        String textv = (detMatrixv.equals(new Rational(0))) ? "0, on vektorijono "
                + "lineaarisesti riippuvainen." : "erisuuri kuin 0, on "
                + "vektorijono vapaa.";
        System.out.println("Matriisin determinantti on " + detMatrixv + ". " +
                "Koska vektorijonosta muodostetun matriisin determinantti on " +
                textv);        
    
        System.out.println("--------------");

        
        String w = "(1,1,1),(-1/2,-2,-2/3),(0,1,1)";
        Matrix B = new Matrix(w);
        System.out.println("");
        System.out.println(B);
        System.out.println("");
        Rational detB = Count.determinant(B.getMatrixArray(), 
                B.getN(), B.getM());
        String textB = (B.equals(new Rational(0))) ? "0, on vektorijono "
                + "lineaarisesti riippuvainen." : "erisuuri kuin 0, on "
                + "vektorijono vapaa.";
        System.out.println("Matriisin determinantti on " + detB + ". " +
                "Koska vektorijonosta muodostetun matriisin determinantti on " +
                textB);
        
      
    }
    
    public int one(){
        return 1;
    }
}
