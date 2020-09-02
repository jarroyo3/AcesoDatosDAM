package alumno;

import menu.Menu;
import menu.student.CheckQualificationCommand;
import menu.student.ListModuleCommand;

public class AlumnoMenu extends Menu {

	@Override
	protected void setCommands() {
		commandList.add(new CheckQualificationCommand());
		commandList.add(new ListModuleCommand());
	}

}
