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
import org.junit.Rule;
import org.junit.rules.ExpectedException;

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
    /*
    @Test
    public void cramerRuleWorksOk(){
        Matrix a = new Matrix("(1,0,0),(0,1,0),(0,0,1)");
        Matrix b = new Matrix("(3),(5),(7)");
        Matrix x = Count.solveByCramerRule(a, b);
        Matrix expectedX = new Matrix("(3),(5),(7)");
        assertEquals(expectedX, x);
    }
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void reciprocalForZeroDoesNotExist() {
        thrown.expect(RuntimeException.class);
        thrown.expectMessage("Nollalla ei ole käänteislukua.");
        Rational a = Count.reciprocal(Rational.ZERO);
    }

    @Test
    public void givesRigthDeterminant() {
        Matrix a = new Matrix(
                new SetOfVectors("(100,200,300),(10/7,4/7,3/7),(0,0,0)"));
        Matrix b = new Matrix(new SetOfVectors("(2,4),(0,2)"));
        assertEquals(Rational.ZERO, Count.det(a));
        assertEquals(new Rational(4), Count.det(b));
    }

    @Test
    public void givesRightReciprocal() {
        Rational a = new Rational(-13, 7);
        Rational expectedReciprocal = new Rational(-7, 13);
        assertEquals(expectedReciprocal, Count.reciprocal(a));
    }

    @Test
    public void productOfTwoRationalsGivesRightAnswer() {
        BigInteger numA = new BigInteger("9000000000");
        BigInteger denomA = new BigInteger("18000000000");
        BigInteger numB = new BigInteger("40000000000");
        BigInteger denomB = new BigInteger("160000000000");
        Rational a = new Rational(numA, denomA);
        Rational b = new Rational(numB, denomB);
        Rational ab = Count.product(a, b);
        assertEquals(BigInteger.ONE, ab.getNumerator());
        assertEquals(new BigInteger("8"), ab.getDenominator());
    }

    @Test
    public void multiplyMatrixGivesRigthAnswer() {
        Matrix a = new Matrix(new SetOfVectors("(2,0,0),(0,2,0),(0,0,2)"));
        Matrix b = new Matrix(new SetOfVectors("(3.3,5/5,7),(11,13,17),(19,23,31)"));
        Matrix ab = Count.multiply(a, b);
        Matrix expected = new Matrix(new SetOfVectors("(6.6,10/5,14),(22,26,34),(38,46,62)"));
        assertEquals(expected, ab);
    }
    
    @Test
    public void transposeGivesRightMatrix() {
        Matrix a = new Matrix(new SetOfVectors("(1,0,0),(0,2,0)"));
        Matrix t = Count.transpose(a);
        Matrix expected = new Matrix(new SetOfVectors("(1,0),(0,2),(0,0)"));
        assertEquals(expected, t);
    }
    
}
