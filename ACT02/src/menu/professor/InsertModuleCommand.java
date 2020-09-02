package menu.professor;

import DAO.ModuloDAO;
import menu.Command;
import modulo.Modulo;
import utils.IO;

public class InsertModuleCommand extends Command {

	public InsertModuleCommand() {
		super("Insertar módulo.");
	}

	@Override
	public void execute() {
		String nombre = IO.instance().readString("Nombre del modulo: ");
		if (!ModuloDAO.instance().existe(nombre)) {
			Modulo modulo = ModuloDAO.instance().save(new Modulo(nombre));
			this.escuela.setModulo(modulo);
		} else {
			IO.instance().writeln("El módulo ya existe");
		}

	}

}
