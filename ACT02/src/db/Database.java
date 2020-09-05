package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// TODO refactor this class
public class Database {

	protected static Connection conexion;
	
	private static Database instance;
	
	private Database() {
		this.connect();
	}
	
	public static Database getInstance() {
		if (null == instance) {
			instance = new Database();
		}
		return instance;
	}

	public void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conexion = DriverManager.getConnection(
					"jdbc:mysql://192.168.1.50:3307/acceso_datos_dam_practica2?useSSL=false&useUnicode=true&characterEncoding=UTF-8&user=dam&password=dam");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ResultSet executeS(String queryS) {
		ResultSet rs = null;
		try {
			Statement st = conexion.createStatement();
			String query = queryS;
			rs = st.executeQuery(query);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	public void update(String query) {
		try {
			Statement st = conexion.createStatement();
			st.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete(String query) {
		try {
			this.update(query);
		} catch (Exception e) {

		}
	}

	public int insert(String query) {
		int id = 0;
		try {
			Statement st = conexion.createStatement();
			id = st.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = st.getGeneratedKeys();
			if (rs != null && rs.next()) {
				id = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}
}
