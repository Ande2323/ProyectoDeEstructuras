package co.edu.unbosque.persistence;

import co.edu.unbosque.model.Cliente;
import co.edu.unbosque.model.ListaEnlazada;
import co.edu.unbosque.model.Nodo;

public class ClienteDAO implements CrudDAO<ClienteDTO>{
	
	private ListaEnlazada<Cliente> listaClientes;
	private final String CSV_NAME = "Clientes.csv";
	private final String SERIAL_NAME = "Clientes.bin";

	public ClienteDAO() {
		listaClientes = new ListaEnlazada<Cliente>();
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

		        Cliente temporal = new Cliente();

		        temporal.setNombre(columnas[0].trim());
		        temporal.setTipoCliente(columnas[1].trim());
		        temporal.setContacto(columnas[2].trim());
		        temporal.setTipoSangre(columnas[3].trim());

		        listaClientes.insertarAlFinal(temporal);
		    }
		}
	

	@Override
	public void escribirEnArchivoCSV() {
	    StringBuilder sb = new StringBuilder();

	    Nodo<Cliente> actual = listaClientes.getCabeza();

	    while (actual != null) {
	        Cliente cliente = actual.getDato();

	        sb.append(cliente.getNombre() + ";");
	        sb.append(cliente.getTipoCliente() + ";");
	        sb.append(cliente.getContacto() + ";");
	        sb.append(cliente.getTipoSangre() + "\n");

	        actual = actual.getSiguiente();
	    }

	    FileHandler.escribirDatos(CSV_NAME, sb.toString());
	}
	
	@Override
	public void cargarDesdeArchivoSerializado() {

	    Object contenido = FileHandler.leerArchivoSerializado(SERIAL_NAME);

	    if (contenido != null) {
	        listaClientes = (ListaEnlazada<Cliente>) contenido;
	    } else {
	        listaClientes = new ListaEnlazada<>();
	    }
	}

	@Override
	public void escribirArchivoSerializado() {

	    FileHandler.escribirArchivoSerializado(SERIAL_NAME, listaClientes);
	}

	@Override
	public void crear(ClienteDTO obj) {

	    Cliente cliente = DataMapper.convertirClienteDTOACliente(obj);

	    listaClientes.insertarAlFinal(cliente);
	    
	    escribirEnArchivoCSV();
	    escribirArchivoSerializado();
	}
	
	@Override
	public boolean eliminar(int index) {
 
		// Validar rango
		if (index < 0 || index >= listaClientes.getTamaño()) {
			return false;
		}
 
		// Caso especial: eliminar el primer nodo (cabeza)
		if (index == 0) {
			listaClientes.eliminarPrimero();
			escribirEnArchivoCSV();
			escribirArchivoSerializado();
			return true;
		}

		// Caso general: avanzar hasta el nodo ANTERIOR al que queremos borrar
				Nodo<Cliente> anterior = listaClientes.getCabeza();
				for (int i = 0; i < index - 1; i++) {
					anterior = anterior.getSiguiente();
				}
		 
				// El nodo a eliminar es el siguiente del anterior
				Nodo<Cliente> aEliminar = anterior.getSiguiente();
		 
				// Saltar el nodo objetivo reconectando el puntero
				anterior.setSiguiente(aEliminar.getSiguiente());
		 
				// Persistir cambios
				escribirEnArchivoCSV();
				escribirArchivoSerializado();
				return true;
			}

	@Override
	public boolean actualizar(int index, ClienteDTO obj) {
 
		// Validar parámetros
		if (index < 0 || index >= listaClientes.getTamaño() || obj == null) {
			return false;
		}
 
		// Obtener el objeto Cliente almacenado en esa posición
		Cliente clienteExistente = listaClientes.obtener(index);
 
		if (clienteExistente == null) {
			return false;
		}
 
		// Actualizar solo los campos que el DTO trae con valor no nulo/vacío
		if (obj.getNombre() != null && !obj.getNombre().isBlank()) {
			clienteExistente.setNombre(obj.getNombre());
		}
		if (obj.getTipoCliente() != null && !obj.getTipoCliente().isBlank()) {
			clienteExistente.setTipoCliente(obj.getTipoCliente());
		}
		if (obj.getContacto() != null && !obj.getContacto().isBlank()) {
			clienteExistente.setContacto(obj.getContacto());
		}
		if (obj.getTipoSangre() != null && !obj.getTipoSangre().isBlank()) {
			clienteExistente.setTipoSangre(obj.getTipoSangre());
		}
 
		// Persistir cambios
		escribirEnArchivoCSV();
		escribirArchivoSerializado();
		return true;
	}

	@Override
	public ListaEnlazada<ClienteDTO> leerTodos() {

	    ListaEnlazada<ClienteDTO> listaDTO = new ListaEnlazada<>();

	    Nodo<Cliente> actual = listaClientes.getCabeza();

	    while (actual != null) {
	        listaDTO.insertarAlFinal(
	            DataMapper.convertirClienteAClienteDTO(actual.getDato())
	        );
	        actual = actual.getSiguiente();
	    }

	    return listaDTO;
	}	
}
