package DAO;

import profesor.Profesor;
import repository.ProfesorRepository;

public class ProfesorDAO {

	private ProfesorRepository profesorRepo;

	private static ProfesorDAO instance;

	private ProfesorDAO() {
		this.profesorRepo = ProfesorRepository.instance();
	}

	public static ProfesorDAO instance() {
		if (null == instance) {
			instance = new ProfesorDAO();
		}
		return instance;
	}

	public Profesor save(Profesor profesor) {
		return profesorRepo.save(profesor);
	}

	public Profesor deleteById(int id) {
		return profesorRepo.deleteById(id);

	}

	public boolean existe(String username, String password) {
		return profesorRepo.existe(username, password);
	}

	public Profesor findByUsername(String nombreUsuario) {
		return profesorRepo.findByUsername(nombreUsuario);
	}
}
