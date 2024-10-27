package proyecto.edd1.main;

public class Cola {
    private class Nodo {
        int dato;
        Nodo siguiente;
        
        public Nodo(int dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }
    
    private Nodo frente;
    private Nodo ultimo;
    
    public Cola() {
        frente = null;
        ultimo = null;
    }
    
    public void encolar(int dato) {
        Nodo nuevoNodo = new Nodo(dato);
        if (estaVacia()) {
            frente = nuevoNodo;
            ultimo = nuevoNodo;
        } else {
            ultimo.siguiente = nuevoNodo;
            ultimo = nuevoNodo;
        }
    }
    
    public int desencolar() {
        if (estaVacia()) {
            return -1;
        }
        int dato = frente.dato;
        frente = frente.siguiente;
        if (frente == null) {
            ultimo = null;
        }
        return dato;
    }
    
    public boolean estaVacia() {
        return frente == null;
    }
}
