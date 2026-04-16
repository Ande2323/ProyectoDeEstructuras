package co.edu.unbosque.persistence;

import co.edu.unbosque.model.Kit;
import co.edu.unbosque.model.ListaEnlazada;
import co.edu.unbosque.model.Nodo;

public class KitDAO implements CrudDAO<KitDTO>{
	private ListaEnlazada<Kit> listaKits;
	private final String CSV_NAME = "Kit.csv";
	private final String SERIAL_NAME = "Kit.bin";
	
	public KitDAO() {
		listaKits = new ListaEnlazada<Kit>();
		leerDeArchivoCSV();
		cargarDesdeArchivoSerializado();
	
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
		        
		        if (columnas.length < 4 ) {
		        	continue;
		        }

		        Kit  temporal = new Kit(contenido, contenido, contenido, false, null, false);

		        temporal.setId(columnas[0].trim());
		        temporal.setNombre(columnas[1].trim());
		        temporal.setDescripcion(columnas[2].trim());
		        temporal.setEnRevision(Boolean.parseBoolean(columnas[3].trim())); //al ser un booleano se debe poner primero las especificaciones para guardar bien la info

		        listaKits.insertarAlFinal(temporal);
		    }

	}
	
	@Override
	public void escribirEnArchivoCSV() {
		 StringBuilder sb = new StringBuilder();

		    Nodo<Kit> actual = listaKits.getCabeza();

		    while (actual != null) {
		        Kit kit = actual.getDato();

		        sb.append(kit.getId()+ ";");
		        sb.append(kit.getNombre()+ ";");
		        sb.append(kit.getDescripcion()+ ";");
		        sb.append(kit.isEnRevision()+ ";");
		        sb.append(kit.getElementosFaltantes()+ ";");
		        sb.append(kit.isRequiereReparacion()+ "\n");

		        actual = actual.getSiguiente();
		    }

		    FileHandler.escribirDatos(CSV_NAME, sb.toString());
	
	}
	
	@Override
	public void cargarDesdeArchivoSerializado() {
	    Object contenido = FileHandler.leerArchivoSerializado(SERIAL_NAME);

	    if (contenido != null) {
	        listaKits = (ListaEnlazada<Kit>) contenido;
	    } else {
	        listaKits= new ListaEnlazada<>();
	    }

	}
	
	@Override
	public void escribirArchivoSerializado() {
		FileHandler.escribirArchivoSerializado(SERIAL_NAME, CSV_NAME);	
	}
	
	@Override
	public void crear(KitDTO obj) {
		Kit kit = DataMapper.convertirKitDTOAKit(obj);
		listaKits.insertarAlFinal(kit);
		
		escribirEnArchivoCSV();
		escribirArchivoSerializado();
	}
	
	@Override
	public boolean eliminar(int index) {
		// Validar rango
				if (index < 0 || index >= listaKits.getTamaño()) {
					return false;
				}
		 
				// Caso especial: eliminar el primer nodo (cabeza)
				if (index == 0) {
					listaKits.eliminarPrimero();
					escribirEnArchivoCSV();
					escribirArchivoSerializado();
					return true;
				}

				// Caso general: avanzar hasta el nodo ANTERIOR al que queremos borrar
						Nodo<Kit> anterior = listaKits.getCabeza();
						for (int i = 0; i < index - 1; i++) {
							anterior = anterior.getSiguiente();
						}
				 
						// El nodo a eliminar es el siguiente del anterior
						Nodo<Kit> aEliminar = anterior.getSiguiente();
				 
						// Saltar el nodo objetivo reconectando el puntero
						anterior.setSiguiente(aEliminar.getSiguiente());
				 
						// Persistir cambios
						escribirEnArchivoCSV();
						escribirArchivoSerializado();
						return true;
}
	@Override
	public boolean actualizar(int index, KitDTO obj) {
		// Validar parámetros
				if (index < 0 || index >= listaKits.getTamaño() || obj == null) {
					return false;
				}
		 
				// Obtener el objeto Cliente almacenado en esa posición
				Kit kitExistente = listaKits.obtener(index);
		 
				if (kitExistente == null) {
					return false;
				}
		 
				// Actualizar solo los campos que el DTO trae con valor no nulo/vacío
				
				if(obj.getId() != null && !obj.getId().isBlank()) {
					kitExistente.setId(obj.getId());
				}
				if (obj.getNombre() != null && !obj.getNombre().isBlank()) {
					kitExistente.setNombre(obj.getNombre());
				}
				if (obj.getDescripcion() != null && !obj.getDescripcion().isBlank()) {
					kitExistente.setDescripcion(obj.getDescripcion());
				}
				if (obj.getElementosFaltantes() != null && !((String) obj.getElementosFaltantes).isBlank()) {
					kitExistente.setElementosFaltantes(obj.getElementosFaltantes()
							);
				}
		 
				// Persistir cambios
				escribirEnArchivoCSV();
				escribirArchivoSerializado();
				return true;
	}
	@Override
	public ListaEnlazada<KitDTO> leerTodos() {

	    ListaEnlazada<KitDTO> listaDTO = new ListaEnlazada<>();

	    Nodo<Kit> actual = listaKits.getCabeza();

	    while (actual != null) {
	        listaDTO.insertarAlFinal(
	            DataMapper.convertirKitAKitDTO(actual.getDato())
	        );
	        actual = actual.getSiguiente();
	    }

	    return listaDTO;
	}	
	
}
