/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linislove.logic;

import linislove.mylittlemath.Check;
import linislove.mylittlemath.LinearSystem;
import linislove.mylittlemath.LinearSystemSolver;
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
public class RandomLinearSystemTest {
    
    public RandomLinearSystemTest() {
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
    public void randomLinearSystemCreateGivesSystemThatIsPossibleToSolve(){
        LinearSystem system = RandomLinearSystem.create(10,10,10);
        Rational[] answers = LinearSystemSolver.solveSystem(system);
        
        assertEquals("Yhtälöryhmän ratkaisu on tarkistettu ja on "
                + "täsmälleen oikein." ,Check.checkAnswer(system, answers));
    }
}
