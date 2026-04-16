 package co.edu.unbosque.model;

import java.io.Serializable;

public class Cliente implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1351590391510163358L;
	
	private String nombre;
	private String tipoCliente;
	private String tipoSangre;
	private String contacto;
	
	public Cliente() {
		// TODO Auto-generated constructor stub
	}

	public Cliente(String nombre, String tipoCliente, String contacto, String tipoSangre) {
		super();
		this.nombre = nombre;
		this.tipoCliente = tipoCliente;
		this.contacto = contacto;
		this.tipoSangre = tipoSangre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public String getContacto() {
		return contacto;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
	}

	public String getTipoSangre() {
		return tipoSangre;
	}

	public void setTipoSangre(String tipoSangre) {
		this.tipoSangre = tipoSangre;
	}

	@Override
	public String toString() {
		return "Cliente [nombre=" + nombre + ", tipoCliente=" + tipoCliente + ", contacto=" + contacto + "]";
	}
	
	
}
