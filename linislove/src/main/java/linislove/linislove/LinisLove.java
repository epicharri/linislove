/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linislove.linislove;

import java.util.*;
import linislove.mylittlemath.Count;
import linislove.mylittlemath.Rational;

/**
 *
 * @author harrikah
 */
public class LinisLove {
    public static void main(String[] args){
        
    
    
        Rational a = new Rational(55,200);
        Rational b = new Rational(100, 45);
        Rational nolla = new Rational(0,20);
        System.out.println("a = " + a + " = " + a.canceled());
        System.out.println("b = " + b + " = " + b.canceled());
        System.out.println("nolla = " + nolla + " = " + nolla.canceled());
        System.out.println("a * b = " + Count.Product(a, b));
    
    }
    
    public int one(){
        return 1;
    }
}
