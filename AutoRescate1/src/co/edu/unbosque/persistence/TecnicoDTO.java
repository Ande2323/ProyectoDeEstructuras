package co.edu.unbosque.persistence;
 import java.io.Serializable;
  
public class TecnicoDTO implements Serializable{
	private static final long serialVersionUID = 2L;
	private String id;
	private String nombre; 
	private String especialidad;
	private String estado;
	private String zona;
	private boolean disponible; 
	
	public TecnicoDTO() {
		
	}
	public TecnicoDTO(String id, String nombre, String especialidad, String estado, String zona, boolean disponible) {
		this.id = id;
		this.nombre = nombre;
		this.especialidad = especialidad;
		this.estado = estado;
		this.zona = zona;
		this.disponible = disponible; 
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEspecialidad() {
		return especialidad;
	}
	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
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
	public boolean isDisponible() {
		return disponible;
	}
	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "TecnicoDTO [id=" + id + ", nombre=" + nombre + ", especialidad=" + especialidad + ", estado=" + estado
				+ ", zona=" + zona + ", disponible=" + disponible + "]";
	}
}
