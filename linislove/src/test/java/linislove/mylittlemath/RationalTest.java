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
public class RationalTest {

    public RationalTest() {
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
    public void simplifyWorks(){
        Rational number = new Rational(0);
        number.setNumerator(222);
        number.setDenominator(444);
        number.simplify();
        assertEquals(new Rational("1/2"), number);
    }
    
    @Test
    public void createRationalNumberZero() {
        Rational number = new Rational(0);
        String expRes = ("0");
        String res = number.toString();
        assertEquals(expRes, res);
    }

    @Test
    public void createRationalNumberOfTwoWholeNumbers() {
        Rational number = new Rational(31, 7);
        String expRes = ("31/7");
        String res = number.toString();
        assertEquals(expRes, res);
    }

    @Test
    public void createRationalNumberOfString() {
        Rational number = new Rational("210/9");
        String expRes = ("70/3");
        String res = number.toString();
        assertEquals(expRes, res);
    }

    @Test
    public void equalNumbersAreEqual() {
        Rational a = new Rational(100, 50);
        Rational b = new Rational(2222, 1111);
        assertTrue(a.toString().equals(b.toString()));
    }

    @Test
    public void originalRationalNumberIsAsParameterWasWhenCreatingNumber() {
        Rational number = new Rational("210/9");
        String expRes = ("70/3");
        String res = number.toString();
        assertEquals(expRes, res);
        assertEquals("210/9", number.original());
    }

    @Test
    public void signumGivesRightSign() {
        Rational a = new Rational("1/-1");
        Rational b = new Rational(-1, -1);
        Rational c = new Rational(1, 1);
        Rational d = new Rational(0, 1000);
        assertEquals(BigInteger.ONE.negate(), a.signumOfRational());
        assertEquals(BigInteger.ONE, b.signumOfRational());
        assertEquals(BigInteger.ONE, c.signumOfRational());
        assertEquals(BigInteger.ZERO, d.signumOfRational());
    }
}
