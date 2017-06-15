/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linislove.logic;

import linislove.mylittlemath.Count;
import linislove.mylittlemath.LinearSystem;
import linislove.mylittlemath.Matrix;

/**
 *
 * @author harrikah
 */
public class Solution {

    public static String solveLinearSystem(String system) {
        LinearSystem linearSystem = new LinearSystem(system);
        Matrix A = new Matrix(linearSystem);
        Matrix b = new Matrix(linearSystem.getB(), linearSystem.getB().length, 1,
                linearSystem.getSizeOfLongestRationalNumber());
        Matrix x = Count.solveByCramerRule(A, b);
        return Count.giveSolutions(x);
    }
}
