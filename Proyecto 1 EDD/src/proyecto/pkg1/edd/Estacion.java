/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkg1.edd;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Clase padre que representa una estacion de tren con sus atributos:
 * 1- nombre de la estacion
 * 2- Linea
 * 3- quienes lo apuntan
 * 4- a quien apunta
 * 5- es un area comercias?
 * 
 */
public class Estacion {
    private String nombre;
    private String linea;
    private List<Estacion> quienesLoApuntan;
    private List<Estacion> aQuienesApunta;
    private boolean areaComercial;

    public Estacion(String nombre, String linea) {
        this.nombre = nombre;
        this.linea = linea;
        this.quienesLoApuntan = new ArrayList<>();
        this.aQuienesApunta = new ArrayList<>();

    }

    // Getters y setters
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

    public List<Estacion> getQuienesLoApuntan() {
        return quienesLoApuntan;
    }

    public void setQuienesLoApuntan(List<Estacion> quienesLoApuntan) {
        this.quienesLoApuntan = quienesLoApuntan;
    }

    public List<Estacion> getAQuienesApunta() {
        return aQuienesApunta;
    }

    public void setAQuienesApunta(List<Estacion> aQuienesApunta) {
        this.aQuienesApunta = aQuienesApunta;
    }

    public boolean isAreaComercial() {
        return areaComercial;
    }

    public void setAreaComercial(boolean areaComercial) {
        this.areaComercial = areaComercial;
    }
}