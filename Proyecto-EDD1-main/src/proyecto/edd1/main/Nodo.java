/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.edd1.main;

class Nodo {
   private String nombre;
    private String linea;
    private Nodo[] conexiones;
    private int conexionIndex;

    public Nodo(String nombre, String linea) {
        this.nombre = nombre;
        this.linea = linea;
        this.conexiones = new Nodo[10]; // Initial size, can grow dynamically
        this.conexionIndex = 0;
    }

    // Agrega una conexión entre nodos (vértices)
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

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
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
}
