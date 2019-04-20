package DAO;

import java.util.List;

import models.Alumno;
import repository.AlumnoRepository;

public class AlumnoDAO {

private AlumnoRepository alumnoRepo;
	
	public AlumnoDAO() {
		this.alumnoRepo = new AlumnoRepository();
	}
	
	public Alumno save(Alumno alumno) {
		return alumnoRepo.save(alumno);
	}

	public void deleteById(int id) {
		alumnoRepo.deleteById(id);
		
	}

	public boolean existe(String username, String password) {
		return alumnoRepo.existe(username, password);
	}

	public List<Alumno> findAll() {
		return alumnoRepo.findAll();
	}
	
	public Alumno findById(Long id) {
		return alumnoRepo.findById(id);
	}

	public Alumno findByUsername(String nombreUsuario) {
		return alumnoRepo.findByUsername(nombreUsuario);
	}
	
	public List<String> findNotasByUsuario(Long idUsuario) {
		return alumnoRepo.findNotasByIdAlumno(idUsuario);
	}
}
