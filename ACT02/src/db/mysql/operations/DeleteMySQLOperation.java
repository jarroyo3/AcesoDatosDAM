package db.mysql.operations;

import java.sql.SQLException;

import alumno.Alumno;
import db.DatabaseOperation;
import historial.Historial;
import modulo.Modulo;
import profesor.Profesor;

public class DeleteMySQLOperation extends DatabaseOperation implements UpdateOrDeleteOperation {

	@Override
	public void delete(Profesor profesor) throws SQLException, ClassNotFoundException {
		executeUpdateOrDelete(this.database.connect(), String.format("DELETE FROM profesores WHERE id = %s", profesor.getId()));
	}

	@Override
	public void delete(Modulo modulo) throws SQLException, ClassNotFoundException {
		executeUpdateOrDelete(this.database.connect(), String.format("DELETE FROM modulos WHERE id = %s", modulo.getId()));
	}

	@Override
	public void delete(Historial historial) throws SQLException, ClassNotFoundException {
		executeUpdateOrDelete(this.database.connect(), String.format("DELETE FROM historial WHERE id = %s", historial.getId()));
	}

	@Override
	public void delete(Alumno alumno) throws SQLException, ClassNotFoundException {
		executeUpdateOrDelete(this.database.connect(), String.format("DELETE FROM profesores WHERE id = %s", alumno.getId()));
	}

	
}
