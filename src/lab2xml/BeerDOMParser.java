package lab2xml;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author Артем
 */
public class BeerDOMParser {
    public static List<Beer> parseDocument(String xmlPath) 
            throws ParserConfigurationException, SAXException, IOException
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        List<Beer> beerList = new ArrayList<>();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new FileInputStream(xmlPath));

        //Iterating through the nodes and extracting the data.
        NodeList nodeList = document.getDocumentElement().
                getElementsByTagName("beer");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element beerElement = (Element) nodeList.item(i);
            Beer beer = new Beer();
            beer.setId(beerElement.getAttribute("id"));
            beer.setName(beerElement.getElementsByTagName("name")
                    .item(0).getTextContent().trim());
            beer.setType(beerElement.getElementsByTagName("type")
                    .item(0).getTextContent().trim());
            beer.setAl(Boolean.parseBoolean(
                    beerElement.getElementsByTagName("al")
                    .item(0).getTextContent().trim()));
            beer.setManufacturer(beerElement
                    .getElementsByTagName("manufacturer").item(0)
                    .getTextContent().trim());
            NodeList ingredients = beerElement
                    .getElementsByTagName("ingredients");
            for (int j = 0; j < ingredients.getLength(); j++) {
                beer.getIngredients().add(ingredients.item(j)
                        .getTextContent().trim());
            }
            Element charsElement = (Element) beerElement
                    .getElementsByTagName("chars").item(0);
            beer.setChars(getCharsFromSubTree(charsElement));

            beerList.add(beer);
        }
        return beerList;
    }
    

    protected static Chars getCharsFromSubTree(Element charsElement) {
        Chars chars = new Chars();
        Node strength = charsElement.getElementsByTagName("strength").item(0);
        if (strength != null)
            chars.setStrength(Float.parseFloat(strength
                    .getTextContent().trim()));
        chars.setTransparency(Float.parseFloat(charsElement
                .getElementsByTagName("transparency").item(0)
                .getTextContent().trim()));
        chars.setFiltered(Boolean.parseBoolean(charsElement
                .getElementsByTagName("filtered").item(0)
                .getTextContent().trim()));
        chars.energy = new Chars.Energy();
        chars.energy.setValue(Float.parseFloat(charsElement
                .getElementsByTagName("energy").item(0)
                .getTextContent().trim()));
        chars.energy.setUnit(charsElement
                .getElementsByTagName("energy").item(0)
                .getAttributes().getNamedItem("unit")
                .getTextContent().trim());
        NodeList packagingList = charsElement.getElementsByTagName("packaging");
        for (int i = 0; i < packagingList.getLength(); i++) {
            Packaging packaging = new Packaging();
            Element packagingElement = (Element) packagingList.item(i);
            packaging.material = packagingElement
                    .getElementsByTagName("material").item(0)
                    .getTextContent().trim();
            packaging.volume = Float.parseFloat(packagingElement
                    .getElementsByTagName("volume").item(0)
                    .getTextContent().trim());
            chars.getPackaging().add(packaging);
        }
        return chars;
    }
}