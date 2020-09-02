package menu.student;

import java.util.List;

import DAO.AlumnoDAO;
import menu.Command;
import utils.IO;

public class CheckQualificationCommand extends Command {

	public CheckQualificationCommand() {
		super("Consultar notas.");
	}

	@Override
	public void execute() {
		if (this.escuela.getAlumno() != null) {
			List<String> notas = AlumnoDAO.instance().findNotasByUsuario(this.escuela.getAlumno().getId());
			notas.stream().filter(nota -> !nota.isEmpty()).forEach(IO.instance()::writeln);
			if (notas.isEmpty()) {
				IO.instance().writeln("No existen notas");

			}
		}
	}

}
