package proyecto.edd1.main;

/**
 * Implementación de una estructura de datos Cola para el recorrido BFS.
 * Utiliza una lista enlazada para almacenar los elementos.
 */
public class Cola {
    /**
     * Clase interna que representa un nodo en la cola.
     */
    private class Nodo {
        int dato;
        Nodo siguiente;
        
        /**
         * Constructor del nodo de la cola.
         * @param dato Valor a almacenar en el nodo
         */
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
    
    /**
     * Agrega un elemento al final de la cola.
     * @param dato Elemento a agregar
     */
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
    
    /**
     * Remueve y retorna el elemento al frente de la cola.
     * @return El elemento al frente de la cola, o -1 si está vacía
     */
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
