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
    public void createstRightMatrix(){
        String vectorQueue = "(1,2/3),(2,4/3)";
        Rational[][] expectedArray = new Rational[2][2];
        expectedArray[0][0] = new Rational(1);
        expectedArray[1][0] = new Rational(2,3);
        expectedArray[0][1] = new Rational(2);
        expectedArray[1][1] = new Rational(4,3);
        Matrix a = new Matrix(vectorQueue);
        Rational[][] vectorArrayOfMatrixA = a.getMatrixArray();
        for (int i = 0; i < a.getM(); i++){
            for (int j = 0; j < a.getN(); j++){
                assertEquals(expectedArray[j][i], vectorArrayOfMatrixA[j][i]);
            }
        }
        assertEquals(2, a.getM());
        assertEquals(2, a.getN());
    }
}
