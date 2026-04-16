
// OperacionDTO.java
package co.edu.unbosque.persistence;

import java.time.LocalDateTime;

import co.edu.unbosque.model.TipoOperacion;

public class OperacionDTO<T> {
    
    private String idOperacion;
    private TipoOperacion tipo;
    private T objetoAfectado;
    private Object estadoAnterior;
    private Object estadonuevo;     
    private LocalDateTime timestamp;

    public OperacionDTO() {
    	
    }

	public OperacionDTO(String idOperacion, TipoOperacion tipo, T objetoafectado, Object estadoAnterior, Object estadonuevo,
			LocalDateTime timestamp) {
		super();
		this.idOperacion = idOperacion;
		this.tipo = tipo;
		this.objetoAfectado = objetoAfectado;
		this.estadoAnterior = estadoAnterior;
		this.estadonuevo = estadonuevo;
		this.timestamp = timestamp;
	}

	public String getIdOperacion() {
		return idOperacion;
	}

	public void setIdOperacion(String idOperacion) {
		this.idOperacion = idOperacion;
	}

	public TipoOperacion getTipo() {
		return tipo;
	}

	public void setTipo(TipoOperacion tipo) {
		this.tipo = tipo;
	}

	public T getObjetoAfectado() {
		return objetoAfectado;
	}

	public void setObjetoAfectado(T objeto) {
		this.objetoAfectado = objeto;
	}

	public Object getEstadoAnterior() {
		return estadoAnterior;
	}

	public void setEstadoAnterior(Object estadoAnterior) {
		this.estadoAnterior = estadoAnterior;
	}

	public Object getEstadonuevo() {
		return estadonuevo;
	}

	public void setEstadonuevo(Object estadonuevo) {
		this.estadonuevo = estadonuevo;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "OperacionDTO [idOperacion=" + idOperacion + ", tipo=" + tipo + ", objeto=" + objetoAfectado
				+ ", estadoAnterior=" + estadoAnterior + ", estadonuevo=" + estadonuevo + ", timestamp=" + timestamp
				+ "]";
	}
	
}