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
interface GreaterOrLessThan {
    
    public boolean greaterThan(Object o);
    public boolean lessThan(Object o);
    @Override
    public boolean equals(Object o);
    public boolean greaterOrEqual(Object o);
    public boolean lessThanOrEqual(Object o);
}
