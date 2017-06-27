/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linislove.logic;

import java.util.InputMismatchException;
import linislove.mylittlemath.Count;
import linislove.mylittlemath.LinearSystem;
import linislove.mylittlemath.LinearSystemSolver;
import linislove.mylittlemath.Matrix;
import linislove.mylittlemath.Rational;
import linislove.mylittlemath.SetOfVectors;

/**
 *
 * @author harrikah
 */
public class Solution {

    public static String solveLinearSystem(String system) throws Exception {
        LinearSystem linearSystem = new LinearSystem(system);
        Rational[] x = LinearSystemSolver.solveSystem(linearSystem);
        return Count.giveSolutions(x);
    }
    
    public static String solveLinearDependency(String vectorSet) throws Exception {
        try {
            Matrix matrixA = new Matrix(new SetOfVectors(vectorSet));
            // Lineaarialgebraa: m on rivien määrä ja n on sarakkeiden määrä.
            // Matriisi on luotu vektoreista ja selvitetään vapautta, joten
            // m on siten komponenttien määrä ja n on vektoreiden määrä.
            int m = matrixA.getM();
            int n = matrixA.getN();            
            if (m < n) return moreVectorsThanDimension();
            if (m > n) return lessVectorsThanDimension(matrixA, m, n);    
            return numberOfVectorsAndDimensionAreEqual(matrixA);
        } catch (InputMismatchException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }            
    } 
    
    private static String numberOfVectorsAndDimensionAreEqual(Matrix matrixA) {
        Rational det = Count.det(matrixA);
        String freedom = "Vektorijonosta muodostetun matriisin\n "
                + "determinantti on " + det + ".\n";
        if (det.equals(Rational.ZERO)) {
            freedom += "Koska determinantti on 0, on vektorijono lineaarisesti "
                    + "riippuvainen eli sidottu.";
        } else {
            freedom += "Koska determinantti on nollasta eriävä, on vektorijono "
                    + "lineaarisesti riippumaton eli vapaa.";
                }
        return freedom;
    }
    
    private static String moreVectorsThanDimension(){
        return "Vektorijono on lineaarisesti riippuvainen eli "
                    + "sidottu, koska vektoreiden määrä on suurempi kuin "
                    + "vektoriavaruuden dimensio.";
    }    
    
    private static String lessVectorsThanDimension(Matrix matrixA, int m, int n) {
        // Jos m > n niin matriisi A on vapaa joss det(A^T*A) != 0. 
        Rational detAtA = Count.det(Count.multiply(Count.transpose(matrixA), matrixA));                
        if (detAtA.isZero()) return "Vektorijono on sidottu.\nVektoreita on "
                + "vähemmän kuin vektoriavaruuden dimensio. Tutkitaan siis "
                + "vapautta seuraavasti. Olkoon A syöttämästäsi vektorijonosta muodostettu "
                + "matriisi siten että vektorien komponentit muodostavat "
                + "matriisin sarakkeet. Olkoon m matriisin rivien määrä (ja siis komponenttien määrä) ja "
                + "n matriisin sarakkeiden määrä (ja siis vektoreiden määrä). Jos m > n niin A on sidottu "
                + "jos ja vain jos det(A^T * A) = 0. Syöttämästäsi vektorijonosta näin muodostetun matriisin "
                + "determinantti on 0, joten syöttämäsi vektorijono on lineaarisesti riippuvainen eli sidottu.";
        else return "Vektorijono on vapaa.\n"
                + "Vektoreita on vähemmän kuin vektoriavaruuden dimensio. Tutkitaan siis vapautta seuraavasti. "
                + "Olkoon A vektorijonosta muodostettu matriisi siten että vektorien komponentit muodostavat "
                + "matriisin sarakkeet. Olkoon m matriisin rivien määrä (ja siis komponenttien määrä) ja "
                + "n matriisin sarakkeiden määrä (ja siis vektoreiden määrä). Jos m > n niin A on vapaa "
                + "jos ja vain jos det(A^T * A) != 0. Syöttämästäsi vektorijonosta näin muodostetun matriisin determinantti on "
                + detAtA + ".\n"
                + "Koska determinantti on nollasta eriävä, on syöttämäsi vektorijono lineaarisesti riippumaton eli vapaa.";        
    }
}
