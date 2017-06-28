package linislove.mylittlemath;

/**
 * Count -luokka suorittaa rationaalilukujen (Rational) sekä matriisien (Matrix)
 * laskutoimituksia.
 */
public class Count extends MatrixOperations {

    /**
     * Laskee rationaalilukujen kertolaskun.
     *
     * @param a Rationaaliluku jolla kerrotaan.
     * @param b Rationaaliluku jota kerrotaan.
     * @return Rationaaliluku joka on a:n ja b:n tulo, siis a*b.
     */
    public static Rational product(Rational a, Rational b) {
        return a.multiply(b);
    }

    /**
     * Palauttaa rationaaliluvun käänteisalkion kertolaskun suhteen.
     *
     * @param a Rationaaliluku jonka käänteisalkio halutaan.
     * @return Luvun a käänteisalkio.
     */
    public static Rational reciprocal(Rational a) {
        return a.reciprocal();
    }

    /**
     * Metodi palauttaa rationaaliluvun käänteisalkion yhteenlaskun suhteen.
     *
     * @param a
     * @return Käänteisalkio yhteenlaskun suhteen.
     */
    public static Rational opposite(Rational a) {
        return new Rational(a.getNumerator().negate(), a.getDenominator());
    }

    /**
     * Metodi palauttaa kahden rationaaliluvun summan.
     *
     * @param a Rationaaliluku (Rational a)
     * @param b Rationaaliluku (Rational b)
     * @return Palauttaa rationaaliluvun a + b.
     */
    public static Rational sum(Rational a, Rational b) {
        return a.plus(b);
    }

    /**
     * Metodi palauttaa kahden rationaaliluvun (Rational -luokan olio)
     * erotuksen.
     *
     * @param a Luku josta vähennetään.
     * @param b Luku joka vähennetään.
     * @return Luku joka on edellisten erotus eli a-b
     */
    public static Rational difference(Rational a, Rational b) {
        return sum(a, opposite(b));
    }

    /**
     * Metodi suorittaa rationaalilukujen laskutoimituksen.
     *
     * @param a Rationaaliluku joka jaetaan (Rational a)
     * @param b Rationaaliluku jolla jaetaan (Rational b)
     * @return Rationaaliluvun laskutoimituksesta a / b.
     */
    public static Rational divide(Rational a, Rational b) {
        return product(a, reciprocal(b));
    }

    /**
     * Metodilla selvitetään mikä rationaaliluvun merkki on.
     *
     * @param a Rationaaliluku josta selvitetään merkki
     * @return Kokonaisluku (int) -1, jos a<0, 0 jos a=0 ja 1 jos a>0.
     */
    public static int signum(Rational a) {
        return a.signumOfRational().intValue();
    }

    /**
     * Metodi laskee kokonaisluvuilla (int) paljonko on (-1)^n. Tämä on tehty
     * välttämään mahdollista Math.pow(double, double) aikaansaamaa virhettä.
     *
     * @param n Exponentti.
     * @return Palauttaa int -kokonaisluvun (-1)^n.
     */
    public static int minusOnePoweredTo(int n) {
        return (n % 2 == 0) ? 1 : -1;
    }

    /**
     * Metodi palauttaa merkkijonon yhtälöryhmän ratkaisujen vaatimassa
     * muodossa.
     *
     * @param x Rational[] -taulukko
     * @return Palauttaa merkkijonon siten että sen tuloste on x_1 = 7 x_2 = 3
     * jos x[0} = 7 ja x[1] = 3.
     */
    public static String giveSolutions(Rational[] x) {
        String str = "";
        for (int i = 0; i < x.length; i++) {
            str += "x_" + (i + 1) + " = " + x[i];
            str += (i < x.length - 1) ? "\n" : "";
        }
        return str;
    }

    /**
     * Palauttaa rationaaliluvun itseisarvon.
     *
     * @param a Luku, josta palautetaan itseisarvo.
     * @return Luvun Rational a itseisarvo.
     */
    public static Rational abs(Rational a) {
        return a.abs();
    }

     /**
     * Palauttaa kopion Matrix -oliosta (matriisi).
     *
     * @param a Matriisi joka kopioidaan.
     * @return Kopioitu samansisältöinen matriisi (Matrix -olio).
     */
    public static Matrix createCopy(Matrix a) {
        return new Matrix(createCopy(a.getMatrixArray()));
    }

    /**
     * Palauttaa Rational[][] taulukosta kopion.
     *
     * @param a Taulukko joka kopioidaan.
     * @return Kopioitu uusi taulukko.
     */
    public static Rational[][] createCopy(Rational[][] a) {
        int m = a.length;
        int n = a[0].length;
        Rational[][] copy = new Rational[m][n];
        for (int i = 0; i < m; i++) {
            System.arraycopy(a[i], 0, copy[i], 0, n);
        }
        return copy;
    }

    /**
     * Palauttaa Rational[] taulukosta kopion.
     *
     * @param a Taulukko joka kopioidaan.
     * @return Kopioitu uusi taulukko.
     */
    public static Rational[] createCopy(Rational[] a) {
        int n = a.length;
        Rational[] copy = new Rational[n];
        System.arraycopy(a, 0, copy, 0, n);
        return copy;
    }   
}
