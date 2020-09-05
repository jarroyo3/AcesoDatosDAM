package repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.Database;
import db.DatabaseFactory;
import historial.Historial;

public class HistorialRepository {

	private static HistorialRepository instance;
	private DB database;

	private HistorialRepository() {
		database = DatabaseFactory.instance().getDatabase();
	}
	
	public static HistorialRepository instance() {
		if (null == instance) {
			instance = new HistorialRepository();
		}
		
		return instance;
	}
	
	public Historial save(Historial historial) {
		try {
			database.insert(historial);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			historial = null;
		}
		return historial;
	}

	public List<Historial> findAll() {
		String query = "select * from historial";
		List<Historial> historial = new ArrayList<Historial>();
		ResultSet result = Database.getInstance().executeS(query);
		try {
			while (result.next()) {
				String tipo = result.getString("tipo");
				Long idUser = result.getLong("user");
				String detalle = result.getString("detalle");
				Long id = result.getLong("id");
				Historial h = new Historial(id, tipo, idUser, detalle);
				historial.add(h);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return historial;
	}
}
