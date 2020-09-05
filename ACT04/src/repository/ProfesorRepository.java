package repository;

import java.io.File;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import db.DatabaseCore;
import models.Profesor;

public class ProfesorRepository {

	private static final String RESOURCE_NAME = "/profesores";

	public Profesor save(Profesor profesor) {
		StringBuilder xmlQuery = new StringBuilder();
		Long id = DatabaseCore.getInstance().setResourceName(RESOURCE_NAME).Last_Insert_Id("/profesores/profesor");
		profesor.setId(id);
		xmlQuery.append("update insert ");
		xmlQuery.append("<profesor><id>%s</id><nombre>%s</nombre>");
		xmlQuery.append("<nom_user>%s</nom_user><password>%s</password>");
		xmlQuery.append("</profesor> into /profesores");
		String query = String.format(xmlQuery.toString(), profesor.getId(), profesor.getNombre(), profesor.getNomUser(),
				profesor.getPassword());

		try {
			DatabaseCore.getInstance().setResourceName(RESOURCE_NAME).insert(query);
			return profesor;
		} catch (XMLDBException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void deleteById(int id) {
		try {
			DatabaseCore.getInstance().setResourceName(RESOURCE_NAME).deleteById("/profesores/profesor", id);
		} catch (XMLDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean existe(String username, String pass) {

		StringBuilder xmlQuery = new StringBuilder();

		xmlQuery.append("for $l in //profesores/profesor let ");
		xmlQuery.append("$nom_user := $l/nom_user let $password := $l/password ");
		xmlQuery.append("where $nom_user='%s' and $password ='%s' return $l");

		String query = String.format(xmlQuery.toString(), username, pass);

		ResourceSet result;
		try {
			result = DatabaseCore.getInstance().setResourceName(RESOURCE_NAME).executeQ(query);
			return result.getSize() > 0;
		} catch (XMLDBException e1) {
			e1.printStackTrace();
			return false;
		}
	}

	public Profesor findByUsername(String nombreUsuario) {
		Profesor profesor = null;

		StringBuilder xmlQuery = new StringBuilder();

		xmlQuery.append("for $l in //profesores/profesor let ");
		xmlQuery.append("$nom_user := $l/nom_user ");
		xmlQuery.append("where $nom_user='%s' return $l");

		String query = String.format(xmlQuery.toString(), nombreUsuario);

		ResourceSet result;

		try {
			result = DatabaseCore.getInstance().setResourceName(RESOURCE_NAME).executeQ(query);

			ResourceIterator iterator = result.getIterator();
			while (iterator.hasMoreResources()) {
				XMLResource resource = (XMLResource) iterator.nextResource();
				Node node = resource.getContentAsDOM();
				NodeList nodeList = node.getFirstChild().getChildNodes();

				Long id = Long.valueOf(nodeList.item(0).getNextSibling().getTextContent());
				String nombre = nodeList.item(2).getNextSibling().getTextContent();
				String username = nodeList.item(4).getNextSibling().getTextContent();
				String pass = nodeList.item(6).getNextSibling().getTextContent();

				/*
				 * for (int i = 0; i < 11; i++) {
				 * System.out.println(nodeList.item(i).getNextSibling().getTextContent() +
				 * " ["+i+"]"); }
				 */
				profesor = new Profesor(id, nombre, username, pass);
			}

		} catch (XMLDBException e1) {
			e1.printStackTrace();
		}

		return profesor;
	}
}
