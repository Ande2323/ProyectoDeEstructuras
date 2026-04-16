package co.edu.unbosque.model;

import java.util.UUID;

public class Repuesto {
    private UUID id;
    private String nombre;
    private String tipo;
    private boolean estaListo;

    public Repuesto(UUID id, String nombre, String tipo, boolean estaListo) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.estaListo = estaListo;
    }

    
    public UUID getId() {
		return id;
	}


	public void setId(UUID id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public boolean isEstaListo() {
		return estaListo;
	}


	public void setEstaListo(boolean estaListo) {
		this.estaListo = estaListo;
	}


	@Override
    public String toString() { 
		return "Repuesto: " + nombre + " [" + tipo + "]";
	}
	
}
