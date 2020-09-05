package modulo;

import java.sql.SQLException;

import db.DatabaseVisitor;
import escuela.EscuelaVisitor;
import sistemaescolar.SistemaEscolar;

public class Modulo extends SistemaEscolar {

	private Long id;
	private String nombre;

	public Modulo() {
	}

	public Modulo(Long id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public Modulo(String nombre) {
		super();
		this.nombre = nombre;
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
