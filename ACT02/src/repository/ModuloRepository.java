package repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import db.Database;
import models.Modulo;

public class ModuloRepository {

	public Modulo save(Modulo modulo) {
		String query = String.format("INSERT INTO modulos (nombre) VALUES ('%s')", modulo.getNombre(),
				modulo.getNombre());
		int id = Database.getInstance().insert(query);
		if (id != 0) {
			modulo.setId(new Long(id));
		}
		return modulo;
	}

	public void deleteById(int id) {
		Database.getInstance().delete("DELETE FROM modulos WHERE id = " + id);
	}

	public boolean existe(String username) {
		String query = String.format("SELECT id FROM modulos WHERE nombre = '%s'", username);
		ResultSet result = Database.getInstance().executeS(query);

		try {
			return result.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public List<Modulo> findAll() {
		List<Modulo> modulos = new ArrayList<>();

		String query = "SELECT * from modulos";
		ResultSet result = Database.getInstance().executeS(query);
		try {
			while (result.next()) {
				Modulo modulo = convertirModulos(result);
				modulos.add(modulo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
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
		Database.getInstance().delete(String.format("DELETE FROM modulos WHERE nombre = '%s'", nombre));
	}
	
	public HashMap<String, List<String>> listarAlumnosPorModulo() {
		HashMap<String, List<String>> alumnosModulo = new HashMap<>();
		String query = "SELECT a.nombre as 'nombre_alumno', m.nombre as 'nombre_modulo'" + 
				"FROM alumnos a " + 
				"LEFT JOIN alumno_modulos am " + 
				"ON a.id = am.id_alumno " + 
				"LEFT JOIN modulos m " + 
				"ON m.id = am.id_modulo";
		
		ResultSet result = Database.getInstance().executeS(query);
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
