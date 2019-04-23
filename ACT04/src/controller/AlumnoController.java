package controller;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import DAO.AlumnoDAO;
import models.Alumno;
import models.Historial;
import models.Modulo;
import services.HistorialService;
import services.MenuService;

public class AlumnoController {


	private static final int OPCION_CONSULTA_NOTAS = 1;
	private static final int OPCION_LISTA_MODULOS = 2;
	private static final int OPCION_ATRAS = 3;

	private MenuService menuService;
	
	private AlumnoDAO alumnoDAO;
	
	private Scanner scan;
	
	private Alumno alumno;
	
	private HistorialService historialService;
	
	public AlumnoController() {
		this.menuService = MenuService.getInstance();
		this.scan = new Scanner(System.in);
		this.alumnoDAO = new AlumnoDAO();
		this.historialService = new HistorialService();
	}
	
	
	public void consultaNotas() {
		if (this.alumno != null) {
			List<String> notas = alumnoDAO.findNotasByUsuario(this.alumno.getId());
			if (notas.isEmpty()) {
				this.menuService.log("No existen notas", false);
				return;
			}
			notas.forEach(System.out::println);
		}
	}
	
	public void listaModulosPorAlumno() {
		
	}
	
	public void listaModulos() {
		if (this.alumno != null) {
//			if (this.alumno.getModulos().isEmpty()) {
//				this.menuService.log("No existen modulo", false);
//				return;
//			}
			//List<String> nombres = alumno.getModulos().stream().map(Modulo::getNombre).collect(Collectors.toList());
			//nombres.forEach(System.out::println);
		}
	}
	
	public void valida() {
		menuService.pideNombreUsuario();
		String nombreUsuario = scan.nextLine();
		menuService.pidePass();
		String pass = scan.nextLine();
		
		if (alumnoDAO.existe(nombreUsuario, pass)) {
			this.alumno = alumnoDAO.findByUsername(nombreUsuario);
			Historial h = new Historial(null, "P", this.alumno.getNomUser(), new Date().toInstant().toString());
			this.historialService.save(h);
			renderizaMenuAlumno();
		} else {
			menuService.log("Usuario o contrasena incorrectos", true);
		}
		
	}

	private void renderizaMenuAlumno() {
		this.menuService.imprimeOpcionesAlumno();
		try {
			int opcion = scan.nextInt();
			while (opcion != OPCION_ATRAS) {
				switch (opcion) {
				case OPCION_CONSULTA_NOTAS:
					consultaNotas();
					break;
				case OPCION_LISTA_MODULOS:
					listaModulos();
					break;
				}
				this.menuService.imprimeOpcionesAlumno();
				opcion = scan.nextInt();

			}

		} catch (InputMismatchException ime) {
			System.out.println("La opcion elegida no es valida.");
		}

	}

	public void inserta() {
		menuService.pideNombre();
		String nombre = scan.nextLine();
		menuService.pideNombreUsuario();
		String nombreUsu = scan.nextLine();
		menuService.pidePass();
		String pass = scan.nextLine();
		menuService.pideNota();
		String nota = scan.nextLine();
		menuService.pideModulo();
		String modulo = scan.nextLine();

		Alumno alumno = new Alumno(null, nombre, nombreUsu, pass, modulo, nota);
		alumno = alumnoDAO.save(alumno);
		menuService.log(String.format("Anadido alumno %s  ", alumno.getNombre()), false);

	}

	public void listarTodos() {
		List<Alumno> alumnos = alumnoDAO.findAll();
		System.out.println("+-----------------   LISTADO DE ALUMNOS-------------------+");
		alumnos.forEach(alumno -> {
			System.out.println("\t" + alumno.getId() + ". " + alumno.getNombre());
		});

		System.out.println("+--------------------------------------------+");
	}

	public void elimina() {
		menuService.pideIdEntidad();
		int id = scan.nextInt();
		alumnoDAO.deleteById(id);
		menuService.log("Alumno eliminado correctamente", false);
	}
}
