import escuela.Escuela;
import escuela.EscuelaFactory;
import menu.Menu;

public class Main {

	private Menu menu;

	public Main() {
		this.menu = EscuelaFactory.getInstance().getMenu();
	}

	public static void main(String[] args) {
		new Main().init();
	}

	private void init() {
		Escuela escuela = EscuelaFactory.getInstance().getEscuela();
		menu.execute(escuela);
	}
}
