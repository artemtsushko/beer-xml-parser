package lab2xml;

import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.File;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 *
 * @author Артем
 */
public class BeerXmlValidator {
    
    public static boolean validate(String XMLPath, String XSDPath) 
            throws IOException {
        
        SchemaFactory schemaFactory = SchemaFactory
                .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = null;
        try {
            schema = schemaFactory.newSchema(new File(XSDPath));
        } catch (SAXException ex) {
            System.err.println("The schema file seems to be not valid");
            System.err.println("Details:");
            System.err.println(ex);
            return false;
        }
        InputStream xmlFile = new FileInputStream(XMLPath);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        factory.setValidating(false); // we will use schema instead of DTD
        factory.setSchema(schema);
        DocumentBuilder parser = null;
        try {
            parser = factory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            System.err.println("Exception cought from DocumentBuilderFactory");
            System.err.println("Details:");
            System.err.println(ex);
            return false;
        }
        ErrorHandlerImpl errorHandler = new ErrorHandlerImpl();
        parser.setErrorHandler(errorHandler);
        try {
            parser.parse(xmlFile);
        } catch (SAXException ex) {
            //System.err.println(ex);
            return false;
        }
        return true;
    }
}

class ErrorHandlerImpl implements ErrorHandler {

    @Override
    public void warning(SAXParseException exception) throws SAXException {
        throw exception;
    }

    @Override
    public void error(SAXParseException exception) throws SAXException {
        throw exception;
    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        throw exception;
    }
    
}

