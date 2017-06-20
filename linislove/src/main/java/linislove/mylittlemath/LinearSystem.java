/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linislove.mylittlemath;

import java.math.BigInteger;
import java.util.HashMap;

/**
 *
 * Luokka muuntaa merkkijonosyötteestä matriisit yhtälöä Ax=b varten. Syöte on
 * annettava muodossa: "c_1_1x1 + c_1_2x2 + ... + c_1_kxk = b1; c_2_1x1 +
 * c_2_2x2 + ... + c_2_kxk = b_2; ... c_m_1x1 + c_m_2x2 + ... + c_m_kxk = b_m; "
 *
 * Esimerkki: String system = "-x1 + 7x2 + (10/3)x3 = 5; 2x1 + (4/11)x2 + 1.25x3
 * = 4/7 + x1 + x2 + (17/87)x3 = 172";
 */
public class LinearSystem {

    private Rational[][] a;
    //private int[] subScriptsOfUnknowns;
    private Rational[][] b;
    private int sizeOfLongestRationalNumber;
    private String system;
    private int maxNumberOfUnknowns;

    public LinearSystem(String system) {
        this.system = system;
        this.sizeOfLongestRationalNumber = 0;
        this.maxNumberOfUnknowns = 0;
        if (!parseLinearSystem()) {
            throw new RuntimeException("Vääränlainen syöte yhtälöryhmälle.");
        }
    }

    private boolean parseLinearSystem() {
        String[] equations = giveEquations(this.system);
        int numberOfEquations = equations.length;
        HashMap[] equationMaps = new HashMap[numberOfEquations];
        for (int i = 0; i < numberOfEquations; i++) {
            equationMaps[i] = parseEquation(equations[i]);
        }
        if (numberOfEquations != maxNumberOfUnknowns) {
            return false;
        }
        initMatricesAandB(numberOfEquations, this.maxNumberOfUnknowns);
        for (int i = 0; i < equationMaps.length; i++) {
            HashMap row = equationMaps[i];
            for (int j = 0; j < this.maxNumberOfUnknowns; j++) {
                Object number = row.get(j + 1);
                if (number != null) {
                    a[i][j] = new Rational((String) number);
                    setSizeOfLongestIfNecessary(a[i][j]);
                }
            }
            Object num = row.get(Integer.MIN_VALUE);
            String number = (String) num;
            b[i][0] = new Rational(number);
            setSizeOfLongestIfNecessary(b[i][0]);
        }
        return true;
    }

    private void setSizeOfLongestIfNecessary(Rational number) {
        int size = number.toString().length();
        if (size > this.sizeOfLongestRationalNumber) {
            this.sizeOfLongestRationalNumber = size;
        }
    }

    public Rational[][] getA() {
        return a;
    }

    public Rational[][] getB() {
        return b;
    }

    public int getSizeOfLongestRationalNumber() {
        return sizeOfLongestRationalNumber;
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

    private int[] nextXandIndex(String equation, int index) {
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
            throw new RuntimeException("Muuttujalla x ei ole alaindeksiä "
                    + "joten yhtälöryhmä on väärin syötetty.");
        }
        int[] nextXandI = new int[2];
        nextXandI[0] = Integer.parseInt(subscript);
        nextXandI[1] = index;
        return nextXandI;
    }

    private Object[] nextNumberAndIndex(String equation, int index) {
        String number = "";
        int lastIndex = equation.length() - 1;
        while (true) {
            if (index > lastIndex) {
                return numberAndIndex(number, index);
            }
            char c = equation.charAt(index);
            if (c != 'x') {
                number += c;
            } else {
                index++;
                if (number.isEmpty() || number.equals("+")) {
                    return numberAndIndex("1", index);
                }
                if (number.equals("-")) {
                    return numberAndIndex("-1", index);
                }
                return numberAndIndex(number, index);
            }
            index++;
        }
    }

    private Object[] numberAndIndex(String number, int index) {
        Object[] nAndI = new Object[2];
        nAndI[0] = number;
        nAndI[1] = index;
        return nAndI;
    }

    private String[] giveEquations(String s) {
        s = cleanString(s);
        String[] e = s.split(";");
        return e;
    }

    public String cleanString(String s) {
        s = s.replaceAll("\\s", "");
        s = s.replaceAll("\n", ";");
        s = s.replaceAll(";+", ";");
        s = s.replaceAll(";\\z", "");
        s = s.replaceAll("^;", "");
        return s;
    }

    @Override
    public String toString() {
        return linearSystem();
    }

    private String linearSystem() {

        String print = "";
        int m = a.length;
        int n = a[0].length;
        int bm = b.length;
        int bn = b[0].length;
        if (bm != m) {
            return "Matriisit A ja b eri "
                    + "korkuisia. A:ssa rivejä " + m + " ja b:ssä " + bm;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Rational number = a[i][j];
                String element = giveCoefficientAndVariableX(number, j);
                print += (element.isEmpty()) ? "" : element + " ";
            }
            print += "= " + b[i][0] + "\n";
        }
        return print;
    }

    private static String giveCoefficientAndVariableX(Rational number, int j) {
        String print = "";

        int sign = Count.signum(number);
        if (sign == 0 && j > 0) {
            return "+ 0";
        }
        if (sign == 0 && j == 0) {
            return "0";
        }
        if (j > 0 && sign > 0) {
            print += "+ ";
        }
        if (sign < 0) {
            print += "-";
        }
        if (sign < 0) {
            number = Count.opposite(number);
        }
        if (number.equals(Rational.ONE)) {
            return print + "x_" + (j + 1);
        }
        if (number.getDenominator().equals(BigInteger.ONE)) {
            print += number.toString();
        } else {
            print += number;
        }
        print += "x_" + (j + 1);
        return print;
    }

}
