package org.danielayanian.correlativas;

import java.util.ArrayList;
import java.util.List;

import org.danielayanian.correlativas.models.Alumno;
import org.danielayanian.correlativas.models.Materia;
import org.danielayanian.correlativas.services.LectorDeArchivos;

public class App{
	
	public static void main(String[] args) {
		
		//Creo materias
		Materia algebra1 = new Materia("Álgebra I");
		Materia algebra2 = new Materia("Álgebra II");
		Materia analisisMatematico1 = new Materia("Análisis Matemático I");
		Materia analisisMatematico2 = new Materia("Análisis Matemático II");
		Materia probabilidadYEstadistica = new Materia("Probabilidad y Estadística");
		
		//Agrego las correlativas
		algebra2.agregarMateriaCorrelativa(algebra1);
		analisisMatematico2.agregarMateriaCorrelativa(analisisMatematico1);
		probabilidadYEstadistica.agregarMateriaCorrelativa(analisisMatematico2);
		probabilidadYEstadistica.agregarMateriaCorrelativa(algebra2);
		
		List<Materia> materias = new ArrayList<Materia>();
		materias.add(algebra1);
		materias.add(algebra2);
		materias.add(analisisMatematico1);
		materias.add(analisisMatematico2);
		materias.add(probabilidadYEstadistica);
		
		//Creo alumnos
		Alumno alumno1 = new Alumno("Juan López","123");
		//Agrego una materia aprobada al alumno
		alumno1.agregarMateriaAprobada(algebra1);
		
		Alumno alumno2 = new Alumno("Luis Sánchez","435");
		
		List<Alumno> alumnos = new ArrayList<Alumno>();
		alumnos.add(alumno1);
		alumnos.add(alumno2);
		
		//Leo el archivo con las inscripciones
		LectorDeArchivos archivo = new LectorDeArchivos("src/main/resources/inscripcion.csv");
		
		//Listo las inscripciones
		archivo.listarInscripciones(alumnos, materias);
				
	}
}
