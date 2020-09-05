package db;

import java.sql.Connection;
import java.sql.SQLException;

import alumno.Alumno;
import historial.Historial;
import modulo.Modulo;
import profesor.Profesor;

public abstract class DB {

	private DatabaseVisitor databaseVisitor;

	public abstract Connection connect() throws ClassNotFoundException, SQLException;

	public void set(DatabaseVisitor databaseVisitor) {
		this.databaseVisitor = databaseVisitor;
	}

	public void insert(Profesor profesor) throws ClassNotFoundException, SQLException {
		profesor.insert(databaseVisitor);
	}

	public void insert(Alumno alumno) throws ClassNotFoundException, SQLException {
		alumno.insert(databaseVisitor);
	}

	public void insert(Historial historial) throws ClassNotFoundException, SQLException {
		historial.insert(databaseVisitor);
	}

	public void insert(Modulo modulo) throws ClassNotFoundException, SQLException {
		modulo.insert(databaseVisitor);
	}

	public void delete(Profesor profesor) throws ClassNotFoundException, SQLException {
		profesor.delete(databaseVisitor);
	}

	public void delete(Alumno alumno) throws ClassNotFoundException, SQLException {
		alumno.delete(databaseVisitor);
	}

	public void delete(Historial historial) throws ClassNotFoundException, SQLException {
		historial.delete(databaseVisitor);
	}

	public void delete(Modulo modulo) throws ClassNotFoundException, SQLException {
		modulo.delete(databaseVisitor);
	}
	
	public void update(Profesor profesor) throws ClassNotFoundException, SQLException {
		profesor.update(databaseVisitor);
	}

	public void update(Alumno alumno) throws ClassNotFoundException, SQLException {
		alumno.update(databaseVisitor);
	}

	public void update(Historial historial) throws ClassNotFoundException, SQLException {
		historial.update(databaseVisitor);
	}

	public void update(Modulo modulo) throws ClassNotFoundException, SQLException {
		modulo.update(databaseVisitor);
	}

}
