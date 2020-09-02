package menu.history;

import menu.Command;
import utils.IO;

public class ListEventsByTypeCommand extends Command {

	public ListEventsByTypeCommand() {
		super("Listar los eventos por tipo.");
	}

	@Override
	public void execute() {
		// TODO not implemented yet
		IO.instance().writeln("No implementado!!!");
	}
}
