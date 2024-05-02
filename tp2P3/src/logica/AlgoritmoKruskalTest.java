package logica;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

public class AlgoritmoKruskalTest {

	@Test (expected=IllegalStateException.class)
	public void testGenerarAGMNull() {
		AlgoritmoKruskal algoritmoK = new AlgoritmoKruskal();
		
		algoritmoK.generarAGM(null);
	}
	
	@Test
	public void testTodasAristasMismoPeso() {
		ConjuntoPersonas listaPersonas = new ConjuntoPersonas();
		
		listaPersonas.crearPersona("A", "1", "1", "1", "1");
		listaPersonas.crearPersona("B", "1", "1", "1", "1");
		listaPersonas.crearPersona("C", "1", "1", "1", "1");
		listaPersonas.crearPersona("D", "1", "1", "1", "1");
		
		AlgoritmoKruskal algoritmoK = new AlgoritmoKruskal();
		
		algoritmoK.generarAGM(listaPersonas.getPersonas());
		
		algoritmoK.generarDosGrupos();
	
		assertEquals(1,algoritmoK.obtenerGrupo2().size());
		
		
	}
	
	@Test
	public void testInstanciaClustering() {
		
		ConjuntoPersonas listaPersonas = new ConjuntoPersonas();
		
		listaPersonas.crearPersona("A", "1", "1", "1", "1");
		listaPersonas.crearPersona("B", "2", "2", "2", "2");
		listaPersonas.crearPersona("C", "5", "5", "5", "5");
		listaPersonas.crearPersona("D", "4", "4", "4", "4");
		listaPersonas.crearPersona("E", "2", "4", "1", "3");
		listaPersonas.crearPersona("F", "1", "5", "3", "2");
		/*El AGM seria: A - B : Peso 4
		 *  B - E : Peso 4
		 *  C - D : Peso 4
		 *  B - F : Peso 5
		 *  E - D : Peso 6
		 *  Se elimina arista E - D
		 *  Quedan 2 componentes conexas
		 *  G1(C,D) y G2(A,B,E,F)
		 */ 
		
		
		AlgoritmoKruskal algoritmoK = new AlgoritmoKruskal();
		
		algoritmoK.generarAGM(listaPersonas.getPersonas());
		
		algoritmoK.generarDosGrupos();
		
		LinkedList<Persona> esperado = new LinkedList<Persona>();
		esperado.add(listaPersonas.getPersonas().get(3));
		esperado.add(listaPersonas.getPersonas().get(2));
		
		assertEquals(esperado,algoritmoK.obtenerGrupo1());
	}

}
