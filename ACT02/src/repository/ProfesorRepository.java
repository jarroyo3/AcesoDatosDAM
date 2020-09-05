package repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import db.DB;
import db.Database;
import db.DatabaseFactory;
import profesor.Profesor;

public class ProfesorRepository {

	private static ProfesorRepository instance;
	private DB database;

	private ProfesorRepository() {
		database = DatabaseFactory.instance().getDatabase();
	}

	public static ProfesorRepository instance() {
		if (null == instance) {
			instance = new ProfesorRepository();
		}

		return instance;
	}

	public Profesor save(Profesor profesor) {
		try {
			database.insert(profesor);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			profesor = null;
		}
		return profesor;
	}

	public Profesor deleteById(int id) {
		Profesor p = findById(id);
		if (p != null) {
			try {
				database.delete(p);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		return p;
	}

	public boolean existe(String username, String pass) {
		String query = String.format("SELECT id FROM profesores WHERE nom_user = '%s' AND password = '%s'", username,
				pass);
		ResultSet result = Database.getInstance().executeS(query);

		try {
			return result.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public Profesor findById(int id) {
		Profesor profesor = null;

		String query = String.format("SELECT * from profesores WHERE id = '%s'", id);
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
