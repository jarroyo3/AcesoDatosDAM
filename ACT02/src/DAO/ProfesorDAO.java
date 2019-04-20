package DAO;

import models.Profesor;
import repository.ProfesorRepository;

public class ProfesorDAO {

	private ProfesorRepository profesorRepo;
	
	public ProfesorDAO() {
		this.profesorRepo = new ProfesorRepository();
	}
	
	public Profesor save(Profesor profesor) {
		return profesorRepo.save(profesor);
	}

	public void deleteById(int id) {
		profesorRepo.deleteById(id);
		
	}

	public boolean existe(String username, String password) {
		return profesorRepo.existe(username, password);
	}

	public Profesor findByUsername(String nombreUsuario) {
		return profesorRepo.findByUsername(nombreUsuario);
	}
}
