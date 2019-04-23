package services;

import java.util.List;

import models.Historial;
import repository.HistorialRepo;

public class HistorialService {

	private HistorialRepo historialRepo;

	public HistorialService() {
		this.historialRepo = new HistorialRepo();
	}

	public Historial save(Historial historial) {
		return historialRepo.save(historial);
	}
	
	public List<Historial> findAll() {
		return historialRepo.findAll();
	}

}
