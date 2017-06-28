/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linislove.mylittlemath;

/**
 * Luokka sisältää LinearSystem -luokkaa varten metodeja.
 *
 * @author harrikah
 */
public interface LinearSystemHelper {

    default Object[] nextNumberAndIndex(String equation, int index) {
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

    default Object[] numberAndIndex(String number, int index) {
        Object[] nAndI = new Object[2];
        nAndI[0] = number;
        nAndI[1] = index;
        return nAndI;
    }

    default String[] giveEquations(String s) {
        s = cleanString(s);
        String[] e = s.split(";");
        return e;
    }

    default String cleanString(String s) {
        s = s.replaceAll("\\s", "");
        s = s.replaceAll("\n", ";");
        s = s.replaceAll(";+", ";");
        s = s.replaceAll(";\\z", "");
        s = s.replaceAll("^;", "");
        return s;
    }
}
