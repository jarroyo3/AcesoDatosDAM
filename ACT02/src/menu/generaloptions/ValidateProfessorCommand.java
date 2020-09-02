package menu.generaloptions;

import java.util.Date;

import DAO.HistorialDAO;
import DAO.ProfesorDAO;
import historial.Historial;
import menu.Command;
import profesor.Profesor;
import profesor.ProfesorMenu;
import utils.IO;

public class ValidateProfessorCommand extends Command {

	public ValidateProfessorCommand() {
		super("Validar entrada de un profesor");
	}

	@Override
	public void execute() {
		String username = IO.instance().readString("Nombre de usuario: ");
		String pass = IO.instance().readString("Contraseña: ");

		if (ProfesorDAO.instance().existe(username, pass)) {
			Profesor profesor = ProfesorDAO.instance().findByUsername(username);
			profesor.setValidated(true);
			this.escuela.setProfesor(profesor);
			Historial h = new Historial(null, "P", profesor.getId(), new Date().toInstant().toString());
			HistorialDAO.instance().save(h);
			new ProfesorMenu().execute(this.escuela);
		} else {
			IO.instance().writeln("Usuario o contraseña incorrectos");
		}

	}

}
