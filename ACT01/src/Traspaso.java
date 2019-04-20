import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import models.Modulos;
import models.Modulos.Modulo;
import models.Modulos.Modulo.Alumno;

public class Traspaso {

	private static final String FICHERO_NOTAS_TXT = "notas.txt";
	private static final String ESPACIO = " ";
	private static final String IGUAL = "=";

	private Modulos modulos;
	private File notasTXT;
	private BufferedReader br;

	public Traspaso() {
		this.notasTXT = new File(FICHERO_NOTAS_TXT);
		this.modulos = new Modulos();
		if (notasTXT.exists()) {
			if (notasTXT.canRead()) {
				try {
					leer();
					construirXML();
				} catch (IOException | JAXBException e) {
					e.printStackTrace();
				} finally {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	private void construirXML() throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Modulos.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		jaxbMarshaller.marshal(modulos, new File("modulos.xml"));
		jaxbMarshaller.marshal(modulos, System.out);
	}

	public static void main(String[] args) {
		new Traspaso();
	}

	private void leer() throws IOException {
		br = new BufferedReader(new FileReader(this.notasTXT));

		String linea = "";
		String siguiente = "";
		double uf1 = 0, uf2 = 0, uf3 = 0;
		Map<String, List<Alumno>> mapa = new HashMap<String, List<Alumno>>();
		while (null != (linea = br.readLine())) {
			String m = "";
			Alumno alumno = new Alumno();
			if (!"".contentEquals(siguiente)) {
				alumno.setNombre(linea);
				linea = siguiente;
			}
			if (esPrimeraLinea(linea)) {
				m = parseaLinea(linea, 1, ESPACIO);
				System.out.println("Linea ->> " + linea);
				while (null != (siguiente = br.readLine()) && !esPrimeraLinea(siguiente)) {
					System.out.println("\tSiguiente --> " + siguiente);
					if (siguiente.startsWith("UF1")) {
						uf1 = Double.parseDouble(parseaLinea(siguiente, 1, IGUAL));
						if (uf1 != 0) {
							alumno.setUF1(uf1);
						}
					}

					if (siguiente.startsWith("UF2")) {
						uf2 = Double.parseDouble(parseaLinea(siguiente, 1, IGUAL));
						if (uf2 != 0) {
							alumno.setUF2(uf2);
						}
					}

					if (siguiente.startsWith("UF3")) {
						uf3 = Double.parseDouble(parseaLinea(siguiente, 1, IGUAL));
						if (uf3 != 0) {
							alumno.setUF3(uf3);
						}
					}

					if (esNombre(siguiente)) {
						alumno.setNombre(siguiente);
					}
				}
				if (mapa.containsKey(m)) {
					mapa.get(m).add(alumno);
				} else {
					List<Alumno> listaAlumnos = new ArrayList<Modulos.Modulo.Alumno>();
					listaAlumnos.add(alumno);
					mapa.put(m, listaAlumnos);
				}
			}

		}

		List<Modulo> listaModulo = new ArrayList<Modulos.Modulo>();
		mapa.entrySet().stream().forEach(mod -> {
			Modulo modulo = new Modulo();
			modulo.setM(mod.getKey());
			List<Alumno> lista = mod.getValue();
			modulo.getAlumno().addAll(lista);
			listaModulo.add(modulo);
		});

		this.modulos.getModulo().addAll(listaModulo);
	}

	private String parseaLinea(String linea, int i, String separador) {
		String[] lineaCortada = linea.split(separador);
		return lineaCortada[i];
	}

	public boolean existeModulo(final List<Modulo> list, final String name) {
		return list.stream().filter(o -> o.getM().equals(name)).findFirst().isPresent();
	}

	private boolean esPrimeraLinea(String linea) {
		return linea.startsWith("@");
	}

	private boolean esNombre(String linea) {
		return !linea.startsWith("@") && !linea.startsWith("UF");
	}
}
