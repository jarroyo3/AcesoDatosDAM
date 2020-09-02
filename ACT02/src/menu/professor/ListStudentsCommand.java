package menu.professor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import DAO.AlumnoDAO;
import alumno.Alumno;
import menu.Command;
import utils.IO;

public class ListStudentsCommand extends Command {

	private static final String PUNTO = ". ";

	public ListStudentsCommand() {
		super("Listar todos los alumnos.");
	}

	@Override
	public void execute() {
		List<Alumno> alumnos = AlumnoDAO.instance().findAll();
		alumnos.stream().flatMap(alumno -> Stream.of(String.valueOf(alumno.getId()), PUNTO.concat(alumno.getNombre())))
				.collect(Collectors.toList()).forEach(IO.instance()::writeln);

	}

}
