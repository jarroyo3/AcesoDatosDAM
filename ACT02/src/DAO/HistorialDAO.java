package DAO;

import java.util.List;

import historial.Historial;
import repository.HistorialRepository;

public class HistorialDAO {

	private HistorialRepository historialRepo;

	private static HistorialDAO instance;

	private HistorialDAO() {
		this.historialRepo = HistorialRepository.instance();
	}

	public static HistorialDAO instance() {
		if (null == instance) {
			instance = new HistorialDAO();
		}

		return instance;
	}

	public Historial save(Historial historial) {
		return historialRepo.save(historial);
	}

	public List<Historial> findAll() {
		return historialRepo.findAll();
	}
}
