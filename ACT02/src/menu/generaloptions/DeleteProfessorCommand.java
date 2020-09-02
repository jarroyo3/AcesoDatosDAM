package menu.generaloptions;

import DAO.ProfesorDAO;
import menu.Command;
import profesor.Profesor;
import utils.IO;

public class DeleteProfessorCommand extends Command {

	public DeleteProfessorCommand() {
		super("Eliminar profesor.");
	}

	@Override
	public void execute() {
		int id = IO.instance().readInt("Introduce la ID del profesor: ");
		Profesor profesor = ProfesorDAO.instance().deleteById(id);
		this.escuela.setProfesor(profesor);
	}

}
