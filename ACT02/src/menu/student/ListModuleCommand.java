package menu.student;

import java.util.stream.Collectors;

import menu.Command;
import modulo.Modulo;
import utils.IO;

public class ListModuleCommand extends Command {

	public ListModuleCommand() {
		super("Listar mis modulos.");
	}

	@Override
	public void execute() {
		if (this.escuela.getAlumno() != null) {
			// TODO create two operation instead of this if/else structure
			if (this.escuela.getAlumno().getModulos().isEmpty()) {
				IO.instance().writeln("No existen modulo");
			} else {
				this.escuela.getAlumno().getModulos().stream().map(Modulo::getNombre).collect(Collectors.toList())
						.forEach(IO.instance()::writeln);
			}
		}

	}

}
