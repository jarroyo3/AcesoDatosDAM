package escuela;

import alumno.Alumno;
import historial.Historial;
import modulo.Modulo;
import operations.DisplayDeleteConsoleOperation;
import operations.DisplayInsertConsoleOperation;
import operations.EscuelaOperationComposite;
import profesor.Profesor;

public class Escuela {

	private Alumno alumno;

	private Profesor profesor;

	private Modulo modulo;

	private Historial historial;

	private EscuelaVisitor escuelaVisitor;

	private Escuela() {
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}

	public void setHistorial(Historial historial) {
		this.historial = historial;
	}

	public void setEscuelaVisitor(EscuelaVisitor escuelaVisitor) {
		this.escuelaVisitor = escuelaVisitor;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public Modulo getModulo() {
		return modulo;
	}

	public Historial getHistorial() {
		return historial;
	}

	public void accept() {
		profesor.accept(escuelaVisitor);
		alumno.accept(escuelaVisitor);
		modulo.accept(escuelaVisitor);
		historial.accept(escuelaVisitor);
	}

	public static class Builder {

		private Alumno alumno;
		private Profesor profesor;
		private Modulo modulo;
		private Historial historial;

		public Builder withAlumno(Alumno alumno) {
			this.alumno = alumno;
			return this;
		}

		public Builder withProfessor(Profesor profesor) {
			this.profesor = profesor;
			return this;
		}

		public Builder withModule(Modulo modulo) {
			this.modulo = modulo;
			return this;
		}

		public Builder withHistorial(Historial historial) {
			this.historial = historial;
			return this;
		}

		public Escuela build() {
			Escuela escuela = new Escuela();
			escuela.alumno = this.alumno;
			escuela.profesor = this.profesor;
			escuela.modulo = this.modulo;
			escuela.historial = this.historial;
			EscuelaOperationComposite escuelaComposite = new EscuelaOperationComposite();
			escuelaComposite.add(new DisplayInsertConsoleOperation());
			escuelaComposite.add(new DisplayDeleteConsoleOperation());
			escuela.escuelaVisitor = escuelaComposite;
			return escuela;
		}
	}

}
