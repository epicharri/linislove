/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linislove.mylittlemath;

/**
 * Luokka tarkistaa vektorijonon oikeellisuuden ja luo siitä matriisin Matrix
 * -luokan käyttöön.
 *
 */
public class SetOfVectors {

    private final String vectorQueue;
    private Rational[][] matrix;
    private int sizeOfLongestRationalNumber;

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
        this.matrix = new Rational[m][n];

        for (int j = 0; j < n; j++) {
            String[] componentsOfVector = vector[j].split(",");
            if (componentsOfVector.length != m) {
                return false;
            }
            for (int i = 0; i < m; i++) {
                Rational number = new Rational(componentsOfVector[i]);
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
