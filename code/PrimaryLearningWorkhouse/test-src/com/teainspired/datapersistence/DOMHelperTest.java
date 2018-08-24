package com.teainspired.datapersistence;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DOMHelperTest {

    public static final String USER_XML = "C:/Users/Douglas/IdeaProjects/PrimaryLearningApp/code/PrimaryLearningWorkhouse/xml/Users.xml";

    @Test
    void readDocumentThrowsException() {
        try {
            assertNotNull(DOMHelper.readDocument(new File(USER_XML)));
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    void getTagValue() {
        try {
            Document document = DOMHelper.readDocument(new File(USER_XML));
            Element element = (Element)document.getElementsByTagName("student").item(0);
            // test for a missing tag
            String tag1 = DOMHelper.getTagValue("bob", element);
            assertNull(tag1, "expected to be null");
            String tag2 = DOMHelper.getTagValue("username", element);
            assertEquals("Ada", tag2);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getTagValueAsInt() {
        Document document = null;
        try {
            document = DOMHelper.readDocument(new File(USER_XML));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        final Element element = (Element)document.getElementsByTagName("student").item(0);
        assertThrows(NumberFormatException.class, (()-> DOMHelper.getTagValueAsInt("username", element)));
        // test for a valid tag
        int tag1 = DOMHelper.getTagValueAsInt("primaryYear", element);
        assertEquals(5, tag1);
    }
}