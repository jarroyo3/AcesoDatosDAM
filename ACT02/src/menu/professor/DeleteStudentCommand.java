package menu.professor;

import DAO.AlumnoDAO;
import alumno.Alumno;
import menu.Command;
import utils.IO;

public class DeleteStudentCommand extends Command {

	public DeleteStudentCommand() {
		super("Eliminar alumno.");
	}

	@Override
	public void execute() {
		int id = IO.instance().readInt("ID del alumno a eliminar: ");
		Alumno alumno = AlumnoDAO.instance().deleteById(id);
		this.escuela.setAlumno(alumno);
	}

}
