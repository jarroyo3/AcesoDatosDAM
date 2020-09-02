package historial;

import menu.Menu;
import menu.history.ListEventsByTypeCommand;
import menu.history.ListSavedEventsCommand;

public class HitorialMenu extends Menu {

	@Override
	public void setCommands() {
		commandList.add(new ListEventsByTypeCommand());
		commandList.add(new ListSavedEventsCommand());

	}

}
