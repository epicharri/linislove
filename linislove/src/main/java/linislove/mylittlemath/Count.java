/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linislove.mylittlemath;

/**
 *
 * @author harrikah
 */
public class Count {
    
    public static Rational product(Rational a, Rational b) {
        int upstairs = a.getNumerator() * b.getNumerator();
        int downstairs = a.getDenominator() * b.getDenominator();
        return new Rational(upstairs, downstairs);
    }
    
    public static Rational reciprocal(Rational a){
        return new Rational(a.getDenominator(), a.getNumerator());
        // tarkastettava ettei ole 0.
    }
    
    public static Rational opposite(Rational a){
        return new Rational(a.getNumerator()*-1,a.getDenominator());
    }
    
    public static Rational sum(Rational a, Rational b) {
        int upstairs = a.getNumerator() * b.getDenominator() +
                b.getNumerator() * a.getDenominator();
        int downstairs = a.getDenominator() * b.getDenominator();
        return new Rational(upstairs, downstairs);
    }
    
    public static Rational difference(Rational a, Rational b) {
        return sum(a, opposite(b));
    }
    
    public static int signum(Rational a) {
        int numSign = (a.getNumerator() >= 0) ? 1 : -1;
        numSign = (a.getNumerator() == 0) ? 0 : numSign;
        int denomSign = (a.getDenominator() > 0) ? 1 : -1;
        int ratSign = (numSign * denomSign >= 0) ? 1 : -1;
        ratSign = (numSign == 0) ? 0 : ratSign;
        return ratSign;
    }

    // antaa supistetun rationaaliluvun
    public static Rational simplify(Rational rational) {
        if (rational.getNumerator() == 0) 
            return new Rational(0, 1);
        if (rational.getDenominator() == 1)
            return new Rational(rational.getNumerator(), 1);
        int a = GCD(rational.getNumerator(), rational.getDenominator());
        Rational simplified = new Rational(rational.getNumerator() / a, 
                rational.getDenominator() / a);
        int sign = Count.signum(simplified);
        simplified.setNumerator(Math.abs(simplified.getNumerator()) * sign);
        simplified.setDenominator(Math.abs(simplified.getDenominator()));
        return simplified;
    }

    // Palauttaa GCD:n eli syt:n: Eukleideen algoritmin implementaatio
    public static int GCD(int num, int denom) {
        if (denom == 0) return num;
        return GCD(denom, num % denom);
    }
    
    public static int powint(int a, int x){
        if (x < 0) throw new RuntimeException("Exponentti ei ole positiivinen");
        if (x == 0) return 1;
        if (x == 1) return a;
        if (x%2==0) return powint(a*a,x/2);
        else return a*powint(a*a,x/2);
    }
    
    public static Rational determinant(Rational[][] A, int m, int n){
        Rational det = new Rational(0,1);
        if (n != m || n == 0) return null;
        if (n == 1) det = A[0][0];
        else if (n == 2) det = difference(product(A[0][0],A[1][1]),
                product(A[1][0],A[0][1]));
        else {
            for (int j1=0; j1<n; j1++){
                Rational[][] c = new Rational[n-1][];
                for (int k=0; k<(n-1); k++){
                    c[k] = new Rational[n-1];
                }
                for (int i=1; i<n; i++){
                    int j2 = 0;
                    for (int j=0; j<n; j++){
                        if (j == j1) continue;
                        c[i-1][j2] = A[i][j];
                        j2++;
                    }
                }
                det = Count.sum(det, product(product(new Rational(powint(-1,1+j1+1)),
                        A[0][j1]),determinant(c,n-1,n-1)));
            }
        }
        return det;
    }
    
    
}
