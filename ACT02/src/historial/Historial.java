package historial;

import escuela.EscuelaVisitor;

public class Historial {

	private Long id;
	private String tipo;
	private Long idUsuario;
	private String detalle;

	public Historial() {
	}

	public Historial(Long id, String tipo, Long idUsuario, String detalle) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.idUsuario = idUsuario;
		this.detalle = detalle;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public void accept(EscuelaVisitor escuelaVisitor) {
		escuelaVisitor.visit(this);

	}

	@Override
	public String toString() {
		return "Historial [tipo=" + tipo + ", idUsuario=" + idUsuario + ", detalle=" + detalle + "]";
	}
}
