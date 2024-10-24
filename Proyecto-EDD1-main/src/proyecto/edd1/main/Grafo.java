/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.edd1.main;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

public class Grafo {
    private Nodo[] nodos; // Arreglo de nodos
    private int numNodos; // Número actual de nodos en el grafo
    private int[][] listaAdyacencia; // Matriz de adyacencia

    private Graph graphStream; // Grafo para visualización en GraphStream

    // Constructor
    public Grafo(int maxNodos) {
        this.nodos = new Nodo[maxNodos];
        this.numNodos = 0;
        this.listaAdyacencia = new int[maxNodos][maxNodos];
        this.graphStream = new SingleGraph("Grafo de Transporte");
    }

    // Método para agregar un nodo
    public void agregarVertice(String nombre, String linea) {
        if (!existeNodo(nombre)) {
            nodos[numNodos] = new Nodo(nombre, linea);
            graphStream.addNode(nombre).addAttribute("ui.label", nombre);
            numNodos++;
        }
    }

    // Método para agregar una arista (conexión)
    public void agregarArista(String origen, String destino) {
    int idxOrigen = obtenerIndice(origen);
    int idxDestino = obtenerIndice(destino);

    if (idxOrigen != -1 && idxDestino != -1) {
        Nodo nodoOrigen = nodos[idxOrigen];
        Nodo nodoDestino = nodos[idxDestino];

        // Verifica que ambas estaciones pertenezcan a la misma línea
        if (nodoOrigen.getLinea().equals(nodoDestino.getLinea())) {
            // Agrega la arista en la matriz de adyacencia si no existe
            if (listaAdyacencia[idxOrigen][idxDestino] == 0) {
                listaAdyacencia[idxOrigen][idxDestino] = 1;
                listaAdyacencia[idxDestino][idxOrigen] = 1;

                String edgeId = origen + "-" + destino;
                if (graphStream.getEdge(edgeId) == null) {
                    graphStream.addEdge(edgeId, origen, destino);
                }
            }
        } else {
            System.out.println("No se permite la conexión entre " + origen + " y " + destino 
                + " porque pertenecen a diferentes líneas. " + 
                origen + " es de la línea " + nodoOrigen.getLinea() + 
                " y " + destino + " es de la línea " + nodoDestino.getLinea());
        }
    } else {
        System.out.println("Uno o ambos nodos no existen: " + origen + ", " + destino);
    }
}

    // Método para verificar si un nodo ya existe
    private boolean existeNodo(String nombre) {
        for (int i = 0; i < numNodos; i++) {
            if (nodos[i] != null && nodos[i].getNombre().equals(nombre)) {
                return true;
            }
        }
        return false;
    }

    // Método para obtener el índice de un nodo por nombre
    private int obtenerIndice(String nombre) {
        for (int i = 0; i < numNodos; i++) {
            if (nodos[i] != null && nodos[i].getNombre().equals(nombre)) {
                return i;
            }
        }
        return -1;
    }

    // Método para visualizar el grafo
    public void visualizarGrafo() {
        graphStream.display();
    }

    // Método para cargar el grafo desde la lista de nodos con manejo de conexiones entre líneas
  public void cargarGrafoDesdeJSON(Nodo[] nodosImportados) {
    if (nodosImportados == null) {
        System.out.println("Error: nodosImportados es null.");
        return;
    }

    for (int i = 0; i < nodosImportados.length; i++) {
        if (nodosImportados[i] != null) {
            agregarVertice(nodosImportados[i].getNombre(), nodosImportados[i].getLinea());
        }
    }
    for (int i = 0; i < nodosImportados.length; i++) {
        if (nodosImportados[i] != null) {
            for (int j = 0; j < nodosImportados[i].getConexionIndex(); j++) {
                Nodo conexion = nodosImportados[i].getConexiones()[j];
                if (conexion != null) {
                    agregarArista(nodosImportados[i].getNombre(), conexion.getNombre());
                }
            }
        }
    }
}


    // Método para imprimir la lista de adyacencia
    public void imprimirListaAdyacencia() {
        System.out.println("Lista de Adyacencia:");
        for (int i = 0; i < numNodos; i++) {
            if (nodos[i] != null) {
                System.out.print(nodos[i].getNombre() + ": ");
                for (int j = 0; j < numNodos; j++) {
                    if (listaAdyacencia[i][j] == 1) {
                        System.out.print(nodos[j].getNombre() + " ");
                    }
                }
                System.out.println();
            }
        }
    }
}
