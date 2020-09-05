package db;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public abstract class DatabaseFactory {

	private static DatabaseFactory instance;
		
	protected DB database;
	
	public static DatabaseFactory instance() {
		if (null == instance) {
			
			String line = DatabaseFactory.getSingletonConfigure();
			try {
				instance = (DatabaseFactory) (Class
						.forName(line).newInstance());
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		return instance;
	}

	private static String getSingletonConfigure() {
		BufferedReader in = null;
		String line = "";
		try {
			in = new BufferedReader(new FileReader("databaseconf"));
			line = in.readLine().substring(12);
		} catch (IOException ex) {
			System.out.println("IOException al leer: " + ex.getMessage());
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException ex) {
					System.out.println("IOException al cerrar: "
							+ ex.getMessage());
				}
			}
		}
		return line;
	}
	
	public DB getDatabase() {
		return this.database;
	}
}
