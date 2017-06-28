/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linislove.mylittlemath;

import java.util.InputMismatchException;

/**
 * Luokka tarkistaa vektorijonon oikeellisuuden ja luo siitä matriisin Matrix
 * -luokan käyttöön.
 *
 */
public class SetOfVectors {

    private final String vectorQueue;
    private Rational[][] matrix;
    private int sizeOfLongestRationalNumber;
    private static final String NOTSAMEVECTORSPACE = "Syöttämäsi vektorijono "
            + "sisältää eri vektoriavaruuksien vektoreita.";

    /**
     * Konstruktori luo merkkijonosta olion Matrix-luokkaa varten.
     *
     * @param v Merkkijono josta olio luodaan. Merkkijonon syntaksi: Olkoon k,
     * n, m positiivisia kokonaislukuja. Olkoon v_k vektoriavaruuden R^n
     * vektori. Olkoon v_k_1, v_k_2, ..., v_k_n vektorin v_k komponentit. Kunkin
     * komponentin on oltava joko desimaaliluku jossa desimaalierotteena toimii
     * piste (.), rationaaliluku, jossa osoittaja ja nimittäjä on kokonaislukuja
     * ja niiden välissä on merkki /, tai kokonaislukuja. Syöte on annettava
     * muodossa: String v =
     * "(<v_1_1>,<v_1_2>,...,<v_1_n>),...,(<v_m_1>,<v_m_2>,...,<v_m_n>)";
     *
     * Esimerkki: String v = "(1, 2/7), (1.22, 123456)";
     *
     */
    public SetOfVectors(String vectorSet) throws InputMismatchException {
        this.vectorQueue = vectorSet;
        sizeOfLongestRationalNumber = 0;
        parseStringOfSetOfVectorsToMatrixArray(vectorSet);
    }

    private String[] parseVectors(String v) {
        v = v.replaceAll("//s", "");
        v = v.replaceAll("\\(", "");
        v = v.replaceAll("\\),", "@");
        v = v.replaceAll("\\)", "");
        String[] vector = v.split("@");
        return vector;
    }

    /*
    public int getNumberOfVectors() {
        return this.matrix[0].length;
    }
    
    public int getDimensionOfVectorSpace() {
        return this.matrix.length;
    }
     */
    private void parseStringOfSetOfVectorsToMatrixArray(String v) throws InputMismatchException {

        String[] vectors = parseVectors(v);
        int n = vectors.length; // columns eli sarakkeet, vektorien määrä
        int m = vectors[0].split(",").length; // rows eli rivit, komponenttien määrä
        this.matrix = new Rational[m][n];

        try {
            for (int j = 0; j < n; j++) {
                String[] componentsOfVector = vectors[j].split(",");
                if (componentsOfVector.length != m) {
                    throw new InputMismatchException(NOTSAMEVECTORSPACE);
                }
                for (int i = 0; i < m; i++) {
                    Rational number = new Rational(componentsOfVector[i]);
                    if (number.toString().length() > this.sizeOfLongestRationalNumber) {
                        sizeOfLongestRationalNumber = number.toString().length();
                    }
                    this.matrix[i][j] = number;
                }
            }
        } catch (InputMismatchException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
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
