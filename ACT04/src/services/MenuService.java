package services;

public class MenuService {

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	private static MenuService self;

	private MenuService() {
	}

	public static MenuService getInstance() {
		if (null == self) {
			self = new MenuService();
		}
		return self;
	}

	public void imprimeInicio() {
		System.out.println("1. Acceso a opciones profesor y alumno." + ANSI_RESET);
		System.out.println("2. Historial" + ANSI_RESET);
		System.out.println("3. Salir" + ANSI_RESET);

	}

	public void imprimeOpcionesProfesorAlumno() {
		System.out.println("1. Insertar un nuevo profesor" + ANSI_RESET);
		System.out.println("2. Eliminar profesor" + ANSI_RESET);
		System.out.println("3. Validar la entrada de un profesor" + ANSI_RESET);
		System.out.println("4. Validar la entrada de un alumno" + ANSI_RESET);
		System.out.println("5. Atras" + ANSI_RESET);

	}

	public void pideNombre() {
		System.out.println();
		System.out.print(ANSI_YELLOW + "Nombre: " + ANSI_RESET);
		System.out.println();
	}

	public void pideNombreUsuario() {
		System.out.println();
		System.out.print(ANSI_YELLOW + "Nombre usuario: " + ANSI_RESET);
		System.out.println();

	}

	public void pidePass() {
		System.out.println();
		System.out.print(ANSI_YELLOW + "Contrasena: " + ANSI_RESET);
		System.out.println();

	}

	public void pideNota() {
		System.out.println();
		System.out.print(ANSI_YELLOW + "Nota: " + ANSI_RESET);
		System.out.println();
		
	}

	public void pideModulo() {
		System.out.println();
		System.out.print(ANSI_YELLOW + "Modulo: " + ANSI_RESET);
		System.out.println();
		
	}

	public void pideIdEntidad() {
		System.out.println();
		System.out.print(ANSI_YELLOW + "ID: " + ANSI_RESET);
		System.out.println();

	}

	public void log(String msg, boolean error) {
		String textColor = ANSI_GREEN;
		if (error) {
			textColor = ANSI_RED;
		}
		System.out.println(textColor + msg + ANSI_RESET);
	}

	public void imprimeOpcionesProfesor() {
		System.out.println("1. Insertar modulo" + ANSI_RESET);
		System.out.println("2. Listar TODOS los modulo" + ANSI_RESET);
		System.out.println("3. Eliminar modulo" + ANSI_RESET);
		System.out.println("4. Insertar alumno" + ANSI_RESET);
		System.out.println("5. Listar TODOS los alumnos" + ANSI_RESET);
		System.out.println("6. Listar alumno por modulo" + ANSI_RESET);
		System.out.println("7. Elimina alumno" + ANSI_RESET);
		System.out.println("8. Atras" + ANSI_RESET);

	}

	public void imprimeOpcionesAlumno() {
		System.out.println("1. Consultar notas" + ANSI_RESET);
		System.out.println("2. Listar mis modulos" + ANSI_RESET);
		System.out.println("3. Atras" + ANSI_RESET);
	}

	public void imprimeOpcionesHistorial() {
		System.out.println("1. Listar todos los eventos guardados");
		System.out.println("2. Listar los eventos por tipo");
		System.out.println("3. Salir");

	}
}
