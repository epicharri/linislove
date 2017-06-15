/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linislove.logic;

import linislove.mylittlemath.Count;
import linislove.mylittlemath.LinearSystem;
import linislove.mylittlemath.Matrix;
import linislove.mylittlemath.Rational;
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
public class SolutionTest {
    
    public SolutionTest() {
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
    public void solveLinearSystemGivesRightAnswerString(){
        String system = "x1-x2-(-1/7)x3 =1;2/3x2+x3 = (5/7);x4=7;x1+x2+x3+x4=17;";
        String expected = "x1 = (513/70)\n" +
                "x2 = (411/70)\n" +
                "x3 = (-16/5)\n" +
                "x4 = 7";
        String givenSolution = Solution.solveLinearSystem(system);
        assertEquals(expected, givenSolution);
    }
}
