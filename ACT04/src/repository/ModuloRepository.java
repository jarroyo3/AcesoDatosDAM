package repository;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import db.DatabaseCore;
import models.Modulo;
import models.Profesor;

public class ModuloRepository {
	
	private static final String RESOURCE_NAME = "/modulos";

	private File collection;

	public ModuloRepository() {
		collection = new File(RESOURCE_NAME);
	}
	
	
	public Modulo save(Modulo modulo) {
		StringBuilder xmlQuery = new StringBuilder();
		Long id = DatabaseCore.getInstance().setResourceName(RESOURCE_NAME).Last_Insert_Id("/alumnos/alumno");
		modulo.setId(id);
		xmlQuery.append("update insert ");
		xmlQuery.append("<modulo><id>%s</id><nombre>%s</nombre>");
		xmlQuery.append("</modulo> into /modulos");
		String query = String.format(xmlQuery.toString(), modulo.getId(), modulo.getNombre());
		
		try {
			DatabaseCore.getInstance().setResourceName(RESOURCE_NAME).insert(query);
			return modulo;
		} catch (XMLDBException e) {
			e.printStackTrace();
			return null;
		}		
	}

	public void deleteById(int id) {
		try {
			DatabaseCore.getInstance().setResourceName(RESOURCE_NAME).deleteById("/modulos/modulo", id);
		} catch (XMLDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean existe(String nombre) {
		StringBuilder xmlQuery = new StringBuilder();

		xmlQuery.append("for $l in //modulos/modulo let ");
		xmlQuery.append("$nombre := $l/nombre ");
		xmlQuery.append("where $nombre='%s' return $l");

		String query = String.format(xmlQuery.toString(), nombre);

		ResourceSet result;
		try {
			result = DatabaseCore.getInstance().setResourceName(RESOURCE_NAME).executeQ(query);
			return result.getSize() > 0;
		} catch (XMLDBException e1) {
			e1.printStackTrace();
			return false;
		}
	}

	public List<Modulo> findAll() {
		List<Modulo> modulos = new ArrayList<>();
		String query = "for $q in /modulos/modulo return $q";
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

				Modulo modulo = new Modulo(id, nombre);
				modulos.add(modulo);
			}

		} catch (XMLDBException e1) {
			e1.printStackTrace();
		}

		return modulos;
	}

	private Modulo convertirModulos(ResultSet result) throws SQLException {
		Long idModulo = result.getLong("id");
		String nombreModulo = result.getString("nombre");

		Modulo modulo = new Modulo(idModulo, nombreModulo);
		return modulo;
	}

	public void deleteByNombre(String nombre) {
		//DatabaseCore.getInstance().delete(String.format("DELETE FROM modulos WHERE nombre = '%s'", nombre));
	}
	
	public HashMap<String, List<String>> listarAlumnosPorModulo() {
		HashMap<String, List<String>> alumnosModulo = new HashMap<>();
		String query = "";
		
		ResultSet result = DatabaseCore.getInstance().executeS(query);
		try {
			while (result.next()) {
		
				String modulo = result.getString("nombre_modulo");
				String alumno = result.getString("nombre_alumno");
				if (alumnosModulo.containsKey(modulo)) {
					alumnosModulo.get(modulo).add(alumno);
				} else {
					List<String> listaAlumno = new ArrayList<>();
					listaAlumno.add(alumno);
					alumnosModulo.put(modulo, listaAlumno);
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return alumnosModulo;
	}
}
