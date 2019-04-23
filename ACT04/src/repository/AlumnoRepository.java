package repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import db.DatabaseCore;
import models.Alumno;

public class AlumnoRepository {

	private static final String RESOURCE_NAME = "/alumnos";

	public Alumno save(Alumno alumno) {
		StringBuilder xmlQuery = new StringBuilder();
		Long id = DatabaseCore.getInstance().setResourceName(RESOURCE_NAME).Last_Insert_Id("/alumnos/alumno");
		alumno.setId(id);
		xmlQuery.append("update insert ");
		xmlQuery.append("<alumno><id>%s</id><nombre>%s</nombre>");
		xmlQuery.append("<nom_user>%s</nom_user><password>%s</password>");
		xmlQuery.append("<nota>%s</nota>");
		xmlQuery.append("<modulo>%s</modulo>");
		xmlQuery.append("</alumno> into /alumnos");
		String query = String.format(xmlQuery.toString(), alumno.getId(), alumno.getNombre(), alumno.getNomUser(), alumno.getPassword(),
				alumno.getNota(), alumno.getModulo());
		
		try {
			DatabaseCore.getInstance().setResourceName(RESOURCE_NAME).insert(query);
			return alumno;
		} catch (XMLDBException e) {
			e.printStackTrace();
			return null;
		}		
	}

	public void deleteById(int id) {
		try {
			DatabaseCore.getInstance().setResourceName(RESOURCE_NAME).deleteById("/alumnos/alumno", id);
		} catch (XMLDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean existe(String username, String pass) {
		StringBuilder xmlQuery = new StringBuilder();

		xmlQuery.append("for $l in //alumnos/alumno let ");
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

	public List<Alumno> findAll() {
		List<Alumno> alumnos = new ArrayList<>();

		String query = "for $q in /alumnos/alumno return $q";
		ResourceSet result;

		try {
			result = DatabaseCore.getInstance().setResourceName(RESOURCE_NAME).executeQ(query);

			ResourceIterator iterator = result.getIterator();
			while (iterator.hasMoreResources()) {
				XMLResource resource = (XMLResource) iterator.nextResource();
				Node node = resource.getContentAsDOM();
				NodeList nodeList = node.getFirstChild().getChildNodes();

				Long id = new Long(nodeList.item(0).getNextSibling().getTextContent());
				String nombre = nodeList.item(2).getNextSibling().getTextContent();
				String username = nodeList.item(4).getNextSibling().getTextContent();
				String pass = nodeList.item(6).getNextSibling().getTextContent();
				String nota = nodeList.item(8).getNextSibling().getTextContent();
				String modulo = nodeList.item(10).getNextSibling().getTextContent();

				Alumno alumno = new Alumno(id, nombre, username, pass, modulo, nota);
				alumnos.add(alumno);
			}

		} catch (XMLDBException e1) {
			e1.printStackTrace();
		}

		return alumnos;
	}

	public Alumno findById(Long id) {
		Alumno alumno = null;

		String query = "";
		ResultSet result = DatabaseCore.getInstance().executeS(query);
		alumno = buscaAlumno(result);

		return alumno;
	}

	private Alumno buscaAlumno(ResultSet result) {
		Alumno alumno = null;

		return alumno;
	}

	public Alumno findByUsername(String nombreUsuario) {
		Alumno alumno = null;

		StringBuilder xmlQuery = new StringBuilder();

		xmlQuery.append("for $l in //alumnos/alumno let ");
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

				Long id = new Long(nodeList.item(0).getNextSibling().getTextContent());
				String nombre = nodeList.item(2).getNextSibling().getTextContent();
				String username = nodeList.item(4).getNextSibling().getTextContent();
				String pass = nodeList.item(6).getNextSibling().getTextContent();
				String nota = nodeList.item(8).getNextSibling().getTextContent();
				String modulo = nodeList.item(10).getNextSibling().getTextContent();

				alumno = new Alumno(id, nombre, username, pass, modulo, nota);
				break; // me quedo solo con el primero en caso de existir mas de uno
			}

		} catch (XMLDBException e1) {
			e1.printStackTrace();
		}

		return alumno;
	}

	public List<String> findNotasByIdAlumno(Long idAlumno) {
		String queryModulos = String.format("for $l in //alumnos/alumno let $id := $l/id where $id='%s' return $l", idAlumno);

		ResultSet resultNotas = DatabaseCore.getInstance().executeS(queryModulos);

		List<String> notas = new ArrayList<>();
		ResourceSet result;

		try {
			result = DatabaseCore.getInstance().setResourceName(RESOURCE_NAME).executeQ(queryModulos);

			ResourceIterator iterator = result.getIterator();
			while (iterator.hasMoreResources()) {
				XMLResource resource = (XMLResource) iterator.nextResource();
				Node node = resource.getContentAsDOM();
				NodeList nodeList = node.getFirstChild().getChildNodes();
				
				String nota = nodeList.item(8).getNextSibling().getTextContent();
				String modulo = nodeList.item(10).getNextSibling().getTextContent();

				notas.add(nota);
			}

		} catch (XMLDBException e1) {
			e1.printStackTrace();
		}
		return notas;
	}
}
