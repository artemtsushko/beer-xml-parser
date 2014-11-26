/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2xml.comparators;

import java.util.Comparator;
import lab2xml.Beer;

/**
 *
 * @author Артем
 */
public class BeerStrengthComparator implements Comparator<Beer>{

    @Override
    public int compare(Beer o1, Beer o2) {
        Float strength1 = o1.getChars().getStrength();
        Float strength2 = o2.getChars().getStrength();
        if(strength1 == null) {
            if(strength2 == null) 
                return 0;
            else 
                return -1;
        } else if(strength2 == null) {
            return 1;
        } else 
            return strength1.compareTo(strength2);
    }
    
}
