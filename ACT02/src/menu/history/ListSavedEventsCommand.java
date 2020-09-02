package menu.history;

import java.util.List;
import java.util.stream.Collectors;

import DAO.HistorialDAO;
import historial.Historial;
import menu.Command;
import utils.IO;

public class ListSavedEventsCommand extends Command {

	public ListSavedEventsCommand() {
		super("Listar todos los eventos guardados.");
	}

	@Override
	public void execute() {
		List<Historial> h = HistorialDAO.instance().findAll();
		h.stream().map(Historial::toString).collect(Collectors.toList()).forEach(IO.instance()::writeln);
	}

}
