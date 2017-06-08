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
public class SetOfVectors {

    private final String vectorQueue;
    private Rational[][] matrix;
    private int sizeOfLongestRationalNumber;

    public SetOfVectors(String v) {
        this.vectorQueue = v;
        sizeOfLongestRationalNumber = 0;
        if (!parseVectorQueueToMatrixArray(v)) {
            throw new RuntimeException("Matriisia ei voitu luoda antamastasi"
                    + "vektorijonosta, koska jono sisältää eri vektoriavaruuksien"
                    + "vektoreita.");
        }
    }

    private String[] vectors(String v) {
        v = v.replaceAll("//s", "");
        v = v.replaceAll("\\(", "");
        v = v.replaceAll("\\),", "@");
        v = v.replaceAll("\\)", "");
        String[] vector = v.split("@");
        return vector;
    }

    private boolean parseVectorQueueToMatrixArray(String v) {

        String[] vector = vectors(v);
        int n = vector.length; // columns eli sarakkeet, vektorien määrä
        int m = vector[0].split(",").length; // rows eli rivit, komponenttien määrä
        this.matrix = new Rational[n][m];
        for (int i = 0; i < m; i++) {
            String[] componentsOfVector = vector[i].split(",");
            if (componentsOfVector.length != m) {
                return false;
            }
            for (int j = 0; j < n; j++) {
                Rational number = new Rational(componentsOfVector[j]);
                if (number.toString().length() > this.sizeOfLongestRationalNumber) {
                    sizeOfLongestRationalNumber = number.toString().length();
                }
                this.matrix[i][j] = number;
            }
        }
        return true;
    }

    public Rational[][] getMatrixArray() {
        return this.matrix;
    }

    public int getLongest() {
        return this.sizeOfLongestRationalNumber;
    }

    public String getVectorQueue() {
        return this.vectorQueue;
    }

}
