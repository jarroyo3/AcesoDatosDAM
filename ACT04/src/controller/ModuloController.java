package controller;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import DAO.ModuloDAO;
import models.Modulo;
import services.MenuService;

public class ModuloController {

	private MenuService menuService;
	private Scanner scan;
	private ModuloDAO moduloDAO;

	public ModuloController() {
		this.menuService = MenuService.getInstance();
		this.scan = new Scanner(System.in);
		this.moduloDAO = new ModuloDAO();
	}

	public void inserta() {
		menuService.pideNombre();
		String nombre = scan.nextLine();
		Modulo modulo = new Modulo(nombre);
		if (!moduloDAO.existe(nombre)) {
			modulo = moduloDAO.save(modulo);
			menuService.log(String.format("Anadido modulo %s  ", modulo.getNombre()), false);
		} else {
			menuService.log("El modulo ya existe", true);
		}
	}

	public void listaTodos() {
		List<Modulo> modulos = moduloDAO.findAll();
		List<String> nombres = modulos.stream().map(Modulo::getNombre).collect(Collectors.toList());
		nombres.forEach(System.out::println);
	}

	public void elimina() {
		menuService.pideNombre();
		String nombre = scan.nextLine();
		moduloDAO.deleteByNombre(nombre);
		menuService.log(String.format("Eliminado modulo %s  ", nombre), false);
	}

}
