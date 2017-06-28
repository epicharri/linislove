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
public class MatrixTest {

    public MatrixTest() {
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
    public void createsRightMatrix() {
        String vectorQueue = "(1,2/3),(2,4/3)";
        Rational[][] expectedArray = new Rational[2][2];
        expectedArray[0][0] = new Rational(1);
        expectedArray[1][0] = new Rational(2, 3);
        expectedArray[0][1] = new Rational(2);
        expectedArray[1][1] = new Rational(4, 3);
        Matrix a = new Matrix(new SetOfVectors(vectorQueue));
        Rational[][] vectorArrayOfMatrixA = a.getMatrixArray();
        for (int i = 0; i < a.getM(); i++) {
            for (int j = 0; j < a.getN(); j++) {
                assertEquals(expectedArray[i][j], vectorArrayOfMatrixA[i][j]);
            }
        }
        assertEquals(2, a.getM());
        assertEquals(2, a.getN());
    }

    @Test
    public void givesRightString() {
        Matrix matrix = new Matrix(new SetOfVectors("(1/2,1/2),"
                + "(0,2/2)"));
        String expected = "(1/2)  0      \n(1/2)  1      \n";
        assertEquals(expected, matrix.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void geTNumberThrowsException() {
        Matrix matrix = new Matrix(new SetOfVectors("(1/2,1/2),"
                + "(0,2/2)"));
        Rational c = matrix.getNumber(-1, -1);
        Rational d = matrix.getNumber(2, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void seTNumberThrowsException() {
        Matrix matrix = new Matrix(new SetOfVectors("(1/2,1/2),"
                + "(0,2/2)"));
        matrix.setNumber(new Rational(20), -1, -1);
        matrix.setNumber(new Rational(20), 2, 2);
    }

    @Test
    public void getNumberGivesRightNumber() {
        Matrix matrix = new Matrix(new SetOfVectors("(1/2,1/2),"
                + "(0,2/2)"));
        Rational c = matrix.getNumber(0, 0);
        assertEquals(new Rational(1, 2), c);
    }

    @Test
    public void setNumberWorks() {
        Matrix matrix = new Matrix(new SetOfVectors("(1/2,1/2),"
                + "(0,2/2)"));
        matrix.setNumber(new Rational(new BigInteger("10")), 0, 0);
        Matrix rightMatrix = new Matrix(new SetOfVectors("(10,1/2),(0,2/2)"));
        assertEquals(matrix, rightMatrix);
    }
}
