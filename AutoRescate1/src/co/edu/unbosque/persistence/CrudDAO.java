
package co.edu.unbosque.persistence;
import co.edu.unbosque.model.ListaEnlazada;
 
public interface CrudDAO<T>{	
	
	public void leerDeArchivoCSV();

	public void escribirEnArchivoCSV();

	public void cargarDesdeArchivoSerializado();

	public void escribirArchivoSerializado();
	
	public void crear(T obj);

	public boolean eliminar(int index);

	public boolean actualizar(int index, T obj);
	
	public ListaEnlazada<T> leerTodos();
	}
//branch 'master' of https://github.com/Ande2323/ProyectoEstructurasI.git
