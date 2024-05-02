package logica;

public class Arco implements Comparable<Arco> {

	private Persona origen;
	private Persona destino;
	private int peso;
	
	//CompareTo para ordenar los arcos por peso
	public int compareTo(Arco xArco) {
        return Integer.compare(this.peso, xArco.peso);
    }
	
	public Arco(Persona origen, Persona destino, int peso) {
		
		this.origen = origen;
		this.destino = destino;
		this.peso = peso;
	}
	
	 public int getPeso() {
		return peso;
	}

	public Persona getOrigen() {
		return origen;
	}

	public Persona getDestino() {
		return destino;
	}

	@Override
	public String toString() {
		return origen.getNombre() + ", " + destino.getNombre() + " = " + peso ;
	}

	
}
