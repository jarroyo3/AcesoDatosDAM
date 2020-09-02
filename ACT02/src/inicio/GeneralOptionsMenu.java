package inicio;

import menu.Menu;
import menu.generaloptions.DeleteProfessorCommand;
import menu.generaloptions.InsertProfessorCommand;
import menu.generaloptions.ValidateProfessorCommand;
import menu.generaloptions.ValidateStudentCommand;

public class GeneralOptionsMenu extends Menu {

	@Override
	protected void setCommands() {
		commandList.add(new InsertProfessorCommand());
		commandList.add(new DeleteProfessorCommand());
		commandList.add(new ValidateProfessorCommand());
		commandList.add(new ValidateStudentCommand());
	}

}
