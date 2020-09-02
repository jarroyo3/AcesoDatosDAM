package menu;

import escuela.Escuela;

public abstract class Command {

	private String title;
	
	protected Escuela escuela;
	
	protected Command(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public void set(Escuela escuela) {
		this.escuela = escuela;
	}
	
	public abstract void execute();
}
