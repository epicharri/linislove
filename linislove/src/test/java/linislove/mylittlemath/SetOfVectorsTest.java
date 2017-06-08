/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linislove.mylittlemath;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author harrikah
 */
public class SetOfVectorsTest {

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

    @Test(expected = RuntimeException.class)
    public void constructorGivesExceptionIfWrongInput() {
        String vectorQueue = "(1,1),(1,2,3)";
        SetOfVectors set = new SetOfVectors(vectorQueue);
    }

    @Test
    public void constructorWorksOkWithDecentInput() {
        String vectorQueue = "(1,1),(0,0)";
        SetOfVectors set = new SetOfVectors(vectorQueue);
        Matrix matrix = new Matrix(set);
        Rational one = new Rational(1);
        Rational zero = Rational.ZERO;
        assertEquals(2, matrix.getM());
        assertEquals(2, matrix.getN());
        assertEquals(one, matrix.getNumber(0, 0));
        assertEquals(one, matrix.getNumber(1, 0));
        assertEquals(zero, matrix.getNumber(0, 1));
        assertEquals(zero, matrix.getNumber(1, 1));
    }
}
