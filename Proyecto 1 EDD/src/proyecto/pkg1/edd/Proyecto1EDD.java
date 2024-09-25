/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto.pkg1.edd;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author mainp
 */
public class Proyecto1EDD {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Estacion> estaciones = new ArrayList<>();

        System.out.println("Ingrese la cantidad de estaciones que desea agregar:");
        int cantidadEstaciones = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        for (int i = 0; i < cantidadEstaciones; i++) {
            System.out.println("Ingrese el nombre de la estación " + (i + 1) + ":");
            String nombre = scanner.nextLine();
            System.out.println("Ingrese la línea de la estación " + (i + 1) + ":");
            String linea = scanner.nextLine();
            System.out.println("¿Es un área comercial? (true/false)");
            boolean areaComercial = scanner.nextBoolean();
            scanner.nextLine(); // Consumir el salto de línea

            Estacion estacion = new Estacion(nombre, linea);
            estacion.setAreaComercial(areaComercial);

            System.out.println("Ingrese las estaciones que lo apuntan (separadas por coma):");
            String[] quienesLoApuntan = scanner.nextLine().split(",");
            List<Estacion> listaQuienesLoApuntan = new ArrayList<>();
            for (String quienLoApunta : quienesLoApuntan) {
                listaQuienesLoApuntan.add(new Estacion(quienLoApunta.trim(), ""));
            }
            estacion.setQuienesLoApuntan(listaQuienesLoApuntan);

            System.out.println("Ingrese las estaciones a las que apunta (separadas por coma):");
            String[] aQuienesApunta = scanner.nextLine().split(",");
            List<Estacion> listaAQuienesApunta = new ArrayList<>();
            for (String aQuienApunta : aQuienesApunta) {
                listaAQuienesApunta.add(new Estacion(aQuienApunta.trim(), ""));
            }
            estacion.setAQuienesApunta(listaAQuienesApunta);

            estaciones.add(estacion);
        }

        System.out.println("Estaciones creadas:");
        for (Estacion estacion : estaciones) {
            System.out.println("Nombre: " + estacion.getNombre());
            System.out.println("Línea: " + estacion.getLinea());
            System.out.println("Área comercial: " + estacion.isAreaComercial());
            System.out.println("Apunta a:");
            for (Estacion apunta : estacion.getAQuienesApunta()) {
                System.out.println(apunta.getNombre());
            }
            System.out.println("Es apuntada por:");
            for (Estacion loApunta : estacion.getQuienesLoApuntan()) {
                System.out.println(loApunta.getNombre());
            }
            System.out.println();
        }
    }
}
