package menu.generaloptions;

import DAO.ProfesorDAO;
import menu.Command;
import profesor.Profesor;
import utils.IO;

public class InsertProfessorCommand extends Command {

	public InsertProfessorCommand() {
		super("Insertar profesor.");
	}

	@Override
	public void execute() {
		String name = IO.instance().readString("Nombre:");
		IO.instance().writeln();
		String username = IO.instance().readString("Nombre usuario");
		IO.instance().writeln();
		String pass = IO.instance().readString("Contraseña");
		IO.instance().writeln();
		Profesor profesor = ProfesorDAO.instance().save(new Profesor(null, name, username, pass));
		this.escuela.setProfesor(profesor);
	}

}
