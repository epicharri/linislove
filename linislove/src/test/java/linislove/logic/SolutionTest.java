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

    String vectorSet = "Lolcat ei ole vektorijono";
    String linearSystem = "Lolcat ei ole yhtälöryhmä";
    String lolcatAnswer = ". Sen sijaan lolcat on kuva, "
            + "jossa on yhdistetty kissan kuva humoristiseen tekstinpätkään. "
            + "Teksti on yleensä lolcat-kieltä eli \"lolspeakiä\", joka on muunneltua englantia. "
            + "Lolcat-kielessä tehdään tahallaan aloittelijamaisia virheitä englannin kieliopissa. "
            + "Esimerkiksi lause \"Can I have a cheeseburger?\" kirjoitetaan lolcat-kielessä "
            + "tyypillisesti \"I can has cheezburger?\"\n\n"
            + "Lähde: https://fi.wikipedia.org/wiki/Lolcat"
            + "\n"
            + "Ohjelma ei ole tarkoitettu toimimaan Wikipedian korvikkeena, joten kokeile ennemmin "
            + "syöttää esimerkiksi tällainen vektorijono:\n"
            + "(1,0,0),(0,13/17,0)\n"
            + "ja klikkaa nappia 'Vektorijonon vapaus'.\n"
            + "Huomaa, että vektorijonoa syötettäessä sulkuja saa käyttää vain yllämainitulla tavalla. Älä siis laita rationaalilukuja sulkeisiin.\n\n"
            + "Yhtälöryhmän ratkaisua voit kokeilla esimerkiksi syötteellä\n"
            + "(-3/7)x1 + (-2/-5)x2 = 7;\n12.377x1 - 100x2 = 1.11\n"
            + "ja painamalla yhtälöryhmän ratkaisu -nappia. Voit myös arpoa yhtälöryhmän arpomisnappia painamalla ja klikkaamalla ratkaise -nappia. "
            + "Kivoja hetkiä sovelluksen parissa!";

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
    public void solveLinearSystemGivesRightAnswerString() throws Exception {
        String system = "x1-x2-(-1/7)x3 =1;2/3x2+x3 = (5/7);x4=7;x1+x2+x3+x4=17;";
        String expected = "x_1 = (513/70)\n"
                + "x_2 = (411/70)\n"
                + "x_3 = (-16/5)\n"
                + "x_4 = 7";
        String givenSolution = Solution.solveLinearSystem(system);
        assertEquals(expected, givenSolution);
    }

    @Test
    public void lolcatReturnsLolcatAnswerWhenLinearSystemCalled() throws Exception {
        String input = " LolCat ";
        String answer = Solution.solveLinearSystem(input);
        assertEquals(answer, linearSystem + lolcatAnswer);
    }
    
    @Test
    public void lolcatReturnsLolcatAnswerWhenLinearDependencyCalled() throws Exception {
        String input = " Lolcat ";
        String answer = Solution.solveLinearDependency(input);
        assertEquals(answer, vectorSet + lolcatAnswer);
    }    
}
