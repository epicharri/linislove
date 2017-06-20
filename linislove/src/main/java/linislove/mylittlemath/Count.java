package linislove.mylittlemath;

import java.math.BigInteger;

/**
 * Count -luokka suorittaa rationaalilukujen (Rational) sekä matriisien (Matrix)
 * laskutoimituksia.
 */
public class Count {

    /**
     * Laskee rationaalilukujen kertolaskun.
     *
     * @param a Rationaaliluku jolla kerrotaan.
     * @param b Rationaaliluku jota kerrotaan.
     * @return Rationaaliluku joka on a:n ja b:n tulo, siis a*b.
     */
    public static Rational product(Rational a, Rational b) {
        BigInteger upstairs = a.getNumerator().multiply(b.getNumerator());
        BigInteger downstairs = a.getDenominator().multiply(b.getDenominator());
        return new Rational(upstairs, downstairs);
    }

    /**
     * Palauttaa rationaaliluvun käänteisalkion kertolaskun suhteen.
     * @param a     Rationaaliluku jonka käänteisalkio halutaan.
     * @return      Luvun a käänteisalkio.
     */
    public static Rational reciprocal(Rational a) {
        if (!a.getNumerator().equals(BigInteger.ZERO)) {
            return new Rational(a.getDenominator(), a.getNumerator());
        } else {
            throw new RuntimeException("Nollalla ei ole käänteislukua.");
        }
    }

    public static Rational opposite(Rational a) {
        return new Rational(a.getNumerator().negate(), a.getDenominator());
    }

    public static Rational sum(Rational a, Rational b) {
        if (a.getDenominator().equals(b.getDenominator())) {
            return new Rational(a.getNumerator().add(b.getNumerator()),
                    a.getDenominator());
        }
        BigInteger upstairs = a.getNumerator().multiply(b.getDenominator())
                .add(b.getNumerator().multiply(a.getDenominator()));
        BigInteger downstairs = a.getDenominator().multiply(b.getDenominator());
        return new Rational(upstairs, downstairs);
    }

    /**
     * Metodi palauttaa kahden rationaaliluvun (Rational -luokan olio) erotuksen.
     * @param a     Luku josta vähennetään.
     * @param b     Luku joka vähennetään.
     * @return      Luku joka on edellisten erotus eli a-b
     */
    public static Rational difference(Rational a, Rational b) {
        return sum(a, opposite(b));
    }

    public static Rational divide(Rational a, Rational b){
        return product(a, reciprocal(b));
    }
    
    public static int signum(Rational a) {
        return a.signumOfRational().intValue();
    }

    public static int minusOnePoweredTo(int n) {
        return (n % 2 == 0) ? 1 : -1;
    }

    public static Rational det(Matrix matrix) {
        if (matrix.getM() != matrix.getN() || matrix.getN() == 0) {
            return null;
        }
        return determinant(matrix.getMatrixArray(), matrix.getM(), matrix.getN());
    }

    public static Rational determinant(Rational[][] A, int m, int n) {
        Rational detA = new Rational(0, 1);
        if (n == 1) {
            detA = A[0][0];
        } else if (n == 2) {
            detA = difference(product(A[0][0], A[1][1]),
                    product(A[1][0], A[0][1]));
        } else {
            for (int j1 = 0; j1 < n; j1++) {
                Rational[][] c = new Rational[n - 1][];
                for (int k = 0; k < (n - 1); k++) {
                    c[k] = new Rational[n - 1];
                }
                for (int i = 1; i < n; i++) {
                    int j2 = 0;
                    for (int j = 0; j < n; j++) {
                        if (j == j1) {
                            continue;
                        }
                        c[i - 1][j2] = A[i][j];
                        j2++;
                    }
                }
                detA = Count.sum(detA, product(product(new Rational(minusOnePoweredTo(1 + j1 + 1)),
                        A[0][j1]), determinant(c, n - 1, n - 1)));
            }
        }
        return detA;
    }

