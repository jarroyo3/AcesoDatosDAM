package menu.professor;

import DAO.AlumnoDAO;
import alumno.Alumno;
import menu.Command;
import utils.IO;

public class InsertStudentCommand extends Command {

	public InsertStudentCommand() {
		super("Insertar un alumno.");
	}

	@Override
	public void execute() {
		String nombre = IO.instance().readString("Nombre: ");
		String nombreUsu = IO.instance().readString("Nombre de usuario: ");
		String pass = IO.instance().readString("Contraseña: ");

		Alumno alumno = AlumnoDAO.instance().save(new Alumno(null, nombre, nombreUsu, pass, null));
		this.escuela.setAlumno(alumno);

	}

}
