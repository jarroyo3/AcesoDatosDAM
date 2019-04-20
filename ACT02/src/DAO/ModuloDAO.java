package DAO;

import java.util.HashMap;
import java.util.List;

import models.Modulo;
import repository.ModuloRepository;

public class ModuloDAO {

	private ModuloRepository moduloRepo;

	public ModuloDAO() {
		this.moduloRepo = new ModuloRepository();
	}
	
	public Modulo save(Modulo modulo) {
		return moduloRepo.save(modulo);
	}

	public void deleteById(int id) {
		moduloRepo.deleteById(id);
		
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
