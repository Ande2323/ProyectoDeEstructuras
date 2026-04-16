package co.edu.unbosque.model;

import java.util.UUID;

public class GestorOperativo {

    private ListaEnlazada<UnidadServicio> unidades;
    private ListaEnlazada<Tecnico> tecnicos;
    private ListaEnlazada<Cliente> clientes;
    private ColaPrioridad<SolicitudServicio> colaPendientes;
    private ListaEnlazada<SolicitudServicio> serviciosActivos;
    private Pila<Kit> pilaKitsRevision;
    private Pila<Repuesto> pilaRepuestos;
    private Pila<Operacion<?>> historialUndo;

    public GestorOperativo() {
        this.unidades = new ListaEnlazada<>();
        this.tecnicos = new ListaEnlazada<>();
        this.clientes = new ListaEnlazada<>();
        this.colaPendientes = new ColaPrioridad<>(2);
        this.serviciosActivos = new ListaEnlazada<>();
        this.pilaKitsRevision = new Pila<>();
        this.pilaRepuestos = new Pila<>();
        this.historialUndo = new Pila<>();
    }

    public void registrarUnidad(UnidadServicio u) {
        unidades.insertarAlFinal(u);
    }

    public void registrarTecnico(Tecnico t) {
        tecnicos.insertarAlFinal(t);
    }

    public void registrarCliente(Cliente c) {
        clientes.insertarAlFinal(c);
    }

    public void ingresarSolicitud(SolicitudServicio solicitud) {
        int nivel = (solicitud.getNivelPrioridad() == Prioridad.CRITICA) ? 0 : 1;
        colaPendientes.encolar(solicitud, nivel);
    }

    public void asignarSiguienteServicio() {
        if (colaPendientes.estaVacia()) {
            return;
        }

        SolicitudServicio solicitud = colaPendientes.desencolar();
        Tecnico tecnicoEncontrado = null;
        UnidadServicio unidadEncontrada = null;

        for (int i = 0; i < tecnicos.getTamaño(); i++) {
            Tecnico t = tecnicos.obtener(i);
            if (t.isEstaDisponible() && t.getZona().equalsIgnoreCase(solicitud.getUbicacion())) {
                tecnicoEncontrado = t;
                break;
            }
        }

        for (int i = 0; i < unidades.getTamaño(); i++) {
            UnidadServicio u = unidades.obtener(i);
            if (u.isEstaDisponible() && u.getZona().equalsIgnoreCase(solicitud.getUbicacion())) {
                unidadEncontrada = u;
                break;
            }
        }

        if (tecnicoEncontrado != null && unidadEncontrada != null) {
            solicitud.asignarRecursos(unidadEncontrada, tecnicoEncontrado);
            tecnicoEncontrado.setEstaDisponible(false);
            unidadEncontrada.setEstaDisponible(false);
            serviciosActivos.insertarAlFinal(solicitud);

            historialUndo.push(new Operacion<>(
                UUID.randomUUID().toString(),
                TipoOperacion.ASIGNACION_RECURSOS,
                solicitud,
                "Pendiente",
                "En curso"
            ));
        } else {
            ingresarSolicitud(solicitud);
        }
    }

    public void finalizarServicio(String observaciones) {
        if (serviciosActivos.estaVacia()) {
            return;
        }

        SolicitudServicio solicitud = serviciosActivos.obtener(0);
        
        if (solicitud != null) {
            solicitud.setObservaciones(observaciones);
            solicitud.marcarComoAtendida();
            
            if (solicitud.getTecnicoAsignado() != null) {
                solicitud.getTecnicoAsignado().setEstaDisponible(true);
            }
            if (solicitud.getUnidadAsignada() != null) {
                solicitud.getUnidadAsignada().setEstaDisponible(true);
            }

            serviciosActivos.eliminar(solicitud);

            historialUndo.push(new Operacion<>(
                UUID.randomUUID().toString(),
                TipoOperacion.CAMBIO_ESTADO_SOLICITUD,
                solicitud,
                "En curso",
                "Atendida"
            ));
        }
    }

    public void gestionarKit(Kit k) {
        pilaKitsRevision.push(k);
    }

    public void gestionarRepuesto(Repuesto r) {
        pilaRepuestos.push(r);
    }

    public void deshacerUltimaOperacion() {
        if (historialUndo.estaVacia()) {
            return;
        }

        Operacion<?> op = historialUndo.pop();
        Object afectado = op.getObjetoAfectado();

        if (op.getTipo() == TipoOperacion.ASIGNACION_RECURSOS && afectado instanceof SolicitudServicio) {
            SolicitudServicio s = (SolicitudServicio) afectado;
            if (s.getTecnicoAsignado() != null) s.getTecnicoAsignado().setEstaDisponible(true);
            if (s.getUnidadAsignada() != null) s.getUnidadAsignada().setEstaDisponible(true);
            s.setEstadoSolicitud((String) op.getEstadoAnterior());
            serviciosActivos.eliminar(s);
            ingresarSolicitud(s);
        } else if (op.getTipo() == TipoOperacion.CAMBIO_ESTADO_SOLICITUD && afectado instanceof SolicitudServicio) {
            SolicitudServicio s = (SolicitudServicio) afectado;
            if (s.getTecnicoAsignado() != null) s.getTecnicoAsignado().setEstaDisponible(false);
            if (s.getUnidadAsignada() != null) s.getUnidadAsignada().setEstaDisponible(false);
            s.setEstadoSolicitud((String) op.getEstadoAnterior());
            serviciosActivos.insertarAlFinal(s);
        }
    }

    public void generarReporteDiarioCSV() {
        
    }
}