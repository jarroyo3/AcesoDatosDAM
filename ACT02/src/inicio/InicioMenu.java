package inicio;

import menu.HistoryCommand;
import menu.Menu;
import menu.GeneralOptionsCommand;

public class InicioMenu extends Menu {

	@Override
	protected void setCommands() {
		commandList.add(new GeneralOptionsCommand());
		commandList.add(new HistoryCommand());
	}

}
