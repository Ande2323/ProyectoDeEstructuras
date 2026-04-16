package co.edu.unbosque.model;

import java.util.UUID;

public class UnidadServicio {
    private UUID id;
    private String tipo;
    private String estado;
    private String zona;
    private boolean estaDisponible;

    public UnidadServicio(UUID id, String tipo, String estado, String zona, boolean estaDisponible) {
        this.id = id;
        this.tipo = tipo;
        this.estado = estado;
        this.zona = zona;
        this.estaDisponible = estaDisponible;
    }


    public UUID getId() {
		return id;
	}


	public void setId(UUID id) {
		this.id = id;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public String getZona() {
		return zona;
	}


	public void setZona(String zona) {
		this.zona = zona;
	}


	public boolean isEstaDisponible() {
		return estaDisponible;
	}


	public void setEstaDisponible(boolean estaDisponible) {
		this.estaDisponible = estaDisponible;
	}


	public boolean esAptaParaAsignacion() {
        return estaDisponible && estado.equalsIgnoreCase("Optimo");
    }

    @Override
    public String toString() {
        return "Unidad [" + tipo + "] ID: " + id + " en " + zona;
    }
	
}
