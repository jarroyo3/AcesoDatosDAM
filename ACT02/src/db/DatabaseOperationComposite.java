package db;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import alumno.Alumno;
import historial.Historial;
import modulo.Modulo;
import profesor.Profesor;

public class DatabaseOperationComposite extends DatabaseOperation {

	private List<DatabaseOperation> operations;

	public DatabaseOperationComposite() {
		this.operations = new ArrayList<DatabaseOperation>();
	}

	public void add(DatabaseOperation databaseOperation) {
		this.operations.add(databaseOperation);
	}

	@Override
	public void set(DB database) {
		super.set(database);
		for (DatabaseOperation operation : operations) {
			operation.set(database);
		}
	}

	@Override
	public void insert(Profesor profesor) throws ClassNotFoundException, SQLException {
		for (DatabaseOperation operation : operations) {
			operation.insert(profesor);
		}
	}

	@Override
	public void insert(Modulo modulo) throws ClassNotFoundException, SQLException {
		for (DatabaseOperation operation : operations) {
			operation.insert(modulo);
		}
	}

	@Override
	public void insert(Historial historial) throws ClassNotFoundException, SQLException {
		for (DatabaseOperation operation : operations) {
			operation.insert(historial);
		}
	}

	@Override
	public void insert(Alumno alumno) throws ClassNotFoundException, SQLException {
		for (DatabaseOperation operation : operations) {
			operation.insert(alumno);
		}
	}

	@Override
	public void delete(Profesor profesor) throws ClassNotFoundException, SQLException {
		for (DatabaseOperation operation : operations) {
			operation.delete(profesor);
		}
	}

	@Override
	public void delete(Modulo modulo) throws ClassNotFoundException, SQLException {
		for (DatabaseOperation operation : operations) {
			operation.delete(modulo);
		}
	}

	@Override
	public void delete(Historial historial) throws ClassNotFoundException, SQLException {
		for (DatabaseOperation operation : operations) {
			operation.delete(historial);
		}
	}

	@Override
	public void delete(Alumno alumno) throws ClassNotFoundException, SQLException {
		for (DatabaseOperation operation : operations) {
			operation.delete(alumno);
		}
	}

}
