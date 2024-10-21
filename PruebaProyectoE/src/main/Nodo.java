/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

class Nodo {
    String nombre;
    Nodo[] conexiones;
    int conexionIndex;

    public Nodo(String nombre) {
        this.nombre = nombre;
        this.conexiones = new Nodo[10];  // Adjust size as needed
        this.conexionIndex = 0;
    }

    public void agregarConexion(Nodo nodo) {
        if (conexionIndex < conexiones.length) {
            conexiones[conexionIndex++] = nodo;
        } else {
            Nodo[] newConexiones = new Nodo[conexiones.length * 2];
            System.arraycopy(conexiones, 0, newConexiones, 0, conexiones.length);
            conexiones = newConexiones;
            conexiones[conexionIndex++] = nodo;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(nombre).append(" -> [");
        for (int i = 0; i < conexionIndex; i++) {
            sb.append(conexiones[i].nombre);
            if (i < conexionIndex - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
