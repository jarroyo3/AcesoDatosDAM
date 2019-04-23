import controller.InicioController;
import db.DatabaseCore;

public class Main {
	
	public static void main(String[] args) {		
		//DatabaseCore.getInstance().connect();
		(new InicioController()).init();
	}
}
