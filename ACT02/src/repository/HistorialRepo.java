package repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.Database;
import models.Historial;

public class HistorialRepo {

	public Historial save(Historial historial) {
		String query = String.format("INSERT INTO historial (tipo, user, detalle) VALUES ('%s','%s','%s')",
				historial.getTipo(), historial.getIdUsuario(), historial.getDetalle());
		int id = Database.getInstance().insert(query);
		if (id != 0) {
			historial.setId(new Long(id));
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
