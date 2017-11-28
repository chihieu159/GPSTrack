package com.example.demo.domain.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

public class XMLUtils {

    private final static Logger logger = LoggerFactory.getLogger(XMLUtils.class);

    public static Document parseXML(InputStream inputStream){
        logger.debug("[XMLUtils][parseXML]");
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputStream);
            doc.getDocumentElement().normalize();

            return doc;

        }catch(Exception ex){
            logger.error("[XMLUtils][parseXML][ex:{}]", ex);
            return null;
        }
    }

    public static String getNodeValue(Node node, String tagName){
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            return ((Element) node).getElementsByTagName(tagName)
                    .item(0)
                    .getTextContent();
        }
        return null;
    }

    public static String getNodeAttribute(Node node, String attributeName){
        return ((Element)node).getAttribute(attributeName);
    }
}
