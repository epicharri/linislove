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
        
        String v = "(1,2,3/152),(2/3,4/37,5),(-5,17/31,-9)";
        Matrix matrix = new Matrix(v);
        System.out.println("");
        System.out.println(matrix);
    }
    
    public int one(){
        return 1;
    }
}
