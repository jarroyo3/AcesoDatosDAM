package controller;

import java.util.InputMismatchException;
import java.util.Scanner;

import services.MenuService;

public class InicioController {

	private static final int OPCION_ACCESO_PROFESOR_ALUMNO = 1;
	private static final int OPCION_HISTORIAL = 2;
	private static final int OPCION_SALIR = 3;

	private static final int OPCION_INSERTA_PROFESOR = 1;
	private static final int OPCION_ELIMINA_PROFESOR = 2;
	private static final int OPCION_VALIDAR_ENTRADA_PROFESOR = 3;
	private static final int OPCION_VALIDAR_ENTRADA_ALUMNO = 4;
	private static final int OPCION_ATRAS = 5;

	private Scanner scan;
	private MenuService menuService;
	private ProfesorController profesorController;
	private AlumnoController alumnoController;
	private HistorialController historialController;

	public InicioController() {
		this.scan = new Scanner(System.in);
		this.menuService = MenuService.getInstance();
		this.profesorController = new ProfesorController();
		this.alumnoController = new AlumnoController();
		this.historialController = new HistorialController();
	}

	public void init() {
		this.menuService.imprimeInicio();
		try {
			int opcion = scan.nextInt();
			while (opcion != OPCION_SALIR) {
				switch (opcion) {
				case OPCION_ACCESO_PROFESOR_ALUMNO:
					muestraOpcionesProfesorAlumno();
					break;
				case OPCION_HISTORIAL:
					historialController.init();
					break;

				}
				this.menuService.imprimeInicio();
				opcion = scan.nextInt();
			}

		} catch (InputMismatchException ime) {
			System.out.println("La opción elegida no es válida.");
		}

	}

	private void muestraOpcionesProfesorAlumno() {
		this.menuService.imprimeOpcionesProfesorAlumno();
		try {
			int opcion = scan.nextInt();
			while (opcion != OPCION_ATRAS) {
				switch (opcion) {
				case OPCION_INSERTA_PROFESOR:
					profesorController.inserta();
					break;
				case OPCION_ELIMINA_PROFESOR:
					profesorController.elimina();
					break;
				case OPCION_VALIDAR_ENTRADA_PROFESOR:
					profesorController.valida();
					break;
				case OPCION_VALIDAR_ENTRADA_ALUMNO:
					alumnoController.valida();
					break;
				}
				this.menuService.imprimeOpcionesProfesorAlumno();
				opcion = scan.nextInt();

			}

		} catch (InputMismatchException ime) {
			System.out.println("La opción elegida no es válida.");
		}
	}
}
