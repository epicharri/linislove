/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linislove.mylittlemath;

import java.util.HashMap;
import java.util.InputMismatchException;

/**
 *
 * Luokka muuntaa merkkijonosyötteestä matriisit yhtälöä Ax=b varten.
 */
public class LinearSystem implements LinearSystemHelper {

    private Rational[][] a;
    private Rational[][] b;
    private String system;
    private int maxNumberOfUnknowns;

    /**
     * Konstruktori, joka luo LinearSystem -olion merkkijonosyötteestä.
     *
     * @param system Merkkijono josta olio luodaan. Syöte on annettava muodossa:
     * "c_1_1x1 + c_1_2x2 + ... + c_1_kxk = b1; c_2_1x1 + c_2_2x2 + ... +
     * c_2_kxk = b_2; ... c_m_1x1 + c_m_2x2 + ... + c_m_kxk = b_m;"
     *
     * Esimerkki: String system = "-x1 + 7x2 + (10/3)x3 = 5; 2x1 + (4/11)x2 +
     * 1.25x3 = 4/7; x1 + x2 + (17/87)x3 = 172";
     * @throws InputMismatchException
     * @throws java.lang.Exception
     */
    public LinearSystem(String system) throws Exception {
        this.system = system;
        this.maxNumberOfUnknowns = 0;
        try {
            parseLinearSystem();
        } catch (InputMismatchException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }

    private void parseLinearSystem() throws InputMismatchException {
        String[] equations = giveEquations(this.system);
        int numberOfEquations = equations.length;
        HashMap[] equationMaps = new HashMap[numberOfEquations];
        for (int i = 0; i < numberOfEquations; i++) {
            equationMaps[i] = parseEquation(equations[i]);
        }

        if (numberOfEquations != maxNumberOfUnknowns) {
            throw new InputMismatchException("Yhtälöiden ja tuntemattomien määrä on "
                    + "erisuuri joten yhtälöryhmä ei ole kvadraattinen. "
                    + "Sovellus ratkaisee vain kvadraattisia yhtälöryhmiä.");
        }

        initMatricesAandB(numberOfEquations, this.maxNumberOfUnknowns);
        for (int i = 0; i < equationMaps.length; i++) {
            HashMap row = equationMaps[i];
            for (int j = 0; j < this.maxNumberOfUnknowns; j++) {
                Object number = row.get(j + 1);
                if (number != null) {
                    a[i][j] = new Rational((String) number);
                }
            }
            Object num = row.get(Integer.MIN_VALUE);
            String number = (String) num;
            b[i][0] = new Rational(number);
        }
    }

    public Rational[][] getA() {
        return a;
    }

    public Rational[][] getB() {
        return b;
    }

    public String getSystem() {
        return system;
    }

    private void initMatricesAandB(int rows, int columns) {
        this.a = new Rational[rows][columns];
        this.b = new Rational[rows][1];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                this.a[i][j] = Rational.ZERO;
            }
            b[i][0] = Rational.ZERO;
        }
    }

    private HashMap parseEquation(String equation) {
        HashMap equationRow = new HashMap<>();
        int lastIndex = equation.length() - 1;
        int index = 0;
        while (index <= lastIndex && equation.charAt(index) != '=') {
            Object[] nAndI = nextNumberAndIndex(equation, index);
            String number = (String) nAndI[0];
            index = (Integer) nAndI[1];
            int[] xAndI = nextXandIndex(equation, index);
            index = xAndI[1];
            int subs = xAndI[0];
            equationRow.put(subs, number);
            if (subs > this.maxNumberOfUnknowns) {
                this.maxNumberOfUnknowns = subs;
            }
        }
        Object[] rightSideOfEquation = nextNumberAndIndex(equation, index + 1);

        String number = (String) rightSideOfEquation[0];
        equationRow.put(Integer.MIN_VALUE, number);
        return equationRow;
    }

    private int[] nextXandIndex(String equation, int index) throws InputMismatchException {
        String subscript = "";
        int lastIndex = equation.length() - 1;
        while (index < lastIndex && equation.charAt(index) != '=') {
            Character c = equation.charAt(index);
            if (c.toString().matches("\\d")) {
                subscript += c;
            } else {
                break;
            }
            index++;
        }
        if (subscript.isEmpty() || !subscript.matches("\\d+")) {
            throw new InputMismatchException("Korjaa syötettä niin että "
                    + "se on muodossa:\n"
                    + "<kerroin>x<alaindeksi> + <kerroin>x<alaindeksi> + ... + <kerroin>x<alaindeksi> = <luku>;\n"
                    + "<kerroin>x<alaindeksi> + <kerroin>x<alaindeksi> + ... + <kerroin>x<alaindeksi>= <luku>;\n"
                    + "...\n"
                    + "<kerroin>x<alaindeksi> + <kerroin>x<alaindeksi> + ... + <kerroin>x<alaindeksi>= <luku>;\n"
                    + "(+ tilalla voi olla myös -). Muista puolipiste yhtälörivin loppuun. "
                    + "Esimerkki: \n"
                    + "-2/3 x1 + 3 x2 - 17/23 x3 = 4/-5;\n"
                    + "-x1 - x3 = -(-17/-19);\n"
                    + "x3 = 11;\n"
                    + "Yhtälöryhmässä siis oletetaan olevan tuntemattomia (x) yhtä monta kuin on suurin alaindeksi. Syötteessä on oltava yhtä monta yhtälöä kuin on tuntemattomia.");
        }
        int[] nextXandI = new int[2];
        nextXandI[0] = Integer.parseInt(subscript);
        nextXandI[1] = index;
        return nextXandI;
    }

    @Override
    public String toString() {
        String equations = system;
        return equations.replaceAll(";", ";\n");
    }
}
