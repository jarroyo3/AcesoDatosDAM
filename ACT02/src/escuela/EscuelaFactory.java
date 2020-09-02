package escuela;

import alumno.Alumno;
import historial.Historial;
import inicio.InicioMenu;
import menu.Menu;
import modulo.Modulo;
import profesor.Profesor;

public class EscuelaFactory {

	private static EscuelaFactory instance;

	private EscuelaFactory() {
	}

	public static EscuelaFactory getInstance() {
		if (null == instance) {
			instance = new EscuelaFactory();
		}
		return instance;
	}

	public Escuela getEscuela() {
		return new Escuela.Builder().withAlumno(new Alumno()).withProfessor(new Profesor()).withModule(new Modulo())
				.withHistorial(new Historial()).build();
	}

	public Menu getMenu() {
		return new InicioMenu();
	}
}
