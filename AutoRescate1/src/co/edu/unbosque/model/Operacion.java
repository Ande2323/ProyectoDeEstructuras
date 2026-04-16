package co.edu.unbosque.model;

import java.time.LocalDateTime;

public class Operacion<T> {
    private String idOperacion;
    private TipoOperacion tipo;
    private T objetoAfectado;
    private Object estadoAnterior;
    private Object estadoNuevo;
    private LocalDateTime timestamp;

    public Operacion() {
		// TODO Auto-generated constructor stub
	}
    
    public Operacion(String idOperacion, TipoOperacion tipo, T objetoAfectado, Object estadoAnterior, Object estadoNuevo) {
        this.idOperacion = idOperacion;
        this.tipo = tipo;
        this.objetoAfectado = objetoAfectado;
        this.estadoAnterior = estadoAnterior;
        this.estadoNuevo = estadoNuevo;
        this.timestamp = LocalDateTime.now();
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

	public void setObjetoAfectado(T objetoAfectado) {
		this.objetoAfectado = objetoAfectado;
	}

	public Object getEstadoAnterior() {
		return estadoAnterior;
	}

	public void setEstadoAnterior(Object estadoAnterior) {
		this.estadoAnterior = estadoAnterior;
	}

	public Object getEstadoNuevo() {
		return estadoNuevo;
	}

	public void setEstadoNuevo(Object estadoNuevo) {
		this.estadoNuevo = estadoNuevo;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

}
