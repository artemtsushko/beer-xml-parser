/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2xml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.io.FileWriter;
import java.io.Writer;

/**
 *
 * @author Артем
 */
public class BeerSAXParser {
    public static List<Beer> parseDocument(String xmlPath) 
            throws IOException, ParserConfigurationException, SAXException
    {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        BeerDocumentHandler handler = new BeerDocumentHandler(1);
        parser.parse(new File(xmlPath), handler);
        return handler.getBeerList();
    }
    
    public static void testWriter() throws IOException {
        Writer writer = new FileWriter("file.txt");
        String line = "EEE";
        writer.write(line);
        writer.write(System.getProperty("line.separator"));
        writer.flush();
    }
}

class BeerDocumentHandler extends DefaultHandler {
    protected List<Beer> beerList = new ArrayList<>();
    protected Beer currentBeer;
    protected Chars currentChars;
    protected Packaging currentPackaging;
    protected String content;

    public BeerDocumentHandler(int i) {}
    public List<Beer> getBeerList() {
        return beerList;
    }

    @Override
    public void characters(char[] ch, int start, int length) 
            throws SAXException {
        content = new String(ch, start, length).trim();
    }

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        switch (qName) {
            case "beer":
                currentBeer = new Beer();
                currentBeer.setId(attributes.getValue("id").trim());
                break;
            case "chars":
                currentChars = new Chars();
                break;
            case "energy":
                currentChars.energy = new Chars.Energy();
                currentChars.energy.setUnit(attributes.getValue("unit").trim());
                break;
            case "packaging":
                currentPackaging = new Packaging();
                break;
            default:
                break;       
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) 
            throws SAXException {
        switch (qName) {
            case "beer":
                beerList.add(currentBeer);
                break;
            case "name":
                currentBeer.setName(content);
                break;
            case "type":
                currentBeer.setType(content);
                break;
            case "al":
                currentBeer.setAl(Boolean.parseBoolean(content));
                break;
            case "manufacturer":
                currentBeer.setManufacturer(content);
                break;
            case "ingredients":
                currentBeer.getIngredients().add(content);
                break;
            case "chars":
                currentBeer.setChars(currentChars);
                break;
            case "strength":
                currentChars.setStrength(Float.parseFloat(content));
                break;
            case "transparency":
                currentChars.setTransparency(Float.parseFloat(content));
                break;
            case "filtered":
                currentChars.setFiltered(Boolean.parseBoolean(content));
                break;
            case "energy":
                currentChars.energy.setValue(Float.parseFloat(content));
                break;
            case "packaging":
                currentChars.getPackaging().add(currentPackaging);
                break;
            case "material":
                currentPackaging.setMaterial(content);
                break;
            case "volume":
                currentPackaging.setVolume(Float.parseFloat(content));
                break;
            default:
                break;
        }
    }

    

}
