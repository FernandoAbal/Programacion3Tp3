package logica;

import java.util.LinkedList;

public class ConjuntoPersonas {
	
	LinkedList<Persona> personas;

	public ConjuntoPersonas() {
		this.personas = new LinkedList<Persona>(); //Las personas que se generan en la interfaz, se agregan a esta linkedlist 
	}
	
	
	public void crearPersona(String nombre, 
			String di, String mi, String ei, String ci ) {
		
		Persona persona = new Persona(nombre, Integer.parseInt(di), 
				Integer.parseInt(mi),Integer.parseInt(ei), Integer.parseInt(ci));		
				
		personas.add(persona);
		
	}
	
	public void mostrarPersonas() {
		
		for(Persona p:personas) {
			System.out.println(p);
		}
	}
	
	public LinkedList<Persona> getPersonas(){
		return personas;
	}
	
	public String[] getUltimaPersona() {
		return personas.getLast().getDatosPersona();
	}

}
