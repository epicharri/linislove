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

    /**
     * Metodi palauttaa Object[] taulukkona seuraavan numeron ja indeksin.
     *
     * @param equation Yhtälöryhmä merkkijonona
     * @param index Indeksi jossa kohti ollaan käymässä merkkijonoa läpi.
     * @return Object[] taulukko, jossa indeksissä 0 on numero ja indeksissä 1
     * on indeksi.
     */
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

    /**
     * Apumetodi metodille nextNumberAndIndex. Palauttaa Object[] -taulukon
     * jossa luku ja indeksi.
     *
     * @param number Luku joka asetetaan taulukkoon indeksiin 0
     * @param index Kokonaisluku (int) joka talletetaan taulukon indeksiin 1.
     * @return Object[] -taulukko.
     */
    default Object[] numberAndIndex(String number, int index) {
        Object[] nAndI = new Object[2];
        nAndI[0] = number;
        nAndI[1] = index;
        return nAndI;
    }

    /**
     * Metodi palauttaa String[] -taulukon jossa merkkijono splitattu
     * taulukkoon.
     *
     * @param s Merkkijono josta taulukko muodostetaan.
     * @return String[] -taulukko jossa merkkijono s splitattu merkin ";"
     * perusteella.
     */
    default String[] giveEquations(String s) {
        s = cleanString(s);
        String[] e = s.split(";");
        return e;
    }

    /**
     * Metodi puhdistaa yhtälöryhmänsyötemerkkijonosta ylimääräiset välilyönnit,
     * rivinvaihdot, ; - ja + -merkit.
     *
     * @param s Puhdistettava merkkijono.
     * @return Palauttaa puhdistetun merkkijonon.
     */
    default String cleanString(String s) {
        s = s.replaceAll("\\s", "");
        s = s.replaceAll("\n", ";");
        s = s.replaceAll(";+", ";");
        s = s.replaceAll(";\\z", "");
        s = s.replaceAll("^;", "");
        return s;
    }
}
