package profesor;

import menu.Menu;
import menu.professor.DeleteModuleCommand;
import menu.professor.DeleteStudentCommand;
import menu.professor.InsertModuleCommand;
import menu.professor.InsertStudentCommand;
import menu.professor.ListModulesCommand;
import menu.professor.ListStudentsCommand;

public class ProfesorMenu extends Menu {

	@Override
	protected void setCommands() {
		commandList.add(new InsertModuleCommand());
		commandList.add(new ListModulesCommand());
		commandList.add(new DeleteModuleCommand());
		commandList.add(new InsertStudentCommand());
		commandList.add(new ListStudentsCommand());
		commandList.add(new DeleteStudentCommand());
	}

}
