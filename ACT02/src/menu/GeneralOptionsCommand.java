package menu;

import inicio.GeneralOptionsMenu;

public class GeneralOptionsCommand extends Command {

	public GeneralOptionsCommand() {
		super("Acceso a opciones profesor y alumno.");
	}

	@Override
	public void execute() {
		new GeneralOptionsMenu().execute(this.escuela);
	}

}
