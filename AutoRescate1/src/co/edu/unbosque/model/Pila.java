package co.edu.unbosque.model;

public class Pila<T> {
    private Nodo<T> tope;
    private int tamano;

    public Pila() {
        this.tope = null;
        this.tamano = 0;
    }

    public void push(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        nuevo.setSiguiente(tope);
        tope = nuevo;
        tamano++;
    }

    public T pop() {
        if (estaVacia()) return null;
        T dato = tope.getDato();
        tope = tope.getSiguiente();
        tamano--;
        return dato;
    }

    public T peek() {
        if(estaVacia()) {
        	return null;
        }
        return tope.getDato();
    }

    public int getTamaño() {
    	return tamano; 
    }
    
    public boolean estaVacia() { 
    	if(tope==null) {
    		return true;
    	} 
    	return false;
    }
    
 
}
