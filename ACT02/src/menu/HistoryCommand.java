package menu;

import historial.HitorialMenu;

public class HistoryCommand extends Command {

	public HistoryCommand() {
		super("Historial");
	}

	@Override
	public void execute() {
		new HitorialMenu().execute(this.escuela);

	}

}
