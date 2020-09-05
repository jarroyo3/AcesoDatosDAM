package db.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import db.DB;
import db.DatabaseOperationComposite;
import db.mysql.operations.DeleteMySQLOperation;
import db.mysql.operations.InsertMySQLOperation;

public class MySQL extends DB {

	private static MySQL instance;

	private MySQL() {
		DatabaseOperationComposite operations = new DatabaseOperationComposite();
		operations.add(new InsertMySQLOperation());
		operations.add(new DeleteMySQLOperation());
		operations.set(this);
		super.set(operations);
	}

	public static MySQL instance() {
		if (null == instance) {
			instance = new MySQL();
		}
		return instance;
	}

	@Override
	public Connection connect() throws ClassNotFoundException, SQLException {
		Connection connection = null;
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection(
				"jdbc:mysql://192.168.1.50:3307/acceso_datos_dam_practica2?useSSL=false&useUnicode=true&characterEncoding=UTF-8&user=dam&password=dam");
		return connection;
	}
}
