package repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import db.Database;
import models.Profesor;

public class ProfesorRepository {

	public Profesor save(Profesor profesor) {
		String query = String.format("INSERT INTO profesores (nombre, nom_user, password) VALUES ('%s','%s','%s')",
				profesor.getNombre(), profesor.getNomUser(), profesor.getPassword());
		int id = Database.getInstance().insert(query);
		if (id != 0) {
			profesor.setId(new Long(id));
		}
		return profesor;
	}

	public void deleteById(int id) {
		Database.getInstance().delete("DELETE FROM profesores WHERE id = " + id);
	}
	
	public boolean existe(String username, String pass) {
		String query = String.format(
				"SELECT id FROM profesores WHERE nom_user = '%s' AND password = '%s'",
				username, pass);
		ResultSet result = Database.getInstance().executeS(query);
		
		 try {
			return result.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public Profesor findByUsername(String nombreUsuario) {
		Profesor profesor = null;
		
		String query = String.format("SELECT * from profesores WHERE nom_user = '%s'", nombreUsuario);
		ResultSet result = Database.getInstance().executeS(query);
		try {
			while (result.next()) {
				profesor = convertirProfesor(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return profesor;
	}
	
	private Profesor convertirProfesor(ResultSet result) throws SQLException {
		Long idProfesor = result.getLong("id");
		String nombreProfesor = result.getString("nombre");
		String usernameProfesor = result.getString("nom_user");
		
		Profesor profesor = new Profesor(idProfesor, nombreProfesor, usernameProfesor, null);
		return profesor;
	}
}
