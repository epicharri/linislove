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
    private final String vectorQueue;
    private int sizeOfLongestRationalNumber;
    private int m;
    private int n;

    public Matrix(String v) {
        this.vectorQueue = v;
        sizeOfLongestRationalNumber = 0;
        if (!createMatrix(v)) {
            throw new RuntimeException("Matriisia ei voitu luoda antamastasi"
                    + "vektorijonosta, koska jono sisältää eri vektoriavaruuksien"
                    + "vektoreita.");
        }
    }

    private String[] vectors(String v) {
        v = v.trim();
        v = v.replaceAll("//s", "");
        v = v.replaceAll("\\(", "");
        v = v.replaceAll("\\),", "@");
        v = v.replaceAll("\\)", "");
        String[] vectorArray = v.split("@");
        return vectorArray;
    }

    private boolean createMatrix(String v) {
        String[] vectorArray = vectors(v);
        this.m = vectorArray.length; // rows eli rivit
        this.n = vectorArray[0].split(",").length; // columns eli sarakkeet
        this.matrix = new Rational[m][n];
        for (int i = 0; i < m; i++) {
            String[] elementsOfVector = vectorArray[i].split(",");
            if (elementsOfVector.length != n) {
                return false;
            }
            for (int j = 0; j < n; j++) {
                Rational number = new Rational(elementsOfVector[j]);
                if (number.toString().length() > this.sizeOfLongestRationalNumber) {
                    sizeOfLongestRationalNumber = number.toString().length();
                }
                this.matrix[j][i] = number;
            }
        }
        return true;
    }

    public Rational[][] getMatrixArray() {
        return this.matrix;
    }

    public int getN() {
        return this.n;
    }

    public int getM() {
        return this.m;
    }

    @Override
    public String toString() {
        String myMatrix = "";
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                String number = matrix[j][i].toString();
                int spacesNeeded = this.sizeOfLongestRationalNumber
                        - number.length() + 2;
                String spaces = "";
                for (int s = 0; s < spacesNeeded; s++) {
                    spaces += " ";
                }
                myMatrix += number + spaces;
            }
            myMatrix += "\n";
        }
        return "Matriisi vektorijonosta " + this.vectorQueue + ":" + "\n\n"
                + myMatrix;
    }
}
