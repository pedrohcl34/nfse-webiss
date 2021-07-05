package br.com.sifcon.nfs.util;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class TransformXMLUtils {
	private static DocumentBuilderFactory documentFactory;
	private static TransformerFactory transformFactory;

	static {
		documentFactory = DocumentBuilderFactory.newInstance();
		documentFactory.setNamespaceAware(true);
		transformFactory = TransformerFactory.newInstance();
	}

	public static Document fromXMLToDOM(String envelopeXML)
			throws SAXException, IOException, ParserConfigurationException {
		//envelopeXML = new String(envelopeXML.getBytes("ISO-8859-1"), "UTF-8");
		ByteArrayInputStream in = new ByteArrayInputStream(envelopeXML.replaceAll("&", "&amp;").getBytes());
		return documentFactory.newDocumentBuilder().parse(in);
	}

	public static String fromDOMToXML(Document document, boolean removerCabecalho) throws TransformerException {
		StringWriter writer = new StringWriter();
		StreamResult result = new StreamResult(writer);
		Transformer transformer = transformFactory.newTransformer();
		transformer.transform(new DOMSource(document), result);
		return removerCabecalho ? writer.toString().replace("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>", "")
				: writer.toString();
	}

	public static Document readXMLFromFile(FileInputStream fileInputStream)
			throws SAXException, IOException, ParserConfigurationException {
		return documentFactory.newDocumentBuilder().parse(fileInputStream);
	}
}
