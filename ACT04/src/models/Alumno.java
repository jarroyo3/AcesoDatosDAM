package models;

public class Alumno {

	private Long id;
	private String nombre;
	private String nomUser;
	private String password;
	private String modulo;
	private String nota;
	
	
	public Alumno(Long id, String nombre, String nomUser, String password, String modulo, String nota) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.nomUser = nomUser;
		this.password = password;
		this.modulo = modulo;
		this.nota = nota;
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

	public String getModulo() {
		return modulo;
	}

	public void setModulo(String modulo) {
		this.modulo = modulo;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}
	
	
}
