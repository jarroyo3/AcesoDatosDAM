package DAO;

import models.Historial;
import repository.HistorialRepo;

public class HistorialDAO {

	private HistorialRepo historialRepo;

	public HistorialDAO() {
		this.historialRepo = new HistorialRepo();
	}
	
	public Historial save(Historial historial) {
		return historialRepo.save(historial);
	}
}