    public static Matrix transpose(Matrix A) {
        int n = A.getM();
        int m = A.getN();
        Rational[][] arrayT = new Rational[n][m];
        Rational[][] arrayA = A.getMatrixArray();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arrayT[j][i] = arrayA[i][j];
            }
        }
        return new Matrix(arrayT);
    }

    public static Matrix multiply(Matrix matrixA, Matrix matrixB) {
        Rational[][] a = matrixA.getMatrixArray();
        Rational[][] b = matrixB.getMatrixArray();
        int aN = matrixA.getN();
        int aM = matrixA.getM();
        int bN = matrixB.getN();
        int bM = matrixB.getM();
        if (aN != bM) {
            throw new RuntimeException("Matriisikertolasku ei ole määritelty näillä matriiseilla.");
        }
        Matrix matrixAb = new Matrix(aM, bN);
        Rational[][] ab = matrixAb.getMatrixArray();

        for (int i = 0; i < matrixAb.getM(); i++) {
            for (int j = 0; j < matrixAb.getN(); j++) {
                for (int k = 0; k < aN; k++) {
                    if (ab[i][j] == null) {
                        ab[i][j] = new Rational(0);
                    }
                    matrixAb.setNumber(
                            Count.sum(
                                    ab[i][j],
                                    Count.product(a[i][k], b[k][j])
                            ),
                            i, j);
                }
            }
        }
        return matrixAb;
    }

    public static Matrix solveByCramerRule(Matrix a, Matrix b) {
        if (a.getM() != a.getN()) {
            throw new RuntimeException(""
                    + "Cramerin säännöllä voi ratkaista vain kvadraattisia "
                    + "yhtälöryhmiä.");
        }
        if (!(a.getM() == b.getM())) {
            throw new RuntimeException("Matriisit annettava siten että "
                    + "A:ssa ja b:ssä on saman verran rivejä.");
        }
        Matrix valuesOfX = new Matrix(b.getM(), 1);
        Rational detA = det(a);
        if (detA.equals(Rational.ZERO)) {
            throw new RuntimeException("Yhtälö"
                    + "ryhmällä ei ole yksikäsitteistä ratkaisua.");
        }
        Rational detAreciprocal = reciprocal(detA);
        for (int i = 0; i < b.getM(); i++) {
            Rational deti = det(substitute(a, b, i));
            Rational x = product(deti, detAreciprocal);
            valuesOfX.setNumber(x, i, 0);
        }
        return valuesOfX;
    }

    public static String giveSolutions(Matrix x) {
        String str = "";
        for (int i = 0; i < x.getM(); i++) {
            str += "x" + (i + 1) + " = " + x.getNumber(i, 0);
            str += (i < x.getM() - 1) ? "\n" : "";
        }
        return str;
    }

    public static String giveSolutions(Rational[] x) {
        String str = "";
        for (int i = 0; i < x.length; i++) {
            str += "x_" + (i + 1) + " = " + x[i];
            str += (i < x.length - 1) ? "\n" : "";
        }
        return str;
    }
    
    public static Matrix substitute(Matrix A, Matrix b, int j) {
        Matrix replaced = createCopy(A);
        for (int i = 0; i < b.getM(); i++) {
            Rational number = b.getNumber(i, 0);
            replaced.setNumber(number, i, j);
        }
        return replaced;
    }

    public static Matrix createCopy(Matrix a) {
        return new Matrix(createCopy(a.getMatrixArray()));
    }
    
    public static Rational[][] createCopy(Rational[][] a){
        int m = a.length;
        int n = a[0].length;
        Rational[][] copy = new Rational[m][n];
        for (int i = 0; i < m; i++){
            System.arraycopy(a[i], 0, copy[i], 0, n);
        }       
        return copy;
    }
    
    public static Rational[] createCopy(Rational[] a){
        int n = a.length;
        Rational[] copy = new Rational[n];
        System.arraycopy(a, 0, copy, 0, n);       
        return copy;
    }    
    
    public static boolean firstGreaterThanSecond(Rational a, Rational b){
        int sign = signum(difference(a,b));
        return sign > 0;
    }
    
    public static int gcd(int num, int denom) {
        if (denom == 0) {
            return num;
        }
        return gcd(denom, num % denom);
    }
    
    public static Rational abs(Rational a) {
        if (Count.signum(a) >= 0) {
            return a;
        }
        return Count.opposite(a);
    }

}
