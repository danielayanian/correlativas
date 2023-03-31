package org.danielayanian.correlativas;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.danielayanian.correlativas.models.Alumno;
import org.danielayanian.correlativas.models.Inscripcion;
import org.danielayanian.correlativas.models.Materia;
import org.junit.Test;

public class InscripcionTest {

	//a. Una materia sin correlativas
	@Test
	public void validarInscripcionDeMateriaSinCorrelativas() {
		
		//Creo materias
		Materia algebra1 = new Materia("Álgebra I");
		Materia algebra2 = new Materia("Álgebra II");
		Materia analisisMatematico1 = new Materia("Análisis Matemático I");
		Materia analisisMatematico2 = new Materia("Análisis Matemático II");
		Materia probabilidadYEstadistica = new Materia("Probabilidad y Estadística");
		
		//Agregamos una materia correlativa
		algebra2.agregarMateriaCorrelativa(algebra1);
		
		//Creamos un alumno
		Alumno alumno = new Alumno("Juan López","123");
		
		//Creamos una inscripción SIN CORRELATIVAS
		Inscripcion inscripcion = new Inscripcion(alumno, algebra1);
		
		assertTrue(inscripcion.aprobada());
	}
	
	
	//b. Una materia con correlativas y que el alumno las tenga
	@Test
	public void validarInscripcionDeMateriaConCorrelativasYQueElAlumnoLasTenga() {
		
		//Creo materias
		Materia algebra1 = new Materia("Álgebra I");
		Materia algebra2 = new Materia("Álgebra II");
		Materia analisisMatematico1 = new Materia("Análisis Matemático I");
		Materia analisisMatematico2 = new Materia("Análisis Matemático II");
		Materia probabilidadYEstadistica = new Materia("Probabilidad y Estadística");
		
		//Agregamos una materia correlativa
		algebra2.agregarMateriaCorrelativa(algebra1);
		
		//Creamos un alumno
		Alumno alumno = new Alumno("Juan López","123");
		
		alumno.agregarMateriaAprobada(algebra1);

		//Creamos una inscripcion CON CORRELATIVAS APROBADAS
		Inscripcion inscripcion = new Inscripcion(alumno, algebra2);
		
		assertTrue(inscripcion.aprobada());
	}
	
	//c. Otra materia con correlativas, y que el alumno no las tenga
	@Test
	public void validarInscripcionDeMateriaConCorrelativasYQueElAlumnoNoLasTenga() {
		
		//Creo materias
		Materia algebra1 = new Materia("Álgebra I");
		Materia algebra2 = new Materia("Álgebra II");
		Materia analisisMatematico1 = new Materia("Análisis Matemático I");
		Materia analisisMatematico2 = new Materia("Análisis Matemático II");
		Materia probabilidadYEstadistica = new Materia("Probabilidad y Estadística");
		
		//Agregamos una materia correlativa
		algebra2.agregarMateriaCorrelativa(algebra1);
		
		//Creamos un alumno
		Alumno alumno = new Alumno("Juan López","123");

		//Creamos una inscripción SIN CORRELATIVAS
		Inscripcion inscripcion = new Inscripcion(alumno, algebra2);
		
		assertFalse(inscripcion.aprobada());
	}
		
	
	
}
