/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linislove.logic;

import java.util.InputMismatchException;
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

    String mIsNandNotFree = "Vektorijonosta muodostetun matriisin "
            + "determinantti on 0.\n"
            + "Koska determinantti on 0, on vektorijono lineaarisesti "
            + "riippuvainen eli sidottu. Jono ei myöskään ole vektoriavaruuden "
            + "R^2 kanta, koska vain vapaa jono jossa on "
            + "2 vektoria virittää avaruuden R^2.";

    String mIsNandFree = "Vektorijonosta muodostetun matriisin "
            + "determinantti on 1.\n"
            + "Koska determinantti on nollasta eriävä, on vektorijono "
            + "lineaarisesti riippumaton eli vapaa. \n"
            + "Koska vektorijono on vapaa ja siinä olevien vektorien määrä "
            + "on sama kuin avaruuden R^2 dimensio, "
            + "jono muodostaa avaruuden R^2 kannan ja "
            + "virittää avaruuden R^2. \n"
            + "Antamillasi vektoreilla siis pääsee ihan joka paikkaan kyseisessä avaruudessa :)";

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

    @Test
    public void solveLinearDependencyGivesRightAnswerWhenMisN() throws Exception {
        String free = "(1,0),(0,1)";
        String notFree = "(1,1),(1,1)";
        String isFree = Solution.solveLinearDependency(free);
        String isNotFree = Solution.solveLinearDependency(notFree);
        assertEquals(isFree, this.mIsNandFree);
        assertEquals(isNotFree, this.mIsNandNotFree);
    }

    @Test
    public void solveLinearDepIfNoVectors() throws Exception {
        String noVectors = "()";
        String answer = "Vektorijono on vapaa koska se on tyhjä. On sovittu, että tyhjä vektorijono on "
                + "lineaarisesti riippumaton.";
        assertEquals(Solution.solveLinearDependency(noVectors), answer);
    }

    @Test
    public void solveLinearDepIfMoreVectorsThanDimension() throws Exception {
        String answer = "Vektorijono on lineaarisesti riippuvainen eli "
                + "sidottu, koska vektoreiden määrä on suurempi kuin "
                + "vektoriavaruuden dimensio.";
        assertEquals(Solution.solveLinearDependency("(1,0),(0,1),(3,7)"), answer);
    }

    @Test
    public void solveLinearDepIfLessVectorsThanDimensionAndExpectDependent() throws Exception {
        String notFree = "Vektorijono on sidottu.\nVektoreita on "
                + "vähemmän kuin vektoriavaruuden dimensio. Tutkitaan siis "
                + "vapautta seuraavasti. Olkoon A syöttämästäsi vektorijonosta muodostettu "
                + "matriisi siten että vektorien komponentit muodostavat "
                + "matriisin sarakkeet. Olkoon m matriisin rivien määrä (ja siis komponenttien määrä) ja "
                + "n matriisin sarakkeiden määrä (ja siis vektoreiden määrä). Jos m > n niin A on sidottu "
                + "jos ja vain jos det(A^T * A) = 0. Syöttämästäsi vektorijonosta näin muodostetun matriisin "
                + "determinantti on 0, joten syöttämäsi vektorijono on lineaarisesti riippuvainen eli sidottu.";
        String notFreeSet = "(1,0,0),(2,0,0)";
        assertEquals(notFree, Solution.solveLinearDependency(notFreeSet));
    }

    @Test
    public void solveLinearDepIfLessVectorsThanDimensionAndExpectFree() throws Exception {
        String isFree = "Vektorijono on vapaa.\n"
                    + "Vektoreita on vähemmän kuin vektoriavaruuden dimensio. Tutkitaan siis vapautta seuraavasti. "
                    + "Olkoon A vektorijonosta muodostettu matriisi siten että vektorien komponentit muodostavat "
                    + "matriisin sarakkeet. Olkoon m matriisin rivien määrä (ja siis komponenttien määrä) ja "
                    + "n matriisin sarakkeiden määrä (ja siis vektoreiden määrä). Jos m > n niin A on vapaa "
                    + "jos ja vain jos det(A^T * A) != 0. Syöttämästäsi vektorijonosta näin muodostetun matriisin determinantti on "
                    + "4.\n"
                    + "Koska determinantti on nollasta eriävä, on syöttämäsi vektorijono lineaarisesti riippumaton eli vapaa.";
        String freeSet = "(1,0,0),(0,2,0)";
        assertEquals(isFree, Solution.solveLinearDependency(freeSet));
    }
    
    @Test(expected=NumberFormatException.class)
    public void solveLinearDependencyThrowsNumberFormatException() throws Exception {
        String haveToThrowException = Solution.solveLinearDependency("(0/1,1/0),(1,0)");
    }
    
    @Test(expected=NumberFormatException.class)
    public void solveLinearDependencyThrowsInputMismatchException() throws Exception {
        String haveToThrowException = Solution.solveLinearDependency(",y),(z,y)");
        String thisTooHaveToThrow = Solution.solveLinearDependency("This is not a vector set");
    }
    
}
