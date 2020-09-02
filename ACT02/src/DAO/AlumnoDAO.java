package DAO;

import java.util.List;

import alumno.Alumno;
import repository.AlumnoRepository;

public class AlumnoDAO {

	private static AlumnoDAO instance;
	private AlumnoRepository alumnoRepo;

	private AlumnoDAO() {
		this.alumnoRepo = new AlumnoRepository();
	}

	public Alumno save(Alumno alumno) {
		return alumnoRepo.save(alumno);
	}

	public Alumno deleteById(int id) {
		return alumnoRepo.deleteById(id);

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

	public static AlumnoDAO instance() {
		if (null == instance) {
			instance = new AlumnoDAO();
		}

		return instance;
	}
}
