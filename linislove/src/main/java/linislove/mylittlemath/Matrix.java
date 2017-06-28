package linislove.mylittlemath;

import java.util.Arrays;

/**
 * Matriisiluokka. Ylläpitää tietoa matriisiin kuuluvista rationaaliluvuista.
 *
 */
public class Matrix {

    private Rational[][] matrix;

    /**
     * Konstruktori, luo Matrix -olion annetusta vektorijonosta.
     *
     * @param v SetOfVectors -luokan olio josta luodaan matriisi.
     */
    public Matrix(SetOfVectors v) {
        this.matrix = v.getMatrixArray();

    }

    /**
     * Konstruktori, luo Matrix -olion annetusta vektorijonosta.
     *
     * @param v LinearSystem -luokan olio josta luodaan matriisi.
     */
    public Matrix(LinearSystem l) {
        this.matrix = l.getA();

    }

    /**
     * Konstruktori, luo Matrix -olion annetusta Rational[][] -taulukosta.
     *
     * @param v Rational[][] -luokan olio josta luodaan matriisi.
     */
    public Matrix(Rational[][] a) {
        this.matrix = Count.createCopy(a);
    }

    /**
     * Konstruktori, luo Matrix -olion jonka matriisissa m riviä ja n saraketta.
     *
     * @param m Matriisin rivien määrä.
     * @param n Matriisin sarakkeiden määrä.
     */
    public Matrix(int m, int n) {
        this.matrix = new Rational[m][n];
    }

    // i on rivit ja j on sarakkeet, vakiintuneen käytännön mukaisesti
    /**
     * Metodi asettaa rationaaliluvun haluttuun kohtaan matriisia.
     *
     * @param number Rational -luokan olio eli rationaaliluku joka sijoitetaan.
     * @param i Matriisin rivi johon luku asetetaan. Tässä rivit lasketaan
     * nollasta alkaen, ylhäältä alas.
     * @param j Matriisin sarake johon luku asetetaan. Tässä sarakkeet lasketaan
     * nollasta alkaen, vasemmalta oikealle.
     */
    public void setNumber(Rational number, int i, int j) {
        if (i + 1 > getM() || j + 1 > getN() || i < 0 || j < 0) {
            throw new IllegalArgumentException("Matriisissa ei ole kohtaa jonka "
                    + "i -indeksi on " + i + " ja j -indeksi on " + j
                    + "Matriisissa on " + getM() + "riviä ja " + getN() + "saraketta");
        }
        this.matrix[i][j] = number;
    }

    /**
     * Metodi antaa luvun matriisista halutusta kohdasta.
     *
     * @param i Matriisin rivi josta luku luetaan. Rivit tässä alkaen nollasta,
     * ylhäältä alas.
     * @param j Matriisin sarake josta luku luetaan. Rivit tässä alkaen
     * nollasta, ylhäältä alas.
     * @return Rationaaliluku (Rational -luokan olio) halutusta kohdasta
     * matriisia.
     */
    public Rational getNumber(int i, int j) {
        if (i < getM() && j < getN() && i >= 0 && j >= 0) {
            return this.matrix[i][j];
        } else {
            throw new IllegalArgumentException("Matriisissa ei ole indeksiä "
                    + "(i,j) = " + "(" + i + "," + j + ")");
        }
    }

    /**
     * Metodi palauttaa Rational[][] taulukon Matrix -oliosta.
     *
     * @return Matrix -olion sisäisenä muuttujana olevan Rational[][] taulukon.
     */
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

    private String numberWithSpaces(int i, int j, int longest) {
        String number = matrix[i][j].toString();
        int spacesNeeded = longest - number.length() + 2;
        for (int s = 0; s < spacesNeeded; s++) {
            number += " ";
        }
        return number;
    }

    private int longestNumber() {
        int m = getM();
        int n = getN();
        int longest = 0;
        for (int i = 0; i < m; i++) {
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
