/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linislove.mylittlemath;

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
public class CountTest {

    public CountTest() {
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
    public void methodGCDgivesGreatestCommonDivider() {
        assertEquals(1, Count.gcd(13, 7));
        assertEquals(1, Count.gcd(1, 0));
        assertEquals(3, Count.gcd(39, 21));
    }

    @Test
    public void givesRightReciprocal() {
        Rational a = new Rational(-13, 7);
        Rational expectedReciprocal = new Rational(-7, 13);
        assertEquals(expectedReciprocal, Count.reciprocal(a));
    }

    @Test
    public void productOfTwoRationalsGivesRightAnswer() {
        for (int i = 0; i < 10; i++) {
            int numA = (int) Math.random() * 100;
            int denomA = (int) Math.random() * 100 + 1;
            int numB = (int) Math.random() * 100;
            int denomB = (int) Math.random() * 100 + 1;
            Rational a = new Rational(numA, denomA);
            Rational b = new Rational(numB, denomB);
            Rational ab = Count.product(a, b);
            int greatestCommonDivider = Count.gcd(numA * numB, denomB * denomB);
            int productNum = numA * numB / greatestCommonDivider;
            int productDenom = denomA * denomB / greatestCommonDivider;
            assertEquals(productNum, ab.getNumerator());
            assertEquals(productDenom, ab.getDenominator());
        }
    }
}
