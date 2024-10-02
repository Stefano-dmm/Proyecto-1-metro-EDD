/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package listamain;

/**
 *
 * @author mainp
 */

public class ListaCircular {
    Nodo cabeza;
    private Nodo cola;
    private int tamaño;

    public ListaCircular() {
        cabeza = null;
        cola = null;
        tamaño = 0;
    }
    
    public void mostrar() {
        if (cabeza != null) {
            Nodo temp = cabeza;
            int contador = 1;
            do {
                System.out.println("Nodo " + contador + ": " + temp.campo1 + ", " + temp.campo2 + ", " + temp.campo3);
                temp = temp.siguiente;
                contador++;
            } while (temp != cabeza);
        }
    }
         
    public Nodo getCabeza() {
        return cabeza;
    }

    public void agregar(String campo1, String campo2, String campo3) {
        Nodo nuevoNodo = new Nodo(campo1, campo2, campo3);
        if (cabeza == null) {
            cabeza = nuevoNodo;
            cabeza.siguiente = cabeza;
        } else {
            Nodo actual = cabeza;
            while (actual.siguiente != cabeza) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
            nuevoNodo.siguiente = cabeza;
            cola = nuevoNodo;
        }
        tamaño++;
    }

    public void eliminar(String campo1, String campo2, String campo3) {
        if (cabeza == null) return;
        if (cabeza.campo1.equals(campo1) && cabeza.campo2.equals(campo2) && cabeza.campo3.equals(campo3)) {
            if (cabeza.siguiente == cabeza) {
                cabeza = null;
            } else {
                Nodo actual = cabeza;
                while (actual.siguiente != cabeza) {
                    actual = actual.siguiente;
                }
                actual.siguiente = cabeza.siguiente;
                cabeza = cabeza.siguiente;
            }
            tamaño--;
        } else {
            Nodo actual = cabeza;
            while (actual.siguiente != cabeza && !(actual.siguiente.campo1.equals(campo1) && actual.siguiente.campo2.equals(campo2) && actual.siguiente.campo3.equals(campo3))) {
                actual = actual.siguiente;
            }
            if (actual.siguiente != cabeza && actual.siguiente.campo1.equals(campo1) && actual.siguiente.campo2.equals(campo2) && actual.siguiente.campo3.equals(campo3)) {
                actual.siguiente = actual.siguiente.siguiente;
                tamaño--;
            }
        }
    }

    public boolean contiene(String campo1, String campo2, String campo3) {
        Nodo actual = cabeza;
        do {
            if (actual.campo1.equals(campo1) && actual.campo2.equals(campo2) && actual.campo3.equals(campo3)) return true;
            actual = actual.siguiente;
        } while (actual != cabeza);
        return false;
    }

    public int tamaño() {
        return tamaño;
    }

    public void editarNodo(String campo1, String campo2, String campo3, int campoEditar, String nuevoValor) {
        Nodo actual = cabeza;
        do {
            if (actual.campo1.equals(campo1) && actual.campo2.equals(campo2) && actual.campo3.equals(campo3)) {
                switch (campoEditar) {
                    case 1:
                        actual.campo1 = nuevoValor;
                        break;
                    case 2:
                        actual.campo2 = nuevoValor;
                        break;
                    case 3:
                        actual.campo3 = nuevoValor;
                        break;
                    default:
                        System.out.println("Campo no válido");
                }
                return;
            }
            actual = actual.siguiente;
        } while (actual != cabeza);
    }

    private static class Nodo {
        String campo1;
        String campo2;
        String campo3;
        Nodo siguiente;

        Nodo(String campo1, String campo2, String campo3) {
            this.campo1 = campo1;
            this.campo2 = campo2;
            this.campo3 = campo3;
            this.siguiente = null;
        }

        boolean equals(String campo1, String campo2, String campo3) {
            return this.campo1.equals(campo1) && this.campo2.equals(campo2) && this.campo3.equals(campo3);
        }
    }
}