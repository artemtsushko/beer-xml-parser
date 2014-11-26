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
public class BeerManufacturerAndNameComparator implements Comparator<Beer>{

    @Override
    public int compare(Beer o1, Beer o2) {
        int cmp = o1.getManufacturer()
                .compareToIgnoreCase(o2.getManufacturer());
        if (cmp != 0) {
            return cmp;
        } else {
            return o1.getName().compareToIgnoreCase(o2.getName());
        }
    }   
}
