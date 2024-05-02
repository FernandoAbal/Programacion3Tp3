package logica;

public class Persona {
	
	private String nombre;
	private Integer InteresDeporte;
	private Integer InteresMusica;
	private Integer InteresEspectaculo;
	private Integer InteresCiencia;
	
	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", InteresDeporte=" + InteresDeporte + ", InteresMusica=" + InteresMusica
				+ ", InteresEspectaculo=" + InteresEspectaculo + ", InteresCiencia=" + InteresCiencia + "]";
	}
	public Persona( String nombre, int interesDeporte, int interesMusica, int interesEspectaculo, int interesCiencia) {
		this.nombre = nombre;
		this.InteresDeporte = interesDeporte;
		this.InteresMusica = interesMusica;
		this.InteresEspectaculo = interesEspectaculo;
		this.InteresCiencia = interesCiencia;
	}
	
	public String[] getDatosPersona() {//La tabla necesita un array de String para poder agregarlo como fila
		String[] ret = new String[] {nombre,InteresDeporte.toString(),
				InteresMusica.toString(), InteresEspectaculo.toString(),
				InteresCiencia.toString()};
		return ret;
	}
	
	public int getInteresDeporte() {
		return InteresDeporte;
	}
	
	public int getInteresMusica() {
		return InteresMusica;
	}
	
	public int getInteresEspectaculo() {
		return InteresEspectaculo;
	}
	
	public int getInteresCiencia() {
		return InteresCiencia;
	}
	
	public String getNombre() {
		return nombre;
	}

}
