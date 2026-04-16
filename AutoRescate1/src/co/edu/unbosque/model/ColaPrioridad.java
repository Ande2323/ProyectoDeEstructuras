package co.edu.unbosque.model;

public class ColaPrioridad<T> {

    private Nodo<T> frente;
    private Nodo<T> fin;
    private int tamaño;
    private ListaEnlazada<T>[] niveles;
    private int cantidadNiveles;

    public ColaPrioridad() {
        this(2);
    }

    @SuppressWarnings("unchecked")
    public ColaPrioridad(int numNiveles) {
        this.cantidadNiveles = numNiveles;
        this.tamaño = 0;
        this.niveles = (ListaEnlazada<T>[]) new ListaEnlazada[numNiveles];
        for (int i = 0; i < numNiveles; i++) {
            niveles[i] = new ListaEnlazada<T>();
        }
    }

    public void encolar(T dato, int nivelPrioridad) {
        if (nivelPrioridad >= 0 && nivelPrioridad < cantidadNiveles) {
            niveles[nivelPrioridad].insertarAlFinal(dato);
            Nodo<T> nuevo = new Nodo<T>(dato);
            if (this.fin == null) { 
                this.frente = nuevo;
            }
            
            this.fin = nuevo; 
            this.tamaño++;
        }
    }

    public T desencolar() {
        for (int i = 0; i < cantidadNiveles; i++) {
            if (!niveles[i].estaVacia()) {
                T dato = niveles[i].obtener(0);
                this.frente = new Nodo<T>(dato);
                T datoExtraido = this.frente.getDato();
                
                niveles[i].eliminar(datoExtraido);
                this.tamaño--;

                if (this.tamaño == 0) {
                    this.frente = null;
                    this.fin = null;
                } else {
                    for (int j = 0; j < cantidadNiveles; j++) {
                        if (!niveles[j].estaVacia()) {
                            this.frente = new Nodo<T>(niveles[j].obtener(0));
                            break;
                        }
                    }
                }
                return datoExtraido;
            }
        }
        return null;
    }

    public boolean estaVacia() {
        return this.fin == null;
    }
}