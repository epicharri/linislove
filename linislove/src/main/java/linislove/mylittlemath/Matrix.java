/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linislove.mylittlemath;

/**
 *
 * @author harrikah
 */
public class Matrix {
    
    private Rational[][] matrix;
    
    public Matrix(int m, int n){
        this.matrix = new Rational[m][n];
    }
    
    public Matrix(int m, int n, String vectors){
        
    }
    
    private String[] vectors(String v){
        v = v.replaceAll("//s","");
        v = ")," + v + "(,";
        String[] vectorArray = v.split("),(");
        return vectorArray;
    }
    
    
}
