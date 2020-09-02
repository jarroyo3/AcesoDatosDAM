package operations;

import alumno.Alumno;
import escuela.Escuela;
import escuela.EscuelaVisitor;
import historial.Historial;
import modulo.Modulo;
import profesor.Profesor;

public class EscuelaOperation implements EscuelaVisitor {
	
	protected Escuela escuela;
	
	public void set(Escuela escuela) {
		this.escuela = escuela;
	}
	
	@Override
	public void visit(Alumno alumno) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Profesor profesor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Modulo modulo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Historial historial) {
		// TODO Auto-generated method stub
		
	}

}
