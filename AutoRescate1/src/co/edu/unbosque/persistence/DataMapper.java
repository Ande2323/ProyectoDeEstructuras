package co.edu.unbosque.persistence;

import co.edu.unbosque.model.Cliente;
import co.edu.unbosque.model.Kit;
import co.edu.unbosque.model.ListaEnlazada;
import co.edu.unbosque.model.Tecnico;
import co.edu.unbosque.model.Operacion;

public class DataMapper {

	public static Cliente convertirClienteDTOACliente(ClienteDTO cDTO) {

		Cliente cliente = new Cliente(cDTO.getNombre(), cDTO.getTipoCliente(), cDTO.getTipoSangre(), cDTO.getContacto());

		return cliente;
	}

	public static ClienteDTO convertirClienteAClienteDTO(Cliente cliente) {

		ClienteDTO dto = new ClienteDTO();

		dto.setNombre(cliente.getNombre());
		dto.setTipoCliente(cliente.getTipoCliente());
		dto.setContacto(cliente.getContacto());
		dto.setTipoSangre(cliente.getTipoSangre());

		return dto;
	}
	
	 public static Kit convertirKitDTOAKit(KitDTO kDTO) {
	        return new Kit(
	            kDTO.getId(),
	            kDTO.getNombre(),
	            kDTO.getDescripcion(),
	            kDTO.isEnRevision(),
	            kDTO.getElementosFaltantes() != null
	                ? kDTO.getElementosFaltantes()
	                : new ListaEnlazada<>(),
	            kDTO.isRequiereReparacion()
	        );
	    }

	    public static KitDTO convertirKitAKitDTO(Kit kit) {
	        KitDTO dto = new KitDTO();
	        dto.setId(kit.getId());
	        dto.setNombre(kit.getNombre());
	        dto.setDescripcion(kit.getDescripcion());
	        dto.setEnRevision(kit.isEnRevision());
	        dto.setElementosFaltantes(kit.getElementosFaltantes());
	        dto.setRequiereReparacion(kit.isRequiereReparacion());
	        return dto;
	    }

	    public static Tecnico convertirTecnicoDTOATecnico(TecnicoDTO tDTO) {
	        return new Tecnico(
	            tDTO.getId(),
	            tDTO.getNombre(),
	            tDTO.getEspecialidad(),
	            tDTO.getEstado(),
	            tDTO.getZona(),
	            tDTO.isDisponible()
	        );
	    }

	    public static TecnicoDTO convertirTecnicoATecnicpDTO(Tecnico tecnico) {
	        TecnicoDTO dto = new TecnicoDTO();
	        dto.setId(tecnico.getId());
	        dto.setNombre(tecnico.getNombre());
	        dto.setEspecialidad(tecnico.getEspecialidad());
	        dto.setEstado(tecnico.getEstado());
	        dto.setZona(tecnico.getZona());
	        dto.setDisponible(tecnico.isEstaDisponible());
	       
	        return dto;
	    }


	    public static  Operacion<?> convertirOperacionDTOAOperacion(OperacionDTO oDTO) { //se dice a java "esta clase va a trabajar con algún tipo, pero aún no sé cuál"
	    	/* se pone <?> porque se usa en las firmas 
	    	de métodos cuando el método no necesita 
	    	saber el tipo concreto para hacer su trabajo*/
	        return new Operacion<>(
	            oDTO.getIdOperacion(),
	            oDTO.getTipo(),
	            oDTO.getObjetoAfectado(),
	            oDTO.getEstadoAnterior(),
	            oDTO.getEstadonuevo()
		    );
	    }

	    public static OperacionDTO convertirOperacionAOperacionDTO(Operacion operacion) {
	        OperacionDTO<Object> dto = new OperacionDTO<>(); //object porque no se sabe el tipo q se debe usar
	        dto.setIdOperacion(operacion.getIdOperacion());
	        dto.setTipo(operacion.getTipo());
	        dto.setObjetoAfectado(operacion.getObjetoAfectado());
	        dto.setEstadoAnterior(operacion.getEstadoAnterior());
	        dto.setEstadonuevo(operacion.getEstadoNuevo());
	       
	        return dto;
	    }


}
