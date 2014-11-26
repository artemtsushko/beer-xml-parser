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
public class BeerNameComparator implements Comparator<Beer>{

    @Override
    public int compare(Beer o1, Beer o2) {
        return o1.getName().compareToIgnoreCase(o2.getName());
    }
    
}
