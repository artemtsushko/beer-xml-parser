/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2xml;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 *
 * @author Артем
 */
public class BeerStAXParser {
    public static List<Beer> parseDocument(String xmlPath) 
            throws XMLStreamException, FileNotFoundException {
        
        Reader fileReader = new FileReader(xmlPath);
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader xmlReader = factory.createXMLStreamReader(fileReader);
        return parseXmlStream(xmlReader);       
    }
    
    protected static List<Beer> parseXmlStream(XMLStreamReader reader) 
            throws XMLStreamException {
        List<Beer> beerList = new ArrayList<>();
        Beer currentBeer = null;
        Chars currentChars = null;
        Packaging currentPackaging = null;
        
        while (reader.hasNext()) {
            int event = reader.next();
            switch (event) {
                case XMLStreamConstants.START_ELEMENT:
                    String localName = reader.getLocalName();
                    switch (localName) {
                        case "beer":
                            currentBeer = new Beer();
                            currentBeer.setId(
                                    reader.getAttributeValue("", "id"));
                            beerList.add(currentBeer);
                            break;
                        case "name":
                            currentBeer.setName(
                                    reader.getElementText().trim());
                            break;
                        case "type":
                            currentBeer.setType(
                                    reader.getElementText().trim());
                            break;
                        case "al":
                            currentBeer.setAl(Boolean.parseBoolean(
                                    reader.getElementText().trim()));
                            break;
                        case "manufacturer":
                            currentBeer.setManufacturer(
                                    reader.getElementText().trim());
                            break;
                        case "ingredients":
                            currentBeer.getIngredients().add(
                                    reader.getElementText().trim());
                            break;
                        case "chars":
                            currentChars = new Chars();
                            currentBeer.setChars(currentChars);
                            break;
                        case "strength":
                            currentChars.setStrength(Float.parseFloat(
                                    reader.getElementText().trim()));
                            break;
                        case "transparency":
                            currentChars.setTransparency(Float.parseFloat(
                                    reader.getElementText().trim()));
                            break;
                        case "filtered":
                            currentChars.setFiltered(Boolean.parseBoolean(
                                    reader.getElementText().trim()));
                            break;
                        case "energy":
                            currentChars.energy = new Chars.Energy();
                            currentChars.energy.setUnit(
                                    reader.getAttributeValue(null, "unit"));
                            currentChars.energy.setValue(Float.parseFloat(
                                    reader.getElementText().trim()));
                            break;
                        case "packaging":
                            currentPackaging = new Packaging();
                            currentChars.getPackaging().add(currentPackaging);
                            break;
                        case "material":
                            currentPackaging.setMaterial(
                                    reader.getElementText().trim());
                            break;
                        case "volume":
                            currentPackaging.setVolume(Float.parseFloat(
                                    reader.getElementText().trim()));
                            break;
                        default:
                            break;
                    }
                break;
            }
        }
        return beerList;
        
    }
}
