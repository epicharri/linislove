package linislove.mylittlemath;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.stream.Stream;

/**
 * Matriisiluokka. Ylläpitää tietoa matriisiin kuuluvista rationaaliluvuista.
 *
 */
public class Matrix {

    private Rational[][] matrix;
    //private final String originalSetOrLinearSystem;
    //private int sizeOfLongestRationalNumber;
    //private int m;
    //private int n;

    /**
     * Konstruktori, luo Matrix -olion annetusta vektorijonosta.
     *
     * @param v SetOfVectors -luokan olio josta luodaan matriisi
     */
    public Matrix(SetOfVectors v) {
        //this.originalSetOrLinearSystem = v.getVectorQueue();
        //this.sizeOfLongestRationalNumber = v.getLongest();
        this.matrix = v.getMatrixArray();
        //this.m = matrix.length;
        //this.n = matrix[0].length;
    }

    public Matrix(LinearSystem l) {
        //int rows = l.getA().length;
        //int cols = l.getA()[0].length;
        //this.originalSetOrLinearSystem = l.getSystem();
        //this.sizeOfLongestRationalNumber = l.getSizeOfLongestRationalNumber();
        this.matrix = l.getA();
        //this.m = l.getA().length;
        //this.n = l.getA()[0].length;
    }

    public Matrix(Rational[][] a) {
        //this.originalSetOrLinearSystem = "";
        //sizeOfLongestRationalNumber = sizeOfLongest;
        //this.m = m;
        //this.n = n;
        this.matrix = Count.createCopy(a);
    }
    
    public Matrix(int m, int n) {
        //this.originalSetOrLinearSystem = "";
        //this.sizeOfLongestRationalNumber = 0;
        //this.m = m;
        //this.n = n;
        this.matrix = new Rational[m][n];
    }

    
    
    /*public int getLongest() {
        return this.sizeOfLongestRationalNumber;
    }*/

    // i on rivit ja j on sarakkeet, vakiintuneen käytännön mukaisesti
    public void setNumber(Rational number, int i, int j) {
        if (i + 1 > getM() || j + 1 > getN()) {
            throw new RuntimeException("Matriisissa ei ole kohtaa jonka "
                    + "i -indeksi on " + i + " ja j -indeksi on " + j
                    + "Matriisissa on " + getM() + "riviä ja " + getN() + "saraketta");
        }
        this.matrix[i][j] = number;
        /*
        int sizeOfNumber = number.toString().length();
        if (sizeOfLongestRationalNumber < sizeOfNumber) {
            sizeOfLongestRationalNumber = sizeOfNumber;
        }
        */
    }

    public Rational getNumber(int i, int j) {
        if (i < getM() && j < getN() && i >= 0 && j >= 0) {
            return this.matrix[i][j];
        } else {
            throw new RuntimeException("Matriisissa ei ole indeksiä "
                    + "(i,j) = " + "(" + i + "," + j + ")");
        }
    }

    public Rational[][] getMatrixArray() {
        return this.matrix;
    }

    public int getN() {
        return this.matrix[0].length;
    }

    public int getM() {
        return this.matrix.length;
    }

    @Override
    public String toString() {
        String str = "";
        int m = getM();
        int n = getN();
        int longest = longestNumber();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                str += numberWithSpaces(i, j, longest);
            }
            str += "\n";
        }
        return str;
    }

    private String numberWithSpaces(int i, int j, int longest){
        String number = matrix[i][j].toString();
        int spacesNeeded = longest - number.length() + 2;
        for (int s = 0; s < spacesNeeded; s++) {
            number+= " ";
        }
        return number;
    }
    private int longestNumber(){
        int m = getM();
        int n = getN();
        int longest = 0;
        for (int i = 0; i < m; i ++){
            for (int j = 0; j < n; j++) {
                int lengthOfNumber = this.matrix[i][j].toString().length();
                longest = lengthOfNumber > longest ? lengthOfNumber : longest;
            }
        }
        return longest;
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
