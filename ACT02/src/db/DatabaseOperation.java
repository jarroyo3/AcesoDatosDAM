package db;

import java.sql.SQLException;

import alumno.Alumno;
import historial.Historial;
import modulo.Modulo;
import profesor.Profesor;

public class DatabaseOperation implements DatabaseVisitor {

	protected DB database;

	public void set(DB database) {
		this.database = database;
	}

	@Override
	public void insert(Profesor profesor) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void insert(Modulo modulo) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void insert(Historial historial) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void insert(Alumno alumno) throws ClassNotFoundException, SQLException {

	}

	@Override
	public void delete(Profesor profesor) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Modulo modulo) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Historial historial) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Alumno alumno) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Profesor profesor) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Alumno alumno) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Historial historial) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Modulo modulo) {
		// TODO Auto-generated method stub

	}

}
