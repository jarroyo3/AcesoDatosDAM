import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class Buscador {

	private static final String MODULOS_XML = "modulos.xml";
	public Buscador() {
		new File(MODULOS_XML);
		try {
			cargarXML();
		} catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void cargarXML() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder;
		Document doc = null;

		builder = factory.newDocumentBuilder();
		doc = builder.parse(MODULOS_XML);

		XPathFactory xpathFactory = XPathFactory.newInstance();

		XPath xpath = xpathFactory.newXPath();

		XPathExpression expr = xpath.compile("//*[text()[contains(.,'Steve Rogers')]]");
		Node n = (Node) expr.evaluate(doc, XPathConstants.NODE);
		Node p = n.getParentNode();

		System.out.println(String.format("En el modulo %s el alumno Steve Rogers ha sacado las siguientes notas %s ",
				p.getParentNode().getAttributes().getNamedItem("m"), p.getTextContent()));

	}

	public static void main(String[] args) {
		new Buscador();
	}
}
