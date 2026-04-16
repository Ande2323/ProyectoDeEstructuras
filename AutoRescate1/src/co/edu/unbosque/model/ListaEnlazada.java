package co.edu.unbosque.model;

import java.io.Serializable;

public class ListaEnlazada<T> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
    private Nodo<T> cabeza;
    private int tamaño;

    public ListaEnlazada() {
        this.cabeza = null;
        this.tamaño = 0;
    }

    public void insertarAlFinal(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (estaVacia()) {
            cabeza = nuevo;
        } else {
            Nodo<T> temp = cabeza;
            while (temp.getSiguiente() != null) {
                temp = temp.getSiguiente();
            }
            temp.setSiguiente(nuevo);
        }
        tamaño++;
    }
    
    public boolean eliminar(T dato) {
        if (estaVacia()) 
        	return false;

        if (cabeza.getDato().equals(dato)) {
            cabeza = cabeza.getSiguiente();
            tamaño--;
            return true;
        }

        Nodo<T> actual = cabeza;
        while (actual.getSiguiente() != null) {
            if (actual.getSiguiente().getDato().equals(dato)) {
                actual.setSiguiente(actual.getSiguiente().getSiguiente());
                tamaño--;
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

  
    public void eliminarPrimero() {
        if (cabeza != null) {
            cabeza = cabeza.getSiguiente();
            tamaño--;
        }
    }

    public T obtener(int indice) {
        if (indice < 0 || indice >= tamaño) 
        	return null;
        Nodo<T> temp = cabeza;
        for (int i = 0; i < indice; i++) temp = temp.getSiguiente();
        return temp.getDato();
    }

    public int getTamaño() { 
    	return tamaño; 
    }
    
    public boolean estaVacia() { 
    	return cabeza == null;
    }
    
    public Nodo<T> getCabeza() {
        return cabeza;
    }
}