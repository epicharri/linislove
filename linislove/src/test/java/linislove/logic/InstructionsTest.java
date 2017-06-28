/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linislove.logic;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import linislove.logic.Instructions;

/**
 *
 * @author harrikah
 */
public class InstructionsTest {
    
    public InstructionsTest() {
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
    public void givesRightInstructions(){
        String expected = "Anna vektorijono muodossa "
            + "(1,0,7),(1,2,3),(4,5,6) tai esimerkiksi (1/2,2/5,3/7,4/9,5/13),(3,5,7,9,11) "
            + "ja paina vektorijonon vapaus -nappia.\n"
            + "Rationaalilukuja syötettäessä ei käytetä sulkuja luvun ympärille. Kirjoita siis rationaaliluku -1/3 eikä (-1/3). Desimaalilukuja syötettäessä käytä desimaalipilkun sijasta pistettä (3.14 eikä 3,14)"
            + "\n\n"
            + "Anna yhtälöryhmä muodossa.\n"
            + "x1 + -1/3 x2 - 1.75 x3 = 1;\n"
            + "-13/17 x1 - -19/23 x2 + x3 = 19;\n"
            + "x1+x2+x3 = 31;\n"
            + "ja paina yhtälöryhmän ratkaisu -nappia.\n"
            + ""
            + "Arvo yhtälöryhmä asettaa yläpuolella olevaan kenttään satunnaisen yhtälöryhmän. Voit ratkaista sen painamalla yhtälönratkaisun nappia.\n";
        assertEquals(expected, Instructions.INSTRUCTIONS);
        
    }
}
