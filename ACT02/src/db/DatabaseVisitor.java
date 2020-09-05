package db;

import java.sql.SQLException;

import alumno.Alumno;
import historial.Historial;
import modulo.Modulo;
import profesor.Profesor;

public interface DatabaseVisitor {

	public void insert(Profesor profesor) throws ClassNotFoundException, SQLException;

	public void insert(Modulo modulo) throws ClassNotFoundException, SQLException;

	public void insert(Historial historial) throws ClassNotFoundException, SQLException;

	public void insert(Alumno alumno) throws ClassNotFoundException, SQLException;

	public void delete(Profesor profesor) throws ClassNotFoundException, SQLException;
                
	public void delete(Modulo modulo) throws ClassNotFoundException, SQLException;
                
	public void delete(Historial historial) throws ClassNotFoundException, SQLException;
                
	public void delete(Alumno alumno) throws ClassNotFoundException, SQLException;

	public void update(Profesor profesor);

	public void update(Alumno alumno);

	public void update(Historial historial);

	public void update(Modulo modulo);
}
