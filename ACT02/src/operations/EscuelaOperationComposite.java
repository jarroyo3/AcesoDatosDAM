package operations;

import java.util.ArrayList;
import java.util.List;

import alumno.Alumno;
import escuela.Escuela;
import historial.Historial;
import modulo.Modulo;
import profesor.Profesor;

public class EscuelaOperationComposite extends EscuelaOperation {

	private List<EscuelaOperation> escuelaOperationList;

	public EscuelaOperationComposite() {
		escuelaOperationList = new ArrayList<EscuelaOperation>();
	}

	public void add(EscuelaOperation escuelaOperation) {
		escuelaOperationList.add(escuelaOperation);
	}

	@Override
	public void set(Escuela escuela) {
		super.set(escuela);
		for (EscuelaOperation escuelaOperation : escuelaOperationList) {
			escuelaOperation.set(escuela);
		}
	}

	@Override
	public void visit(Alumno alumno) {
		for (EscuelaOperation escuelaOperation : escuelaOperationList) {
			escuelaOperation.visit(alumno);
		}
	}

	@Override
	public void visit(Profesor profesor) {
		for (EscuelaOperation escuelaOperation : escuelaOperationList) {
			escuelaOperation.visit(profesor);
		}
	}

	@Override
	public void visit(Modulo modulo) {
		for (EscuelaOperation escuelaOperation : escuelaOperationList) {
			escuelaOperation.visit(modulo);
		}
	}

	@Override
	public void visit(Historial historial) {
		for (EscuelaOperation escuelaOperation : escuelaOperationList) {
			escuelaOperation.visit(historial);
		}
	}

}
