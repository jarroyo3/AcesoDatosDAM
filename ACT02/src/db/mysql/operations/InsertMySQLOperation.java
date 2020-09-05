package db.mysql.operations;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import alumno.Alumno;
import db.DatabaseOperation;
import historial.Historial;
import modulo.Modulo;
import profesor.Profesor;

public class InsertMySQLOperation extends DatabaseOperation {

	@Override
	public void insert(Alumno alumno) throws ClassNotFoundException, SQLException {

		String query = String.format("INSERT INTO alumnos (nombre, nom_user, password) VALUES ('%s','%s','%s')",
				alumno.getNombre(), alumno.getNomUser(), alumno.getPassword());

		int id = this.executeInsert(query);
		
		if (id > 0) {
			alumno.setId(Long.valueOf(id));
		}
	}

	@Override
	public void insert(Profesor profesor) throws ClassNotFoundException, SQLException {

		String query = String.format("INSERT INTO profesores (nombre, nom_user, password) VALUES ('%s','%s','%s')",
				profesor.getNombre(), profesor.getNomUser(), profesor.getPassword());

		this.executeInsert(query);

	}

	@Override
	public void insert(Modulo modulo) throws ClassNotFoundException, SQLException {
		String query = String.format("INSERT INTO modulos (nombre) VALUES ('%s')", modulo.getNombre(),
				modulo.getNombre());

		this.executeInsert(query);

	}

	@Override
	public void insert(Historial historial) throws ClassNotFoundException, SQLException {

		String query = String.format("INSERT INTO historial (tipo, user, detalle) VALUES ('%s','%s','%s')",
				historial.getTipo(), historial.getIdUsuario(), historial.getDetalle());

		this.executeInsert(query);

	}

	private int executeInsert(String pQuery) throws ClassNotFoundException, SQLException {
		Connection conn = this.database.connect();
		int id = 0;
		String query = pQuery;
		Statement st = conn.createStatement();
		id = st.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
		ResultSet rs = st.getGeneratedKeys();

		if (rs != null && rs.next()) {
			id = rs.getInt(1);
		}
		return id;
	}
}
