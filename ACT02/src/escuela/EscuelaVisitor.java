package escuela;

import alumno.Alumno;
import historial.Historial;
import modulo.Modulo;
import profesor.Profesor;

public interface EscuelaVisitor {

	void visit(Alumno alumno);

	void visit(Profesor profesor);

	void visit(Modulo modulo);

	void visit(Historial historial);

}
