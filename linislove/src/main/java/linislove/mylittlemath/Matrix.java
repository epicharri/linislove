/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linislove.mylittlemath;

import java.util.Arrays;

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

    public Matrix(SetOfVectors v) {
        this.vectorQueue = v.getVectorQueue();
        this.sizeOfLongestRationalNumber = v.getLongest();
        this.matrix = v.getMatrixArray();
        this.m = matrix.length;
        this.n = matrix[0].length;
    }

    public Matrix(Rational[][] A, int m, int n, int sizeOfLongest) {
        this.vectorQueue = "";
        sizeOfLongestRationalNumber = sizeOfLongest;
        this.m = m;
        this.n = n;
        this.matrix = A;
    }

    public Matrix(int m, int n) {
        this.vectorQueue = "";
        this.sizeOfLongestRationalNumber = 0;
        this.m = m;
        this.n = n;
        this.matrix = new Rational[n][m];
    }

    public int getLongest() {
        return this.sizeOfLongestRationalNumber;
    }

    // i on rivit ja j on sarakkeet, vakiintuneen käytännön mukaisesti
    public void setNumber(Rational number, int i, int j) {
        if (i + 1 > this.m || j + 1 > this.n) {
            throw new RuntimeException("Matriisissa ei ole kohtaa jonka "
                    + "i -indeksi on " + i + " ja j -indeksi on " + j
                    + "Matriisissa on " + m + "riviä ja " + n + "saraketta");
        }
        this.matrix[i][j] = number;
        int sizeOfNumber = number.toString().length();
        if (sizeOfLongestRationalNumber < sizeOfNumber) {
            sizeOfLongestRationalNumber = sizeOfNumber;
        }
    }

    public Rational getNumber(int i, int j) {
        if (i < this.m && j < this.n && i >= 0 && j >= 0) {
            return this.matrix[j][i];
        } else {
            throw new RuntimeException("Matriisissa ei ole indeksiä "
                    + "(i,j) = " + "(" + i + "," + j + ")");
        }
    }

    private boolean createMatrixFromString(String v) {
        // String v:n oltava muodossa v = "m1n1 m1n2 m1n3;m2n1 m2n2 m2n3",
        // missä esim. m1n2 on 1. rivin (m1) 2. sarakkeessa (n2) oleva alkio.
        String[] rows = parseMatrixRowsOfString(v);
        this.m = rows.length; // rows eli rivit
        this.n = rows[0].split(",").length; // columns eli sarakkeet
        this.matrix = new Rational[n][m];
        for (int i = 0; i < m; i++) {
            String[] elementsOfRow = rows[i].split(",");
            if (elementsOfRow.length != n) {
                return false;
            }
            for (int j = 0; j < n; j++) {
                Rational number = new Rational(elementsOfRow[j]);
                if (number.toString().length() > this.sizeOfLongestRationalNumber) {
                    sizeOfLongestRationalNumber = number.toString().length();
                }
                this.matrix[j][i] = number;
            }
        }
        return true;
    }

    private String[] parseMatrixRowsOfString(String v) {
        v = v.trim();
        v = v.replaceAll("//s+", ",");
        String[] rows = v.split(";");
        // PITÄÄ SIT TESTATA ettei tule tyhjiä rivejä!!!!!
        return rows;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Arrays.deepHashCode(this.matrix);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Matrix other = (Matrix) obj;
        return Arrays.deepEquals(this.matrix, other.matrix);
    }

}
