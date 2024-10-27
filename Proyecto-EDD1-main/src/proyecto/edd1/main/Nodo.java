/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.edd1.main;

/**
 * Clase que representa un nodo en el grafo del sistema de metro.
 * Contiene información sobre el nombre de la estaci&oacute;n, sus conexiones y su estado.
 * @author Luis Garnica
 * @author Stefano DiMichelangelo
 * @version 27/10/2024
 */
public class Nodo {
    private String nombre;
    private Nodo[] conexiones;
    private int conexionIndex;
    private boolean areaComercial; // Nuevo atributo para indicar si es un área comercial
    private boolean sucursal;  // Nuevo atributo

    /**
     * Constructor del nodo.
     * @param nombre Nombre de la estación
     */
    public Nodo(String nombre) {
        this.nombre = nombre;
        this.conexiones = new Nodo[10]; // Tamaño inicial, puede crecer dinámicamente
        this.conexionIndex = 0;
        this.areaComercial = false; // Inicialmente no es un área comercial
        this.sucursal = false;  // Inicializado en falso por defecto
    }

    /**
     * Agrega una conexi&oacute;n a otro nodo.
     * @param nodo Nodo con el que se establece la conexi&oacute;n
     */
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
            sb.append(conexiones[i].getNombre());
            if (i < conexionIndex - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    // Getters and setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Nodo[] getConexiones() {
        return conexiones;
    }

    public void setConexiones(Nodo[] conexiones) {
        this.conexiones = conexiones;
    }

    public int getConexionIndex() {
        return conexionIndex;
    }

    public void setConexionIndex(int conexionIndex) {
        this.conexionIndex = conexionIndex;
    }

    public boolean isAreaComercial() {
        return areaComercial;
    }

    public void setAreaComercial(boolean areaComercial) {
        this.areaComercial = areaComercial;
    }

    public boolean isSucursal() {
        return sucursal;
    }

    public void setSucursal(boolean sucursal) {
        this.sucursal = sucursal;
    }
}
