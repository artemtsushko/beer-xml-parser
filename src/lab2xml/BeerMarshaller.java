/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2xml;

import java.io.File;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

/**
 *
 * @author Артем
 */
public class BeerMarshaller {
    public static void marshall(List<Beer> beerList, 
                                String xmlPath) {
        BeerCollection beerCollection = new BeerCollection();
        beerCollection.getBeer().addAll(beerList);
        try {
            JAXBContext context = JAXBContext.newInstance(BeerCollection.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            File file=new File(xmlPath);
            marshaller.marshal(beerCollection, file); 
        } catch(Exception e) {
            System.err.print(e);
        }
    }
}
