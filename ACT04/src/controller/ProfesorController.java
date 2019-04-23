package controller;

import java.util.Date;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import DAO.HistorialDAO;
import DAO.ModuloDAO;
import DAO.ProfesorDAO;
import models.Historial;
import models.Profesor;
import services.MenuService;

public class ProfesorController {

	private static final int OPCION_INSERTA_MODULO = 1;
	private static final int OPCION_LISTAR_MODULOS = 2;
	private static final int OPCION_ELIMINA_MODULO = 3;
	private static final int OPCION_INSERTA_ALUMNO = 4;
	private static final int OPCION_LISTAR_ALUMNOS = 5;
	private static final int OPCION_LISTAR_ALUMNOS_POR_MODULO = 6;
	private static final int OPCION_ELIMINA_ALUMNO = 7;
	private static final int OPCION_ATRAS = 8;

	private MenuService menuService;

	private ProfesorDAO profesorDAO;

	private ModuloDAO moduloDAO;

	private AlumnoController alumnoController;

	private ModuloController moduloController;

	private Scanner scan;
	
	private Profesor profesor;
	private HistorialDAO historialDAO;

	public ProfesorController() {
		this.menuService = MenuService.getInstance();
		this.scan = new Scanner(System.in);
		this.profesorDAO = new ProfesorDAO();
		this.alumnoController = new AlumnoController();
		this.moduloController = new ModuloController();
		this.moduloDAO = new ModuloDAO();
		this.historialDAO = new HistorialDAO();
	}

	public void inserta() {
		menuService.pideNombre();
		String nombre = scan.nextLine();
		menuService.pideNombreUsuario();
		String nombreUsuario = scan.nextLine();
		menuService.pidePass();
		String pass = scan.nextLine();
		Profesor profesor = new Profesor(null, nombre, nombreUsuario, pass);
		profesor = profesorDAO.save(profesor);
		menuService.log(String.format("Anadido profesor %s con nombre %s ", profesor.getId(), profesor.getNombre()),
				false);
	}

	public void elimina() {
		menuService.pideIdEntidad();
		int id = scan.nextInt();
		profesorDAO.deleteById(id);
		menuService.log("Se ha eliminado el profesor correctamente", false);
	}

	public void valida() {
		menuService.pideNombreUsuario();
		String nombreUsuario = scan.nextLine();
		menuService.pidePass();
		String pass = scan.nextLine();

		if (profesorDAO.existe(nombreUsuario, pass)) {
			this.profesor = profesorDAO.findByUsername(nombreUsuario);
			Historial h = new Historial(null, "P", this.profesor.getNomUser(), new Date().toInstant().toString());
			this.historialDAO.save(h);
			renderizaMenuProfesor();
		} else {
			menuService.log("Usuario o contrasena incorrectos", true);
		}

	}

	private void renderizaMenuProfesor() {
		this.menuService.imprimeOpcionesProfesor();
		try {
			int opcion = scan.nextInt();
			while (opcion != OPCION_ATRAS) {
				switch (opcion) {
				case OPCION_INSERTA_MODULO:
					moduloController.inserta();
					break;
				case OPCION_LISTAR_MODULOS:
					moduloController.listaTodos();
					break;
				case OPCION_ELIMINA_MODULO:
					moduloController.elimina();
					break;
				case OPCION_INSERTA_ALUMNO:
					alumnoController.inserta();
					break;
				case OPCION_LISTAR_ALUMNOS:
					alumnoController.listarTodos();
					break;
				case OPCION_ELIMINA_ALUMNO:
					alumnoController.elimina();
				case OPCION_LISTAR_ALUMNOS_POR_MODULO:
					listarAlumnosPorModulo();
					break;

				}
				this.menuService.imprimeOpcionesProfesorAlumno();
				opcion = scan.nextInt();

			}

		} catch (InputMismatchException ime) {
			System.out.println("La opcion elegida no es valida.");
		}

	}

	private void listarAlumnosPorModulo() {
		HashMap<String, List<String>> alumnosModulo = moduloDAO.listarAlumnoPorModulo();
		alumnosModulo.forEach((modulo, listaAlumnos) -> {
			System.out.println("+Modulo: " + modulo);
			listaAlumnos.forEach(alumno -> {
				System.out.println("\t-" + alumno);
			});
		});
	}
}
