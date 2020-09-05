package db.mysql.factory;

import db.DatabaseFactory;
import db.mysql.MySQL;

public class MySQLFactory extends DatabaseFactory{

	public MySQLFactory() {
			this.database = MySQL.instance();
	}
}
