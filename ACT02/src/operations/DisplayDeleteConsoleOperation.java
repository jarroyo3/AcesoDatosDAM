package operations;

import alumno.Alumno;
import historial.Historial;
import modulo.Modulo;
import profesor.Profesor;
import utils.IO;

public class DisplayDeleteConsoleOperation extends EscuelaOperation {

	@Override
	public void visit(Alumno alumno) {
		if (alumno.isDeleted()) {
			IO.instance().writeln(String.format("Alumno con ID [%s] ha sido eliminado correctamente", alumno.getId()));
		}
	}

	@Override
	public void visit(Profesor profesor) {
		if (profesor.isDeleted()) {
			IO.instance().writeln(String.format("Se ha eliminado el profesor %s con nombre %s ", profesor.getId(),
					profesor.getNombre()));
		}
	}

	@Override
	public void visit(Modulo modulo) {
		if (modulo.isDeleted()) {
			IO.instance().writeln(String.format("Eliminado modulo %s  ", modulo.getId()));
		}
	}

	@Override
	public void visit(Historial historial) {
		// TODO Auto-generated method stub
		super.visit(historial);
	}

}
