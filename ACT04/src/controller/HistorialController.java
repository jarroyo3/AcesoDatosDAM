package controller;

import java.util.List;
import java.util.Scanner;

import models.Historial;
import services.HistorialService;
import services.MenuService;

public class HistorialController {

	private static final int OPCION_LISTAR_EVENTOS = 1;
	private static final int OPCION_LISTAR_EVENTOS_TIPO = 2;
	private static final int OPCION_ATRAS = 3;

	private MenuService menuService;
	private Scanner scan;
	private HistorialService historialService;

	public HistorialController() {
		this.menuService = MenuService.getInstance();
		this.scan = new Scanner(System.in);
		this.historialService = new HistorialService();
	}

	public void listarEventos() {
		List<Historial> h = historialService.findAll();
		h.forEach(historial -> {
			System.out.println("Historial: " + historial.getTipo() + " " + historial.getDetalle() + " "
					+ historial.getIdUsuario());
		});
	}

	public void listarEventosPorTipo() {

	}

	public void init() {
		menuService.imprimeOpcionesHistorial();
		int opcion = scan.nextInt();
		while (opcion != OPCION_ATRAS) {
			switch (opcion) {
			case OPCION_LISTAR_EVENTOS:
				listarEventos();
				break;
			case OPCION_LISTAR_EVENTOS_TIPO:
				break;
			}
			menuService.imprimeOpcionesHistorial();
			opcion = scan.nextInt();
		}
	}
}
