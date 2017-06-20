/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linislove.logic;

import linislove.mylittlemath.Count;
import linislove.mylittlemath.LinearSystem;
import linislove.mylittlemath.LinearSystemSolver;
import linislove.mylittlemath.Matrix;
import linislove.mylittlemath.Rational;

/**
 *
 * @author harrikah
 */
public class Solution {

    public static String solveLinearSystem(String system) {
        LinearSystem linearSystem = new LinearSystem(system);
        Rational[] x = LinearSystemSolver.solveSystem(linearSystem);
        return Count.giveSolutions(x);
    }
}
