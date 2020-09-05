package db.mysql.operations;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public interface UpdateOrDeleteOperation {

	default void executeUpdateOrDelete(Connection conn, String query) throws SQLException {
		Statement st = conn.createStatement();
		st.executeUpdate(query);
	}
}
