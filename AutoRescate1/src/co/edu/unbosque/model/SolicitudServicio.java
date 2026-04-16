package co.edu.unbosque.model;

import java.time.LocalDateTime;

public class SolicitudServicio {

    private String idSolicitud;
    private Cliente cliente;
    private String tipoServicio;
    private String estadoSolicitud;
    private String ubicacion;
    private String descripcion;
    private LocalDateTime fechaHora;
    private Prioridad nivelPrioridad;
    private UnidadServicio unidadAsignada;
    private Tecnico tecnicoAsignado;
    private String observaciones;

    public SolicitudServicio(String idSolicitud, Cliente cliente, String tipoServicio, String estadoSolicitud, 
                            String ubicacion, String descripcion, LocalDateTime fechaHora, Prioridad nivelPrioridad) {
        this.idSolicitud = idSolicitud;
        this.cliente = cliente;
        this.tipoServicio = tipoServicio;
        this.estadoSolicitud = estadoSolicitud;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
        this.fechaHora = fechaHora;
        this.nivelPrioridad = nivelPrioridad;
    }

    public String getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(String idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public String getEstadoSolicitud() {
        return estadoSolicitud;
    }

    public void setEstadoSolicitud(String estadoSolicitud) {
        this.estadoSolicitud = estadoSolicitud;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Prioridad getNivelPrioridad() {
        return nivelPrioridad;
    }

    public void setNivelPrioridad(Prioridad nivelPrioridad) {
        this.nivelPrioridad = nivelPrioridad;
    }

    public UnidadServicio getUnidadAsignada() {
        return unidadAsignada;
    }

    public void setUnidadAsignada(UnidadServicio unidadAsignada) {
        this.unidadAsignada = unidadAsignada;
    }

    public Tecnico getTecnicoAsignado() {
        return tecnicoAsignado;
    }

    public void setTecnicoAsignado(Tecnico tecnicoAsignado) {
        this.tecnicoAsignado = tecnicoAsignado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    public void asignarRecursos(UnidadServicio unidadAsignada, Tecnico tecnicoAsignado) {
        this.unidadAsignada = unidadAsignada;
        this.tecnicoAsignado = tecnicoAsignado;
    }

    public void marcarComoAtendida() {
        this.estadoSolicitud = "Atendida";
    }

    @Override
    public String toString() {
        return "SolicitudServicio [ID=" + idSolicitud + ", Cliente=" + cliente.getNombre() + 
               ", Tipo=" + tipoServicio + ", Estado=" + estadoSolicitud + "]";
    }
}