package menu.professor;

import DAO.ModuloDAO;
import menu.Command;
import modulo.Modulo;
import utils.IO;

public class DeleteModuleCommand extends Command {

	public DeleteModuleCommand() {
		super("Eliminar modulo.");
	}

	@Override
	public void execute() {
		int id = IO.instance().readInt("Id del modulo: ");
		Modulo modulo = ModuloDAO.instance().deleteById(id);
		this.escuela.setModulo(modulo);
	}

}
