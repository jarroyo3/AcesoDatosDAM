package repository;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import db.DatabaseCore;
import models.Historial;

public class HistorialRepo {
	
	private static final String RESOURCE_NAME = "/historial";
	
	public Historial save(Historial historial) {
		StringBuilder xmlQuery = new StringBuilder();
		Long id = DatabaseCore.getInstance().setResourceName(RESOURCE_NAME).Last_Insert_Id("/historial/evento");
		historial.setId(id);
		xmlQuery.append("update insert ");
		xmlQuery.append("<evento><id>%s</id><tipo>%s</tipo>");
		xmlQuery.append("<user>%s</user><detalle>%s</detalle>");
		xmlQuery.append("</evento> into /historial");
		String query = String.format(xmlQuery.toString(), historial.getId(), historial.getTipo(), historial.getIdUsuario(),
				historial.getDetalle());

		try {
			DatabaseCore.getInstance().setResourceName(RESOURCE_NAME).insert(query);
			return historial;
		} catch (XMLDBException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Historial> findAll() {
		String query = "for $q in /historial/evento return $q";
		List<Historial> historial = new ArrayList<Historial>();
		ResourceSet result;

		try {
			result = DatabaseCore.getInstance().setResourceName(RESOURCE_NAME).executeQ(query);

			ResourceIterator iterator = result.getIterator();
			while (iterator.hasMoreResources()) {
				XMLResource resource = (XMLResource) iterator.nextResource();
				Node node = resource.getContentAsDOM();
				NodeList nodeList = node.getFirstChild().getChildNodes();

				Long id = new Long(nodeList.item(0).getNextSibling().getTextContent());
				String tipo = nodeList.item(2).getNextSibling().getTextContent();
				String usuario = nodeList.item(4).getNextSibling().getTextContent();
				String detalle = nodeList.item(6).getNextSibling().getTextContent();

				Historial h = new Historial(id, tipo, usuario, detalle);
				historial.add(h);
			}

		} catch (XMLDBException e1) {
			e1.printStackTrace();
		}

		return historial;
	}
}
