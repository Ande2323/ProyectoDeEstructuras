package co.edu.unbosque.persistence;

import co.edu.unbosque.model.Cliente;
import co.edu.unbosque.model.ListaEnlazada;
import co.edu.unbosque.model.Nodo;
import co.edu.unbosque.model.Tecnico;

public class TecnicoDAO implements CrudDAO<TecnicoDTO>{

	private ListaEnlazada<Tecnico> listaTecnicos;
	private final String CSV_NAME = "Tecnicos.csv";
	private final String SERIAL_NAME = "Tecnicos.bin";
	
	public TecnicoDAO() {
		listaTecnicos = new ListaEnlazada<Tecnico>();
		leerDeArchivoCSV();
		cargarDesdeArchivoSerializado();
	}
	
	@Override
	public void leerDeArchivoCSV() {
		String contenido = FileHandler.leerDesdeArchivoDeTexto(CSV_NAME);
		
		if(contenido == null || contenido.isBlank()) {
			return;
		}
		
		String[] filas = contenido.split("\n");
		
		for(int i = 0; i < filas.length; i++) {
			
			String[] columnas = filas[i].split(";");
			
			Tecnico temporal = new Tecnico();
			
			temporal.setId(columnas[0].trim());
			temporal.setNombre(columnas[1].trim());
			temporal.setEspecialidad(columnas[2].trim());
			temporal.setEstado(columnas[3].trim());
			temporal.setZona(columnas[4].trim());
			temporal.setEstaDisponible(Boolean.valueOf(columnas[5].trim()));
			
			listaTecnicos.insertarAlFinal(temporal);
		}
		
	}

	@Override
	public void escribirEnArchivoCSV() {
	    StringBuilder sb = new StringBuilder();

	    Nodo<Tecnico> actual = listaTecnicos.getCabeza();

	    while (actual != null) {
	        Tecnico tecnico= actual.getDato();
	        
	        sb.append(tecnico.getId()+";");
	        sb.append(tecnico.getNombre() + ";");
	        sb.append(tecnico.getEspecialidad() + ";");
	        sb.append(tecnico.getEstado() + ";");
	        sb.append(tecnico.getZona() + ";");
	        sb.append(tecnico.isEstaDisponible() + "\n");

	        actual = actual.getSiguiente();
	    }

	    FileHandler.escribirDatos(CSV_NAME, sb.toString());
	
	}

	@Override
	public void cargarDesdeArchivoSerializado() {

	    Object contenido = FileHandler.leerArchivoSerializado(SERIAL_NAME);

	    if (contenido != null) {
	        listaTecnicos = (ListaEnlazada<Tecnico>) contenido;
	    } else {
	        listaTecnicos= new ListaEnlazada<>();
	    }
	
	}

	@Override
	public void escribirArchivoSerializado() {
	    FileHandler.escribirArchivoSerializado(SERIAL_NAME, listaTecnicos);
		
	}

	@Override
	public void crear(TecnicoDTO obj) {

	    Tecnico tecnico= DataMapper.convertirTecnicoDTOATecnico(obj);

	    listaTecnicos.insertarAlFinal(tecnico);
	    
	    escribirEnArchivoCSV();
	    escribirArchivoSerializado();
		
	}

	@Override
	public boolean eliminar(int index) {
		// Validar rango
		if (index < 0 || index >= listaTecnicos.getTamaño()) {
			return false;
		}
 
		// Caso especial: eliminar el primer nodo (cabeza)
		if (index == 0) {
			listaTecnicos.eliminarPrimero();
			escribirEnArchivoCSV();
			escribirArchivoSerializado();
			return true;
		}

		// Caso general: avanzar hasta el nodo ANTERIOR al que queremos borrar
				Nodo<Tecnico> anterior = listaTecnicos.getCabeza();
					for (int i = 0; i < index - 1; i++) {
					anterior = anterior.getSiguiente();
				}
		 
				// El nodo a eliminar es el siguiente del anterior
				Nodo<Tecnico> aEliminar = anterior.getSiguiente();
		 
				// Saltar el nodo objetivo reconectando el puntero
				anterior.setSiguiente(aEliminar.getSiguiente());
		 
				// Persistir cambios
				escribirEnArchivoCSV();
				escribirArchivoSerializado();
				return true;
}

	@Override
	public boolean actualizar(int index, TecnicoDTO obj) {
		if (index < 0 || index >= listaTecnicos.getTamaño() || obj == null) {
			return false;
		}
 
		// Obtener el objeto Cliente almacenado en esa posición
		Tecnico tecnicoExistente = listaTecnicos.obtener(index);
 
		if (tecnicoExistente == null) {
			return false;
		}
 
		// Actualizar solo los campos que el DTO trae con valor no nulo/vacío
		
		if (obj.getId() != null && !obj.getId().isBlank()) {
			tecnicoExistente.setId(obj.getId());
		}
		
		if (obj.getNombre() != null && !obj.getNombre().isBlank()) {
			tecnicoExistente.setNombre(obj.getNombre());
		}
		if (obj.getEspecialidad() != null && !obj.getEspecialidad().isBlank()) {
			tecnicoExistente.setEspecialidad(obj.getEspecialidad());
		}
		if (obj.getEstado() != null && !obj.getEstado().isBlank()) {
			tecnicoExistente.setEstado(obj.getEstado());
		}
		if (obj.getZona() != null && !obj.getZona().isBlank()) {
			tecnicoExistente.setZona(obj.getZona());
		}
 
		// Persistir cambios
		escribirEnArchivoCSV();
		escribirArchivoSerializado();
		return true;
}

	@Override
	public ListaEnlazada<TecnicoDTO> leerTodos() {
	
	ListaEnlazada<TecnicoDTO> listaDTO = new ListaEnlazada<>();

    Nodo<Tecnico> actual = listaTecnicos.getCabeza();

    while (actual != null) {
        listaDTO.insertarAlFinal(
            DataMapper.convertirTecnicoATecnicpDTO(actual.getDato())
        );
        actual = actual.getSiguiente();
    }

    return listaDTO;
	}	

}
