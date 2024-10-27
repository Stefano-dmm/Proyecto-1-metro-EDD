/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto.edd1.main;

/**
 *  El main del programa.
 * @author luisg
 */
public class PruebaProyectoE {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FileExplorer fileExplorer = new FileExplorer();
        Nodo[] nodos = fileExplorer.openFileExplorerAndCreateAdjacencyList();
        
        Grafo grafo = new Grafo(1000);
        grafo.cargarGrafoDesdeJSON(nodos);
        
        // Imprimir la lista de adyacencia al inicio
        System.out.println("Lista de Adyacencia:");
        System.out.println(grafo.obtenerListaAdyacencia()); // Imprimir la lista de adyacencia

        // Crear y mostrar la ventana de EditorDeGrafo pasando el grafo
        EditorDeGrafo editorDeGrafo = new EditorDeGrafo(grafo, grafo.getGraphStream()); // Asegúrate de pasar el grafo aquí
        editorDeGrafo.setVisible(true);
        
        // Visualizar el grafo
        grafo.visualizarGrafo();
    }
}
