package lab2xml;

import static java.lang.System.exit;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import org.xml.sax.SAXException;


public class Main {

    protected static final String xmlPath = ".\\src\\lab2xml\\Beer.xml";
    protected static final String xsdPath = ".\\src\\lab2xml\\Beer.xsd";
    protected static final String xslPath = ".\\src\\lab2xml\\Beer.xsl";
    protected static final String outDir  = ".\\output\\";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try {
            // validation
            if(BeerXmlValidator.validate(xmlPath, xsdPath) == false) {
                System.err.println("XML " + xmlPath + " is NOT VALID");
                exit(1);
            }
            
            // parsers test
            testDOMParser();
            testSAXParser();
            testStAXParser();
            
        } catch (IOException | ParserConfigurationException 
                 | SAXException | XMLStreamException ex) {
            System.err.println(ex);
        }  
    } 
    
    public static void testDOMParser() 
            throws IOException, ParserConfigurationException, SAXException {
        List<Beer> beerList = BeerDOMParser.parseDocument(xmlPath);
        Collections.sort(beerList,new lab2xml.comparators.BeerNameComparator());
        BeerMarshaller.marshall(beerList,outDir + "DOM+SortByName.xml");
        if (BeerXmlValidator.validate(outDir + "DOM+SortByName.xml",
                                      xsdPath) 
            == false) {
            System.err.println("XML " + outDir + "DOM+SortByName.xml"
                                + " is NOT VALID");
            exit(1);
        }
        BeerXMLToHTMLTransformer.transform(outDir + "DOM+SortByName.xml",
                                           xslPath,
                                           outDir + "DOM+SortByName.html");
    }
    
    public static void testSAXParser() 
            throws IOException, ParserConfigurationException, SAXException {
        List<Beer> beerList = BeerSAXParser.parseDocument(xmlPath);
        Collections.sort(beerList,new lab2xml.comparators.BeerStrengthComparator());
        BeerMarshaller.marshall(beerList,outDir + "SAX+SortByStrength.xml");
        if (BeerXmlValidator.validate(outDir + "SAX+SortByStrength.xml",
                                      xsdPath) 
            == false) {
            System.err.println("XML " + outDir + "SAX+SortByStrength.xml"
                                + " is NOT VALID");
            exit(1);
        }
        BeerXMLToHTMLTransformer.transform(outDir + "SAX+SortByStrength.xml", 
                                           xslPath,
                                           outDir + "SAX+SortByStrength.html");
    }
    
    public static void testStAXParser() 
            throws IOException, XMLStreamException {
        List<Beer> beerList = BeerStAXParser.parseDocument(xmlPath);
        Collections.sort(beerList,
                         new lab2xml.comparators.BeerManufacturerAndNameComparator());
        BeerMarshaller.marshall(beerList, 
                                outDir + "StAX+SortByManufacturerAndName.xml");
        if (BeerXmlValidator.validate(outDir + "StAX+SortByManufacturerAndName.xml",
                                      xsdPath) 
            == false) {
            System.err.println("XML " 
                                + outDir + "StAX+SortByManufacturerAndName.xml"
                                + " is NOT VALID");
            exit(1);
        }
        BeerXMLToHTMLTransformer.transform(
                outDir + "StAX+SortByManufacturerAndName.xml", 
                xslPath,
                outDir + "StAX+SortByManufacturerAndName.html");
    }
}
