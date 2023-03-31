package org.danielayanian.correlativas.services;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.danielayanian.correlativas.exceptions.NoExisteAlumnoException;
import org.danielayanian.correlativas.exceptions.NoExisteMateriaException;
import org.danielayanian.correlativas.models.Alumno;
import org.danielayanian.correlativas.models.Inscripcion;
import org.danielayanian.correlativas.models.InscripcionParseada;
import org.danielayanian.correlativas.models.Materia;

import com.opencsv.bean.CsvToBeanBuilder;

public class LectorDeArchivos {

	public String rutaArchivo;
	public List<InscripcionParseada> lineasArchivo;
	
	public LectorDeArchivos(String ruta) {
		this.rutaArchivo = ruta;
		this.leerElArchivo();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void leerElArchivo() {
		
		List<InscripcionParseada> lineasLeidas = null;
		
		try {
			//Agrego el encoding del archivo en FileReader
			lineasLeidas = new CsvToBeanBuilder(new FileReader(this.rutaArchivo, StandardCharsets.UTF_8))
					// con esta configuracion salteamos el encabezado
					.withSkipLines(1)
					// tenemos que declarar el tipo de dato que va a generar con cada linea del csv
					.withType(InscripcionParseada.class)
					.build().parse();
			
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.lineasArchivo = lineasLeidas;
	}
	
	public void listarInscripciones(List<Alumno> alumnos, List<Materia> materias){
		//POR CADA LINEA DEL CSV LEIDO TENGO QUE CREAR LA INSCRIPCION
		//PARA CREAR LA INSCRIPCION DEBO BUSCAR EL ALUMNO POR EL NOMBRE
		// Y BUSCAR LA MATERIA POR EL NOMBRE
		List<Inscripcion> inscripciones = new ArrayList<Inscripcion>();
		
		for(InscripcionParseada lineaLeida : this.lineasArchivo) {
			
			try {
				Inscripcion inscripcion = this.validarInscripcion(alumnos, materias, lineaLeida);
				this.imprimirEnPantalla(lineaLeida.getNombreAlumno(), lineaLeida.getNombreMateria(), inscripcion.aprobadaString());
			} catch (NoExisteAlumnoException e) {
				this.imprimirEnPantalla(lineaLeida.getNombreAlumno(), lineaLeida.getNombreMateria(), "No existe el/la alumno/a");
			} catch (NoExisteMateriaException e) {
				this.imprimirEnPantalla(lineaLeida.getNombreAlumno(), lineaLeida.getNombreMateria(), "No existe la materia");
			}

		}
	}
	
	public Inscripcion validarInscripcion(List<Alumno> alumnos, List<Materia> materias, InscripcionParseada lineaLeida) throws NoExisteAlumnoException, NoExisteMateriaException {
		//busqueda del alumno por el nombre de la linea del csv
		Alumno alumnoLeido = null;
		for(Alumno alumnoDeLaLista : alumnos) {
			if(alumnoDeLaLista.getNombre().equalsIgnoreCase(lineaLeida.getNombreAlumno())) {
				alumnoLeido = alumnoDeLaLista;
			}
		}		
		//busqueda de la materia por el nombre de la linea del csv
		Materia materiaLeida = null;
		for(Materia materiaDeLaLista : materias) {
			if(materiaDeLaLista.getNombre().equalsIgnoreCase(lineaLeida.getNombreMateria())){
				materiaLeida = materiaDeLaLista;
			}
		}
		
		//Agregar validacion de que la materia y el alumno exista
		if(alumnoLeido == null) {
			throw new NoExisteAlumnoException();
		}
		if(materiaLeida == null) {
			throw new NoExisteMateriaException();
		}
		
		return new Inscripcion(alumnoLeido,materiaLeida);
	}
	
	public void imprimirEnPantalla(String nombreAlumno, String nombreMateria, String resultado) {
		// Se usa el % para formatear. Se indica con -20 la cantidad de espacios de texto de separacion
		System.out.printf("%-20s%-30s%-30s\n",nombreAlumno,nombreMateria,resultado);
	}
	
}
