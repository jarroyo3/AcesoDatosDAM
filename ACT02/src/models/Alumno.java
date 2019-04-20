package models;

import java.util.List;

public class Alumno {

	private Long id;
	private String nombre;
	private String nomUser;
	private String password;
	private List<Modulo> modulos;
	
	
	
	public Alumno(Long id, String nombre, String nomUser, String password, List<Modulo> modulos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.nomUser = nomUser;
		this.password = password;
		this.modulos = modulos;
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

	public List<Modulo> getModulos() {
		return modulos;
	}

	public void setModulos(List<Modulo> modulos) {
		this.modulos = modulos;
	}
	
	public void addModulo(Modulo modulo) {
		this.modulos.add(modulo);
	}
}
