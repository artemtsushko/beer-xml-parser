
package lab2xml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class BeerXMLToHTMLTransformer {
    public static void transform (String inXMLPath, 
                                  String inXSLPath, String outHTMLPath) 
                                    throws FileNotFoundException {
        try {
            TransformerFactory tFact = TransformerFactory.newInstance();
            Transformer transformer = tFact.newTransformer(
                                      new StreamSource(inXSLPath));
            
            Reader inStream = new InputStreamReader(
                    new FileInputStream(inXMLPath));

            Writer outStream = new OutputStreamWriter(
                    new FileOutputStream(outHTMLPath));
            
            transformer.transform(new StreamSource(inStream),
                                  new StreamResult(outStream));
        } catch(TransformerException e){ 
            System.err.print(e);
        }
    }
}