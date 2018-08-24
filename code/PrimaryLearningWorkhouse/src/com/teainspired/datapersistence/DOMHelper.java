package com.teainspired.datapersistence;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class DOMHelper {

    public static Document readDocument(File xmlFile) throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document doc = builder.parse(xmlFile);
        doc.getDocumentElement().normalize();
        return doc;
    }

    /*
     * returns the value of the first tag of the given element
     */
    public static String getTagValue(String tag, Element element){
        String result = null;

        NodeList nodeList = element.getElementsByTagName(tag);
        if(nodeList.getLength() > 0 ) {
            NodeList childList = nodeList.item(0).getChildNodes();
            Node node = childList.item(0);
            result = node.getNodeValue();
        }
        return result;
    }

    public static int getTagValueAsInt(String tag, Element element) throws NumberFormatException{
        return Integer.parseInt(getTagValue(tag, element));
    }
}
