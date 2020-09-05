package repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import alumno.Alumno;
import db.DB;
import db.Database;
import db.DatabaseFactory;
import modulo.Modulo;

public class AlumnoRepository {

	private static AlumnoRepository instance;

	private DB database;

	private AlumnoRepository() {
		this.database = DatabaseFactory.instance().getDatabase();
	}

	public Alumno save(Alumno alumno) {

		try {
			database.insert(alumno);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			alumno = null;
		}
		return alumno;
	}

	public Alumno deleteById(int id) {
		Alumno alumno = findById(Long.valueOf(id));
		if (alumno != null) {
			Database.getInstance().delete("DELETE FROM alumnos WHERE id = " + id);
			alumno.setDeleted(true);
			return alumno;
		}
		return null;
	}

	public boolean existe(String username, String pass) {
		String query = String.format("SELECT id FROM alumnos WHERE nom_user = '%s' AND password = '%s'", username,
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

	public List<Alumno> findAll() {
		List<Alumno> alumnos = new ArrayList<>();

		String query = "SELECT * from alumnos";
		ResultSet result = Database.getInstance().executeS(query);
		try {
			while (result.next()) {
				Alumno alumno = convertirAlumnos(result);
				String queryModulos = String.format("SELECT m.id, m.nombre " + "FROM modulos m, alumno_modulos ma "
						+ "WHERE m.id =ma.id_modulo AND ma.id_alumno = %s", alumno.getId());

				ResultSet resultModulos = Database.getInstance().executeS(queryModulos);

				List<Modulo> modulos = new ArrayList<>();
				while (resultModulos.next()) {
					Long idModulo = resultModulos.getLong("id");
					String nombreModulo = resultModulos.getString("nombre");
					Modulo modulo = new Modulo(idModulo, nombreModulo);
					modulos.add(modulo);
				}

				alumno.setModulos(modulos);
				alumnos.add(alumno);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alumnos;
	}

	public Alumno findById(Long id) {
		Alumno alumno = null;

		String query = "SELECT * from alumnos WHERE id = " + id;
		ResultSet result = Database.getInstance().executeS(query);
		alumno = buscaAlumno(result);

		return alumno;
	}

	private Alumno buscaAlumno(ResultSet result) {
		Alumno alumno = null;
		try {
			while (result.next()) {
				alumno = convertirAlumnos(result);
				String queryModulos = String.format("SELECT m.id, m.nombre " + "FROM modulos m, alumno_modulos ma "
						+ "WHERE m.id =ma.id_modulo AND ma.id_alumno = %s", alumno.getId());

				ResultSet resultModulos = Database.getInstance().executeS(queryModulos);

				List<Modulo> modulos = new ArrayList<>();
				while (resultModulos.next()) {
					Long idModulo = resultModulos.getLong("id");
					String nombreModulo = resultModulos.getString("nombre");
					Modulo modulo = new Modulo(idModulo, nombreModulo);
					modulos.add(modulo);
				}

				alumno.setModulos(modulos);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alumno;
	}

	private Alumno convertirAlumnos(ResultSet result) throws SQLException {
		Long idAlumno = result.getLong("id");
		String nombreAlumno = result.getString("nombre");
		String usernameAlumno = result.getString("nom_user");

		Alumno alumno = new Alumno(idAlumno, nombreAlumno, usernameAlumno, null, null);
		return alumno;
	}

	public Alumno findByUsername(String nombreUsuario) {
		Alumno alumno = null;

		String query = String.format("SELECT * from alumnos WHERE nom_user = '%s'", nombreUsuario);
		ResultSet result = Database.getInstance().executeS(query);
		alumno = buscaAlumno(result);

		return alumno;
	}

	public List<String> findNotasByIdAlumno(Long idAlumno) {
		String queryModulos = String.format("SELECT ma.nota, m.nombre " + "FROM modulos m, alumno_modulos ma "
				+ "WHERE m.id =ma.id_modulo AND ma.id_alumno = %s", idAlumno);

		ResultSet resultNotas = Database.getInstance().executeS(queryModulos);

		List<String> notas = new ArrayList<>();
		try {
			while (resultNotas.next()) {
				String nombreModulo = resultNotas.getString("nombre");
				Double nota = resultNotas.getDouble("nota");
				String notasAlumno = nombreModulo + " " + nota;
				notas.add(notasAlumno);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return notas;
	}

	public static AlumnoRepository instance() {
		if (instance == null) {
			instance = new AlumnoRepository();
		}

		return instance;
	}
}
