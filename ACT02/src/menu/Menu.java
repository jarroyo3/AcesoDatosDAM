package menu;

import java.util.ArrayList;
import java.util.List;

import escuela.Escuela;
import utils.LimitedIntDialog;

public abstract class Menu {

	protected List<Command> commandList;
	private ExitCommand exitCommand;

	public Menu() {
		this.commandList = new ArrayList<Command>();
		this.setCommands();
		exitCommand = new ExitCommand();
		this.commandList.add(exitCommand);
	}

	protected abstract void setCommands();

	protected void set(Escuela escuela) {
		for (Command command : commandList) {
			command.set(escuela);
		}
	}

	public void execute(Escuela escuela) {
		this.set(escuela);
		exitCommand.reset();
		do {
			this.write();
			int option = this.getOption();
			commandList.get(option).execute();
			escuela.accept();
		} while (!exitCommand.isClosed());
	}

	private void write() {
		for (int i = 0; i < commandList.size(); i++) {
			System.out.println((i + 1) + ". " + commandList.get(i).getTitle());
		}
	}

	private int getOption() {
		return LimitedIntDialog.instance().read("Opción", 1, commandList.size()) - 1;
	}

	public boolean isClosed() {
		return exitCommand.isClosed();
	}
}
