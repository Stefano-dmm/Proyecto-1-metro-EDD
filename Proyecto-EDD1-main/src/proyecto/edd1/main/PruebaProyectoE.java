/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto.edd1.main;

import proyecto.edd1.main.Nodo;
import proyecto.edd1.main.FileExplorer;

/**
 *
 * @author luisg
 */
public class PruebaProyectoE {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FileExplorer fileExplorer = new FileExplorer();
        Nodo[] nodos = fileExplorer.openFileExplorerAndCreateAdjacencyList();
        
       Grafo grafo= new Grafo(1000);
       grafo.cargarGrafoDesdeJSON(nodos);
       grafo.visualizarGrafo();
       if (nodos != null) {
            System.out.println("Lista de Adyacencia:");
            for (Nodo nodo : nodos) {
                System.out.println(nodo);
            }
        } else {
            System.out.println("No se pudo crear la lista de adyacencia.");
        }
    }
}

