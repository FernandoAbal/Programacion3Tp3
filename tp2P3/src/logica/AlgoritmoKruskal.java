package logica;

import java.util.Collections;
import java.util.LinkedList;

public class AlgoritmoKruskal {

	private Integer[][] indicesSimilaridadPersonas;
	private int tamanioGrafo;
	private LinkedList<Arco> arcos;
	private LinkedList<Persona> personas;
	private LinkedList<Arco> agmSolucion;
	private LinkedList<Persona> grupo1;
	private LinkedList<Persona> grupo2;

	public AlgoritmoKruskal() {
		this.arcos = new LinkedList<Arco>();
		this.agmSolucion = new LinkedList<Arco>();
		this.grupo1 = new LinkedList<Persona>();
		this.grupo2 = new LinkedList<Persona>();
	}

	private void generarMatrizIndiceSimilaridad(LinkedList<Persona> personas) { //
		this.tamanioGrafo = personas.size();// Tamaño grafo es el tamaño de la linkedlist de personas

		inicializarMatrizIndiceSimilaridad(tamanioGrafo); // Le damos el tamaño para crear matriz

		for (int i = 0; i < tamanioGrafo; i++) {
			for (int j = 0; j < tamanioGrafo; j++) {
				if (i == j) {
					indicesSimilaridadPersonas[i][j] = -1; // Kruskal no admite negativos
				} else {// Agregamos la similaridad entre dos personas
					indicesSimilaridadPersonas[i][j] = calcularIndiceSimilaridadEntre(personas.get(i), personas.get(j));
				}
			}
		}
		
		
	}

	private void inicializarMatrizIndiceSimilaridad(int tamanioGrafo) {
		indicesSimilaridadPersonas = new Integer[tamanioGrafo][tamanioGrafo];
	}

	public int calcularIndiceSimilaridadEntre(Persona p1, Persona p2) { // Calculo indice de similaridad entre 2 															// personas
		int indice = 0;

		indice = Math.abs(p1.getInteresCiencia() - p2.getInteresCiencia())
				+ Math.abs(p1.getInteresDeporte() - p2.getInteresDeporte())
				+ Math.abs(p1.getInteresEspectaculo() - p2.getInteresEspectaculo())
				+ Math.abs(p1.getInteresMusica() - p2.getInteresMusica());

		return indice;
	}

	private int obtenerSimilaridad(int i, int j) {
		return indicesSimilaridadPersonas[i][j];
	}

	private boolean existeArco(Arco a) {
		return arcos.contains(a);
	}

	private void cargarListaDeArcos() {
		for (int i = 0; i < tamanioGrafo; i++) {
			for (int j = 0; j < tamanioGrafo; j++) {
				if (indicesSimilaridadPersonas[i][j] >= 0) {
					Arco arco = new Arco(personas.get(i), personas.get(j), obtenerSimilaridad(i, j));
					if (!existeArco(arco)) {
						arcos.add(arco);
					}
				}
			}
		}
	}

	private void ordenarListaDeArcos() {
		Collections.sort(arcos); // Ordena la LinkedList de menor a mayor segun el atributo peso
	}
	
	private void limpiarListas() {
		if(personas != null && !personas.isEmpty())
			personas.clear();
		grupo1.clear();
		grupo2.clear();
		agmSolucion.clear();
		arcos.clear();
	}

	private void preparacionesParaGenerarAGM() {
		generarMatrizIndiceSimilaridad(personas);
		cargarListaDeArcos();
		ordenarListaDeArcos();
	}
	
	@SuppressWarnings("unchecked")
	private void cargarListaPersonas(LinkedList<Persona> lista) {
		LinkedList<Persona> personas = (LinkedList<Persona>) lista.clone();
		this.personas = personas;
	}

	public void generarAGM(LinkedList<Persona> listaPersonas) {
		if (listaPersonas == null || listaPersonas.isEmpty()) {
			throw new IllegalStateException("Deben existir personas para clusterizar.");
		}
		limpiarListas();
		
		cargarListaPersonas(listaPersonas);

		preparacionesParaGenerarAGM();

		UnionFind uf = new UnionFind(personas);

		int arcoAgregadosAGM = 0; // arcos agregados que son parte del AGM
		int i = 0;// Indice para tomar un arco de la linkedlist de arcos
		int vertices = personas.size();// Cant de vertices que forman parte del AGM
		
		while (arcoAgregadosAGM < vertices - 1) {
			Arco sigArco = arcos.get(i);
			i++;

			Persona p1 = uf.find(sigArco.getOrigen());
			Persona p2 = uf.find(sigArco.getDestino());

			if (!uf.generaCiclo(p1, p2)) {
				agmSolucion.add(sigArco);
				arcoAgregadosAGM++;
				uf.union(p1, p2);
			}
		}
		
		
	}

	public LinkedList<Arco> getAGMSolucion() {
		return this.agmSolucion;
	}

	private Arco eliminarAristaAGM() {
		Arco arcoEliminado = agmSolucion.getLast();
		agmSolucion.removeLast();

		return arcoEliminado;
	}

	public void generarDosGrupos() {
			Arco arcoEliminado = eliminarAristaAGM();

			grupo1.add(arcoEliminado.getOrigen());

			grupo2.add(arcoEliminado.getDestino());

			while (grupo1.size() + grupo2.size() < personas.size()) {

				for (Arco arcoAGM : agmSolucion) {
					if (grupo1.contains(arcoAGM.getOrigen()) && !grupo1.contains(arcoAGM.getDestino())) {
						grupo1.add(arcoAGM.getDestino());
					} else if (!grupo1.contains(arcoAGM.getOrigen()) && grupo1.contains(arcoAGM.getDestino())) {
						grupo1.add(arcoAGM.getOrigen());
					} else if (grupo2.contains(arcoAGM.getOrigen()) && !grupo2.contains(arcoAGM.getDestino())) {

						grupo2.add(arcoAGM.getDestino());
					} else if (!grupo2.contains(arcoAGM.getOrigen()) && grupo2.contains(arcoAGM.getDestino())) {
						grupo2.add(arcoAGM.getOrigen());
					}

				}

			}

	}

	public LinkedList<Persona> obtenerGrupo1() {
		return grupo1;
	}

	public LinkedList<Persona> obtenerGrupo2() {
		return grupo2;
	}

}
