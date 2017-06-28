/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linislove.mylittlemath;

import java.util.InputMismatchException;
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
public class LinearSystemTest {
    
    public LinearSystemTest() {
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
    public void toStringWorks() {
        String system = "x1+x2=(1/3);-x1-(1/2)x2=(2/3);";
        String expected = system.replaceAll(";",";\n");
        LinearSystem linearSystem = new LinearSystem(system);
        assertEquals(expected, linearSystem.toString());
    }
    
    @Test (expected=InputMismatchException.class)
    public void constructorGivesInputMismatchExceptionIfNotQuadratic(){
        String system = "x1 + x2 = 3;";
        LinearSystem linearSystem = new LinearSystem(system);
    }
    
    @Test (expected=InputMismatchException.class)
    public void constructorGivesIMExpIfNoIndex(){
        String system = "x1 + x2 = 1;x1 + x = 1;";
        LinearSystem linearSystem = new LinearSystem(system);
    }
}
