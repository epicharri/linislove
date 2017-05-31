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
    
    public Matrix(Rational[][] A, int m, int n, int sizeOfLongest){
        this.vectorQueue = "";
        sizeOfLongestRationalNumber = sizeOfLongest;
        this.m = m;
        this.n = n;
        this.matrix = A;
    }
    
    public Matrix(int m, int n){
        this.vectorQueue = "";
        this.sizeOfLongestRationalNumber = 0;
        this.m = m;
        this.n = n;
        this.matrix = new Rational[n][m];
    }

    public int getLongest(){
        return this.sizeOfLongestRationalNumber;
    }
    
    // i on rivit ja j on sarakkeet, vakiintuneen käytännön mukaisesti
    public void setNumber(Rational number, int i, int j){
        if (i + 1 > this.m || j + 1 > this.n) {
            throw new RuntimeException("Matriisissa ei ole kohtaa jonka "
                    + "i -indeksi on " + i + " ja j -indeksi on " + j + 
                    "Matriisissa on " + m + "riviä ja " + n + "saraketta");
        }
        this.matrix[j][i] = number;
        int sizeOfNumber = number.toString().length();
        if (sizeOfLongestRationalNumber < sizeOfNumber){
            sizeOfLongestRationalNumber = sizeOfNumber;
        }
    }
    
    public Rational getNumber(int i, int j){
        if (i < this.m && j < this.n && i >= 0 && j >= 0){
            return this.matrix[j][i];
        } else throw new RuntimeException("Matriisissa ei ole indeksiä "
                + "(i,j) = " + "(" + i + "," + j + ")");
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
        this.matrix = new Rational[n][m];
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
        //return this.vectorQueue.isEmpty() ? myMatrix : "Matriisi vektorijonosta " + this.vectorQueue + ":" + "\n\n" + myMatrix;
        return myMatrix;
    }
}
