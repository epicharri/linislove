/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linislove.mylittlemath;

import java.math.BigInteger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author harrikah
 */
public class RationalOperationsTest {
    
    public RationalOperationsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void absGivesAbs(){
        Rational a = new Rational(-6, -14);
        Rational b = new Rational(13,-37);
        Rational zero = new Rational(0, -111789);
        Rational absA = a.abs();
        Rational absB = b.abs();
        Rational absZero = zero.abs();
        Rational expectedA = new Rational(3, 7);
        Rational expectedB = new Rational(13, 37);
        Rational expectedZero = Rational.ZERO;
        assertTrue(absA.equals(expectedA));
        assertTrue(absB.equals(expectedB));
        assertTrue(absZero.equals(expectedZero));
    }
    
    @Test
    public void equalNumbersAreEqual(){
        Rational a = new Rational(-222222, -555555);
        Rational b = new Rational(2, 5);
        assertTrue(a.isEqualTo(b));
    }
    
    @Test
    public void greaterOrEqualIsGreaterOrEqual() {
        Rational greaterOrEqualNumber = new Rational 
            (BigInteger.valueOf(1), -10);
        Rational equalNumber = new Rational (-3, new BigInteger("21"));
        Rational lessThanTheGreaterOne = new Rational(-1, 5);
        assertTrue(greaterOrEqualNumber.greaterOrEqual(equalNumber));
        assertTrue(greaterOrEqualNumber.greaterOrEqual(lessThanTheGreaterOne));
    }
    
    @Test
    public void lessOrEqualIsLessOrEqual() {
        Rational lessOrEqualNumber = new Rational 
            (BigInteger.valueOf(1), -5);
        Rational equalNumber = new Rational (-3, new BigInteger("30"));
        Rational greaterThanTheGreaterOne = new Rational(-1, 10);
        assertTrue(lessOrEqualNumber.lessThanOrEqual(equalNumber));
        assertTrue(lessOrEqualNumber.lessThanOrEqual(greaterThanTheGreaterOne));
    }    
    
    @Test
    public void multiplyGivesRightAnswer(){
        Rational a = new Rational(new BigInteger("1257"), 2);
        Rational b = new Rational(4, 1257);
        assertTrue(a.multiply(b).equals(new Rational(2)));
        assertTrue(a.multiply(Rational.ZERO).equals(Rational.ZERO));
    }
    
    @Test
    public void givesRightReciprocal() {
        Rational a = new Rational(6, 34);
        Rational recipA = new Rational(17, 3);
        assertTrue(a.reciprocal().isEqualTo(recipA));
    }

    @Test(expected = NumberFormatException.class)
    public void givesExceptionIfTryingToGetReciprocalOfZero() {
        Rational notAnumber = Rational.ZERO.reciprocal();
    }    

    @Test
    public void minusOperationGivesRightAnswer(){
        Rational a = new Rational(1,3);
        Rational b = new Rational(2,5);
        Rational result = new Rational(-1, 15);
        assertEquals(result, a.minus(b));
    }
    
    @Test
    public void divideWorks(){
        Rational a = new Rational(1,3);
        Rational b = new Rational(-2,5);
        Rational result = new Rational(-5, 6);        
        assertEquals(result, a.divide(b));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void divideThrowsExceptionIfTryingToDivideByZero(){
        Rational a = new Rational(2,3);
        Rational notAnumber = a.divide(Rational.ZERO);
    }
    
    @Test
    public void signumGivesRightSign(){
        Rational a = new Rational(1,2);
        Rational b = new Rational(2,-3);
        Rational c = Rational.ZERO;
        assertEquals(1, a.signum());
        assertEquals(-1, b.signum());
        assertEquals(0, c.signum());
        assertEquals(-1, a.opposite().signum());
    }
    
}
