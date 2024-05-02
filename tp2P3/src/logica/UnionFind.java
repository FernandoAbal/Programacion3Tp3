package logica;

import java.util.HashMap;
import java.util.LinkedList;

public class UnionFind {
	
	private HashMap<Persona, Integer> tamanioCompConexa;
	private int cantComponentesConexas;
	private HashMap <Persona, Persona> listaDePadres;
	
	public UnionFind(LinkedList<Persona> personas) {
		
		this.cantComponentesConexas = personas.size();
		this.tamanioCompConexa = new HashMap<Persona, Integer>();
		
		listaDePadres = new HashMap<Persona, Persona>();
		inicializarListaPadres(personas);
		inicializarListaTamanioCompConexas(personas);
		
	}
	private void inicializarListaTamanioCompConexas(LinkedList<Persona> vertices) {
		for(Persona vertice : vertices) {
			tamanioCompConexa.put(vertice, 1);
		}
	}
	private void inicializarListaPadres(LinkedList<Persona> vertices) {
	
		for(Persona vertice : vertices) {
			listaDePadres.put(vertice, null);
		}
	}
	
	public Persona find(Persona vertice) {// 
		Persona padre = vertice;
		
		
		while(listaDePadres.get(padre) != null ) {//Si es != null, no es padre
			padre = listaDePadres.get(padre);//Ahora el nuevo "padre" 
		}
		
		//PathCompression
		while(listaDePadres.get(vertice) != listaDePadres.get(padre)) {
			Persona siguiente = listaDePadres.get(vertice);
			listaDePadres.put(vertice, padre);
			vertice = siguiente;
		}
		
		return padre;
	}

	public boolean generaCiclo(Persona v1, Persona v2) {
		return find(v1).equals(find(v2));
	}
	
	public int getCantComponentes() {
		return cantComponentesConexas;
	}
	
	//Unir las componentes conexas que contiene los v1 y v2
	public void union(Persona v1, Persona v2) {
		Persona padreV1 = find(v1);
		Persona padreV2 = find(v2);
		
		int tamaniov1 = tamanioCompConexa.get(padreV1);
		int tamaniov2 = tamanioCompConexa.get(padreV2);
		
		if(tamaniov1 < tamaniov2) {
			listaDePadres.put(padreV1, padreV2); //Ahora v2 es padre de v1
			tamanioCompConexa.put(padreV2, tamaniov1+tamaniov2);
		}else {
			listaDePadres.put(padreV2, padreV1); //Ahora v1 es padre de v2
			tamanioCompConexa.put(padreV1, tamaniov1+tamaniov2);
		}
		
	}
	
	
}
