package logica;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

public class UnionFindTest {

	@Test
	public void testPadreCorrecto() {
		
		ConjuntoPersonas listaPersonas = new ConjuntoPersonas();
		
		listaPersonas.crearPersona("A", "1", "1", "1", "1");
		listaPersonas.crearPersona("B", "2", "2", "2", "2");
		listaPersonas.crearPersona("C", "4", "4", "4", "4");
		listaPersonas.crearPersona("D", "5", "5", "5", "5");
		
		UnionFind uf = new UnionFind(listaPersonas.getPersonas());
		
		LinkedList<Arco> arcos = new LinkedList<Arco>();
		
		arcos.add(new Arco(listaPersonas.getPersonas().get(0), listaPersonas.getPersonas().get(1), 4));
		//A - B
		arcos.add(new Arco(listaPersonas.getPersonas().get(2), listaPersonas.getPersonas().get(3), 4));
		//C - D
		arcos.add(new Arco(listaPersonas.getPersonas().get(1), listaPersonas.getPersonas().get(2), 8));
		//B - C
	
		for(Arco a : arcos) {
			
			/*A padre de B
			 * C padre de D
			 * A padre de C
			 * */
			
			Persona p1 = uf.find(a.getOrigen());
			Persona p2 = uf.find(a.getDestino());
			
			if(!uf.generaCiclo(p1, p2)) {
				uf.union(p1, p2);
			}
			
		}
		
		Persona padreEsperado = listaPersonas.getPersonas().get(0);
		
		assertEquals(padreEsperado, uf.find(listaPersonas.getPersonas().get(2)));//Padre de C es A, por PathCompression
		assertEquals(padreEsperado, uf.find(listaPersonas.getPersonas().get(1)));//Padre de B es A
		assertEquals(padreEsperado, uf.find(listaPersonas.getPersonas().get(0)));//Padre de A es A
		
	}
	
	@Test
	public void testVerificarCiclo() {
		
		ConjuntoPersonas listaPersonas = new ConjuntoPersonas();
		
		listaPersonas.crearPersona("A", "1", "1", "1", "1");
		listaPersonas.crearPersona("B", "2", "2", "2", "2");
		listaPersonas.crearPersona("C", "4", "4", "4", "4");
		
		UnionFind uf = new UnionFind(listaPersonas.getPersonas());
		
		LinkedList<Arco> arcos = new LinkedList<Arco>();
		
		arcos.add(new Arco(listaPersonas.getPersonas().get(0), listaPersonas.getPersonas().get(1), 4));
		//A - B
		arcos.add(new Arco(listaPersonas.getPersonas().get(1), listaPersonas.getPersonas().get(2), 8));
		//B - C
		arcos.add(new Arco(listaPersonas.getPersonas().get(0), listaPersonas.getPersonas().get(2), 12));
		//A - C
		for(Arco a : arcos) {	
			Persona p1 = uf.find(a.getOrigen());
			Persona p2 = uf.find(a.getDestino());
			
			if(!uf.generaCiclo(p1, p2)) {
				uf.union(p1, p2);
			}
			
		}
		
		Arco arcoGeneraCiclo = arcos.getLast();
		
		assertTrue(uf.generaCiclo(arcoGeneraCiclo.getOrigen(), arcoGeneraCiclo.getDestino()));
		
	}
	
	

}
