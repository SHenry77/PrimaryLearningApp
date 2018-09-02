package com.teainspired.datapersistence;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class DOMHelperTest {

    private static final String USER_XML = DOMHelperTest.class.getResource("/xml/Users.xml").getPath();

    @Test
    void readDocumentThrowsException() {
        try {
            assertNotNull(DOMHelper.readDocument(new File(USER_XML)));
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception Thrown");
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
            assertEquals("Wesley", tag2);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception thrown");
        }
    }

    @Test
    void getTagValueAsInt() {
        Document document = null;
        try {
            document = DOMHelper.readDocument(new File(USER_XML));
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception Thrown");
        }
        final Element element = (Element)document.getElementsByTagName("student").item(0);
        assertThrows(NumberFormatException.class, (()-> DOMHelper.getTagValueAsInt("username", element)));
        // test for a valid tag
        int tag1 = DOMHelper.getTagValueAsInt("primaryYear", element);
        assertEquals(1, tag1);
    }
}