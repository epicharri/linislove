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
public class Count {
    
    // Toistaiseksi vain rationaaliluvut käytössä
    public static Rational Product(Rational a, Rational b) {
        Rational ac = a.canceled();
        Rational bc = b.canceled();
        int upstairs = ac.getNumerator() * bc.getNumerator();
        int downstairs = ac.getDenominator() * bc.getDenominator();
        Rational multiplied = new Rational(upstairs, downstairs);
        return multiplied.canceled();
    }
    
}
