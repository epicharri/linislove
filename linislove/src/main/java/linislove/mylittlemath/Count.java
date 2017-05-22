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
        Rational simpleA = simplify(a);
        Rational simpleB = simplify(b);
        int upstairs = simpleA.getNumerator() * simpleB.getNumerator();
        int downstairs = simpleA.getDenominator() * simpleB.getDenominator();
        Rational multiplied = new Rational(upstairs, downstairs);
        return simplify(multiplied);
    }
    
    public static Rational reciprocal(Rational a){
        return new Rational(a.getDenominator(), a.getNumerator());
        // tarkastettava ettei ole 0.
    }
    
    public static Rational opposite(Rational a){
        return new Rational(a.getNumerator()*-1,a.getDenominator());
    }
    
    public static Rational sum(Rational a, Rational b) {
        Rational simpleA = simplify(a);
        Rational simpleB = simplify(b);
        int upstairs = simpleA.getNumerator() * simpleB.getDenominator() +
                simpleB.getNumerator() * simpleA.getDenominator();
        int downstairs = simpleA.getDenominator() * simpleB.getDenominator();
        Rational additioned = new Rational(upstairs, downstairs);
        return simplify(additioned);
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
    
}
