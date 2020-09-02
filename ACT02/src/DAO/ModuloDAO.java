package DAO;

import java.util.HashMap;
import java.util.List;

import modulo.Modulo;
import repository.ModuloRepository;

public class ModuloDAO {

	private ModuloRepository moduloRepo;

	private static ModuloDAO instance;

	private ModuloDAO() {
		this.moduloRepo = new ModuloRepository();
	}

	public static ModuloDAO instance() {
		if (null == instance) {
			instance = new ModuloDAO();
		}

		return instance;
	}

	public Modulo save(Modulo modulo) {
		return moduloRepo.save(modulo);
	}

	public Modulo deleteById(int id) {
		return moduloRepo.deleteById(id);

	}

	public boolean existe(String nombre) {
		return moduloRepo.existe(nombre);
	}

	public List<Modulo> findAll() {
		return moduloRepo.findAll();
	}

	public void deleteByNombre(String nombre) {
		moduloRepo.deleteByNombre(nombre);

	}

	public HashMap<String, List<String>> listarAlumnoPorModulo() {
		return moduloRepo.listarAlumnosPorModulo();
	}
}
