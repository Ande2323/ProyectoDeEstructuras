package co.edu.unbosque.model;

public class Kit {

    private String id;
    private String nombre;
    private String descripcion;
    private boolean enRevision;
    private ListaEnlazada<String> elementosFaltantes;
    private boolean requiereReparacion;

    public Kit(String id, String nombre, String descripcion, boolean enRevision, 
               ListaEnlazada<String> elementosFaltantes, boolean requiereReparacion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.enRevision = enRevision;
        this.elementosFaltantes = elementosFaltantes;
        this.requiereReparacion = requiereReparacion;
    }

    public void enviarARevision() {
        this.enRevision = true;
    }

    public void marcarCompleto() {
        this.enRevision = false;
    }

    public void agregarElementoFaltante(String elemento) {
        this.elementosFaltantes.insertarAlFinal(elemento);
    }

    public void marcarRequiereReparacion() {
        this.requiereReparacion = true;
    }

    @Override
    public String toString() {
        return "Kit [ID=" + id + ", Nombre=" + nombre + ", En Revisión=" + enRevision + "]";
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isEnRevision() {
        return enRevision;
    }

    public void setEnRevision(boolean enRevision) {
        this.enRevision = enRevision;
    }

    public ListaEnlazada<String> getElementosFaltantes() {
        return elementosFaltantes;
    }

    public void setElementosFaltantes(ListaEnlazada<String> elementosFaltantes) {
        this.elementosFaltantes = elementosFaltantes;
    }

    public boolean isRequiereReparacion() {
        return requiereReparacion;
    }

    public void setRequiereReparacion(boolean requiereReparacion) {
        this.requiereReparacion = requiereReparacion;
    }
}