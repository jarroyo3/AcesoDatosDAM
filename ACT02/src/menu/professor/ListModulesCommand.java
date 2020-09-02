package menu.professor;

import java.util.List;
import java.util.stream.Collectors;

import DAO.ModuloDAO;
import menu.Command;
import modulo.Modulo;
import utils.IO;

public class ListModulesCommand extends Command {

	public ListModulesCommand() {
		super("Listar TODOS los modulos.");
	}

	@Override
	public void execute() {
		List<Modulo> modules = ModuloDAO.instance().findAll();
		modules.stream().map(Modulo::getNombre).collect(Collectors.toList()).forEach(IO.instance()::writeln);
	}

}
