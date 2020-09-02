package operations;

import alumno.Alumno;
import historial.Historial;
import modulo.Modulo;
import profesor.Profesor;
import utils.IO;

public class DisplayInsertConsoleOperation extends EscuelaOperation {

	@Override
	public void visit(Alumno alumno) {
		if (alumno.isCreated()) {
			IO.instance().writeln(String.format("Añadido alumno %s  ", alumno.getNombre()));
		}
	}

	@Override
	public void visit(Profesor profesor) {
		if (profesor.isCreated()) {
			IO.instance().writeln(
					String.format("Añadido profesor %s con nombre %s ", profesor.getId(), profesor.getNombre()));

		}

	}

	@Override
	public void visit(Modulo modulo) {
		if (modulo.isCreated()) {
			IO.instance().writeln(String.format("Añadido modulo %s  con ID %s ", modulo.getNombre(), modulo.getId()));
		}
	}

	@Override
	public void visit(Historial historial) {
		// TODO Auto-generated method stub
		super.visit(historial);
	}

}
