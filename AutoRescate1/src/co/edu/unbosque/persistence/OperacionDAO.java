package co.edu.unbosque.persistence;

import java.time.LocalDateTime;
import co.edu.unbosque.model.ListaEnlazada;
import co.edu.unbosque.model.Nodo;
import co.edu.unbosque.model.Operacion;
import co.edu.unbosque.model.TipoOperacion;

public class OperacionDAO implements CrudDAO<OperacionDTO<?>>{

	private ListaEnlazada<Operacion<?>> listaOperacion;
	private final String CSV_NAME = "Operacion.csv";
	private final String SERIAL_NAME = "Operacion.bin";
	
	// Método auxiliar privado que fuerza a Java a tratar ambos como el mismo T
	private <T> void copiarObjetoAfectado(Operacion<T> destino, OperacionDTO<?> origen) {
	    @SuppressWarnings("unchecked")
	    T valor = (T) origen.getObjetoAfectado();
	    destino.setObjetoAfectado(valor);
	}
	
	@Override
	public void leerDeArchivoCSV() {

	    String contenido = FileHandler.leerDesdeArchivoDeTexto(CSV_NAME);

	    if (contenido == null || contenido.isBlank()) {
	        return;
	    }

	    String[] filas = contenido.split("\n");

	    for (int i = 0; i < filas.length; i++) {

	        String[] columnas = filas[i].split(";");


	        Operacion temporal = new Operacion();
	        
	        temporal.setIdOperacion(columnas[0].trim());
	        temporal.setTipo(TipoOperacion.valueOf(columnas[1].trim()));
	        //temporal.setObjetoAfectado(columnas[2].trim());
	        temporal.setEstadoNuevo(columnas[3].trim());
	        temporal.setEstadoAnterior(columnas[4].trim());
	        temporal.setTimestamp(LocalDateTime.parse(columnas [5].trim()));
	        listaOperacion.insertarAlFinal(temporal);
	    }
	}

	@Override
	public void escribirEnArchivoCSV() {
		StringBuilder sb = new StringBuilder();
		Nodo <Operacion<?>> actual = listaOperacion.getCabeza();
		
		while (actual != null) {
			Operacion operacion = actual.getDato();
			sb.append(operacion.getIdOperacion() + ";");
			sb.append(operacion.getTipo() + ";");
			sb.append(operacion.getObjetoAfectado() + ";");
			sb.append(operacion.getEstadoAnterior() + ";");
			sb.append(operacion.getEstadoNuevo() + ";");
			sb.append(operacion.getTimestamp() + ";");
			
			actual = actual.getSiguiente(); 
		}
		FileHandler.escribirDatos(CSV_NAME, sb.toString());
	}

	@Override
	public void cargarDesdeArchivoSerializado() {
		Object contenido = FileHandler.leerArchivoSerializado(SERIAL_NAME);
		
		if (contenido != null) {
			listaOperacion = (ListaEnlazada<Operacion<?>>) contenido;
		} else {
			listaOperacion = new ListaEnlazada<>();
		}
	}

	@Override
	public void escribirArchivoSerializado() {
		FileHandler.escribirArchivoSerializado(SERIAL_NAME, listaOperacion);
		
	}

	@Override
	public void crear(OperacionDTO obj) {

		Operacion operacion = DataMapper.convertirOperacionDTOAOperacion(obj);
	 
	    listaOperacion.insertarAlFinal(operacion);
	    
	    escribirEnArchivoCSV();
	    escribirArchivoSerializado();

	}

	@Override
	public boolean eliminar(int index) {
		// Validar rango
		if (index < 0 || index >= listaOperacion.getTamaño()) {
			return false;
		}
 
		// Caso especial: eliminar el primer nodo (cabeza)
		if (index == 0) {
			listaOperacion .eliminarPrimero();
			escribirEnArchivoCSV();
			escribirArchivoSerializado();
			return true;
		}

		// Caso general: avanzar hasta el nodo ANTERIOR al que queremos borrar
				Nodo<Operacion<?>> anterior = listaOperacion.getCabeza();
				for (int i = 0; i < index - 1; i++) {
					anterior = anterior.getSiguiente();
				}
		 
				// El nodo a eliminar es el siguiente del anterior
				Nodo<Operacion<?>> aEliminar = anterior.getSiguiente();
		 
				// Saltar el nodo objetivo reconectando el puntero
				anterior.setSiguiente(aEliminar.getSiguiente());
		 
				// Persistir cambios
				escribirEnArchivoCSV();
				escribirArchivoSerializado();
				return true;
}

	@Override
	public boolean actualizar(int index, OperacionDTO<?> obj) {
		// Validar parámetros
		if (index < 0 || index >= listaOperacion.getTamaño() || obj == null) {
			return false;
		}
 
		// Obtener el objeto Cliente almacenado en esa posición
		Operacion<?> operacionExistente = listaOperacion .obtener(index);
 
		if (operacionExistente == null) {
			return false;
		}
 
		// Actualizar solo los campos que el DTO trae con valor no nulo/vacío
		if (obj.getIdOperacion() != null && !obj.getIdOperacion().isBlank()) {
			operacionExistente.setIdOperacion(obj.getIdOperacion());
		}
		if (obj.getTipo() != null) {
			operacionExistente.setTipo(obj.getTipo());
		}
		if (obj.getObjetoAfectado() != null) {
			copiarObjetoAfectado(operacionExistente, obj);
		}
		if (obj.getEstadoAnterior() != null) {
			operacionExistente.setEstadoAnterior(obj.getEstadoAnterior());
		}
		if (obj.getEstadonuevo() != null) {
			operacionExistente.setEstadoNuevo(obj.getEstadonuevo());
		}
 
		// Persistir cambios
		escribirEnArchivoCSV();
		escribirArchivoSerializado();
		return true;
	}

	@Override
	public ListaEnlazada<OperacionDTO<?>> leerTodos() {

	    ListaEnlazada<OperacionDTO<?>> listaDTO = new ListaEnlazada<>();

	    Nodo<Operacion<?>> actual = listaOperacion.getCabeza();

	    while (actual != null) {
	        listaDTO.insertarAlFinal(
	            DataMapper.convertirOperacionAOperacionDTO(actual.getDato())
	        );
	        actual = actual.getSiguiente();
	    }

	    return listaDTO;
	}	

}
