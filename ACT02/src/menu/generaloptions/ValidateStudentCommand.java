package menu.generaloptions;

import java.util.Date;

import DAO.AlumnoDAO;
import DAO.HistorialDAO;
import alumno.Alumno;
import alumno.AlumnoMenu;
import historial.Historial;
import menu.Command;
import utils.IO;

public class ValidateStudentCommand extends Command {

	public ValidateStudentCommand() {
		super("Validar entrada de un alumno");
	}

	@Override
	public void execute() {
		String nombreUsuario = IO.instance().readString("Nombre de usuario: ");
		String pass = IO.instance().readString("Contraseña: ");

		if (AlumnoDAO.instance().existe(nombreUsuario, pass)) {
			Alumno alumno = AlumnoDAO.instance().findByUsername(nombreUsuario);
			HistorialDAO.instance().save(new Historial(null, "P", alumno.getId(), new Date().toInstant().toString()));
			this.escuela.setAlumno(alumno);
			new AlumnoMenu().execute(this.escuela);
		} else {
			IO.instance().writeln("Usuario o contraseña incorrectos");
		}

	}

}
