/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.edd1.main;

class Nodo {
    private String nombre;
    private Nodo[] conexiones;
    private int conexionIndex;

    public Nodo(String nombre) {
        this.nombre = nombre;
        this.conexiones = new Nodo[10];  // Adjust size as needed
        this.conexionIndex = 0;
    }

    public void agregarConexion(Nodo nodo) {
        if (getConexionIndex() < getConexiones().length) {
            getConexiones()[conexionIndex++] = nodo;
        } else {
            Nodo[] newConexiones = new Nodo[getConexiones().length * 2];
            System.arraycopy(getConexiones(), 0, newConexiones, 0, getConexiones().length);
            setConexiones(newConexiones);
            getConexiones()[conexionIndex++] = nodo;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getNombre()).append(" -> [");
        for (int i = 0; i < getConexionIndex(); i++) {
            sb.append(getConexiones()[i].getNombre());
            if (i < getConexionIndex() - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the conexiones
     */
    public Nodo[] getConexiones() {
        return conexiones;
    }

    /**
     * @param conexiones the conexiones to set
     */
    public void setConexiones(Nodo[] conexiones) {
        this.conexiones = conexiones;
    }

    /**
     * @return the conexionIndex
     */
    public int getConexionIndex() {
        return conexionIndex;
    }

    /**
     * @param conexionIndex the conexionIndex to set
     */
    public void setConexionIndex(int conexionIndex) {
        this.conexionIndex = conexionIndex;
    }
}
