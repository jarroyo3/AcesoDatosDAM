package profesor;

import java.sql.SQLException;

import db.DatabaseVisitor;
import escuela.EscuelaVisitor;
import sistemaescolar.SistemaEscolar;

public class Profesor extends SistemaEscolar {

	private Long id;
	private String nombre;
	private String nomUser;
	private String password;

	public Profesor() {
	}

	public Profesor(Long id, String nombre, String nomUser, String password) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.nomUser = nomUser;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNomUser() {
		return nomUser;
	}

	public void setNomUser(String nomUser) {
		this.nomUser = nomUser;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void accept(EscuelaVisitor escuelaVisitor) {
		escuelaVisitor.visit(this);
	}

	public void insert(DatabaseVisitor databaseVisitor) throws ClassNotFoundException, SQLException {
		databaseVisitor.insert(this);
	}
	
	public void delete(DatabaseVisitor databaseVisitor) throws ClassNotFoundException, SQLException {
		databaseVisitor.delete(this);
	}

	public void update(DatabaseVisitor databaseVisitor) {
		databaseVisitor.update(this);
		
	}
}
