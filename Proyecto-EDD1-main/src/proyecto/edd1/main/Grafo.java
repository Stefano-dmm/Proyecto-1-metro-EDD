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

    private String recorridoNodos; // Atributo para almacenar el recorrido

    // Constructor
    public Grafo(int maxNodos) {
        this.nodos = new Nodo[maxNodos];
        this.numNodos = 0;
        this.listaAdyacencia = new int[maxNodos][maxNodos]; // Inicializar la matriz de adyacencia
        this.graphStream = new SingleGraph("Grafo de Transporte");
    }

    // Método para agregar un nodo
    public void agregarVertice(String nombre) { // Eliminar el parámetro 'linea'
        if (!existeNodo(nombre)) {
            nodos[numNodos] = new Nodo(nombre); // Eliminar el parámetro 'linea'
            getGraphStream().addNode(nombre).addAttribute("ui.label", nombre);
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

            // Verifica que ambas estaciones pertenezcan a la misma línea (eliminar esta verificación)
            // if (nodoOrigen.getLinea().equals(nodoDestino.getLinea())) {
            // Agrega la arista en la matriz de adyacencia si no existe
            if (listaAdyacencia[idxOrigen][idxDestino] == 0) {
                listaAdyacencia[idxOrigen][idxDestino] = 1;
                listaAdyacencia[idxDestino][idxOrigen] = 1;

                String edgeId = origen + "-" + destino;
                if (getGraphStream().getEdge(edgeId) == null) {
                    getGraphStream().addEdge(edgeId, origen, destino);
                }
            }

            // Agregar la conexión en el nodo
            nodoOrigen.agregarConexion(nodoDestino);
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

    public void agregarLinea(String estacion1, String estacion2) {
        if (estacion1 == null || estacion2 == null || estacion1.equals(estacion2)) {
            System.out.println("Error: Las estaciones deben ser diferentes y no nulas.");
            return;
        }

        // Asegurarse de que ambas estaciones existan
        agregarVertice(estacion1);
        agregarVertice(estacion2);

        // Agregar la conexión entre las estaciones
        agregarArista(estacion1, estacion2);

        System.out.println("Línea agregada entre " + estacion1 + " y " + estacion2);
    }

    private Nodo obtenerNodoPorNombre(String nombre) {
        for (Nodo nodo : nodos) {
            if (nodo != null && nodo.getNombre().equals(nombre)) {
                return nodo;
            }
        }
        return null;
    }


    public void eliminarEstacion(String nombre) {
        Nodo nodoAEliminar = obtenerNodoPorNombre(nombre);
        
        if (nodoAEliminar == null) {
            System.out.println("La estación " + nombre + " no existe.");
            return;
        }

        // Eliminar las conexiones que apuntan al nodo a eliminar
        for (int i = 0; i < numNodos; i++) {
            if (nodos[i] != null) {
                eliminarConexion(nodos[i], nodoAEliminar);
            }
        }

        // Eliminar el nodo del array de nodos
        for (int i = 0; i < numNodos; i++) {
            if (nodos[i] != null && nodos[i].getNombre().equals(nombre)) {
                nodos[i] = null;
                break;
            }
        }

        // Si estás usando GraphStream, elimina también el nodo del grafo visualizado
        if (getGraphStream().getNode(nombre) != null) {
            getGraphStream().removeNode(nombre);
        }

        System.out.println("La estación " + nombre + " ha sido eliminada.");
    }

        // Método auxiliar para eliminar la conexión hacia el nodo a eliminar
        private void eliminarConexion(Nodo origen, Nodo destino) {
            Nodo[] conexiones = origen.getConexiones();
            int conexionIndex = origen.getConexionIndex();

            for (int i = 0; i < conexionIndex; i++) {
                if (conexiones[i] != null && conexiones[i].getNombre().equals(destino.getNombre())) {
                    // Desplazar las conexiones para llenar el hueco
                    for (int j = i; j < conexionIndex - 1; j++) {
                        conexiones[j] = conexiones[j + 1];
                    }
                    conexiones[conexionIndex - 1] = null;
                    origen.setConexionIndex(conexionIndex - 1);
                    break;
                }
            }
        }

    // Método para obtener el índice de un nodo por nombre
    public int obtenerIndice(String nombre) {
        for (int i = 0; i < nodos.length; i++) {
            if (nodos[i] != null && nodos[i].getNombre().equals(nombre)) {
                return i;
            }
        }
        return -1; // Nodo no encontrado
    }

    // Método para visualizar el grafo
    public void visualizarGrafo() {
        getGraphStream().display();
    }

    // Método para cargar el grafo desde la lista de nodos con manejo de conexiones entre líneas
    public void cargarGrafoDesdeJSON(Nodo[] nodosImportados) {
        if (nodosImportados == null) {
            System.out.println("Error: nodosImportados es null.");
            return;
        }

        for (int i = 0; i < nodosImportados.length; i++) {
            if (nodosImportados[i] != null) {
                agregarVertice(nodosImportados[i].getNombre()); // Eliminar el parámetro 'linea'
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

    public void marcarAreasComercialesDFS(String nodoInicial, int distanciaMaxima) {
        Nodo nodoInicio = obtenerNodoPorNombre(nodoInicial);
        if (nodoInicio == null) {
            System.out.println("El nodo inicial no existe.");
            return;
        }

        boolean[] visitados = new boolean[nodos.length]; // Arreglo para marcar nodos visitados
        marcarComoAreaComercialDFS(nodoInicio, visitados, 0, distanciaMaxima, true, 0);

        // Imprimir nodos seleccionados
        imprimirNodosSeleccionados();
    }

    // Método modificado para marcar nodos como área comercial
    private void marcarComoAreaComercialDFS(Nodo nodo, boolean[] visitados, int distanciaActual, int distanciaMaxima, boolean esNodoInicial, int contador) {
        if (visitados[obtenerIndice(nodo.getNombre())]) {
            return; // Si ya se visitó el nodo, salir
        }

        visitados[obtenerIndice(nodo.getNombre())] = true; // Marcar el nodo como visitado

        // Marcar el nodo inicial como área comercial
        if (esNodoInicial) {
            nodo.setAreaComercial(true);
        } else {
            // Alternar entre área comercial y no comercial
            if (contador < distanciaMaxima) {
                nodo.setAreaComercial(false); // No marcar como área comercial
            } else {
                nodo.setAreaComercial(true); // Marcar como área comercial
            }
        }

        // Incrementar el contador si no es el nodo inicial
        if (!esNodoInicial) {
            contador++;
        }

        // Recorrer la lista de adyacencia
        for (int i = 0; i < nodos.length; i++) {
            if (listaAdyacencia[obtenerIndice(nodo.getNombre())][i] == 1 && !visitados[i]) {
                Nodo conexion = nodos[i];
                if (conexion != null) {
                    marcarComoAreaComercialDFS(conexion, visitados, distanciaActual + 1, distanciaMaxima, false, contador);
                }
            }
        }
    }

    // Método para imprimir nodos seleccionados
    private void imprimirNodosSeleccionados() {
        System.out.println("Nodos seleccionados como áreas comerciales:");
        for (Nodo nodo : nodos) {
            if (nodo != null && nodo.isAreaComercial()) {
                System.out.println(nodo.getNombre());
            }
        }
    }

    public void marcarAreasComercialesBFS(String nodoInicial, int distanciaMaxima) {
        Nodo nodoInicio = obtenerNodoPorNombre(nodoInicial);
        if (nodoInicio == null) {
            System.out.println("El nodo inicial no existe.");
            return;
        }

        boolean[] visitados = new boolean[nodos.length]; // Arreglo para marcar nodos visitados
        Nodo[] cola = new Nodo[nodos.length]; // Cola para BFS
        int frente = 0, fin = 0; // Índices para el frente y el fin de la cola

        cola[fin++] = nodoInicio; // Agregar el nodo inicial a la cola
        visitados[obtenerIndice(nodoInicio.getNombre())] = true; // Marcar como visitado
        nodoInicio.setAreaComercial(true); // Marcar como área comercial

        int contador = 0; // Contador para alternar áreas comerciales

        while (frente < fin) {
            int size = fin - frente; // Tamaño de la cola en este nivel
            for (int i = 0; i < size; i++) {
                Nodo nodoActual = cola[frente++]; // Sacar el nodo del frente de la cola

                // Recorrer la lista de adyacencia
                for (int j = 0; j < nodos.length; j++) {
                    if (listaAdyacencia[obtenerIndice(nodoActual.getNombre())][j] == 1 && !visitados[j]) {
                        visitados[j] = true; // Marcar como visitado
                        Nodo conexion = nodos[j];
                        cola[fin++] = conexion; // Agregar a la cola

                        // Alternar entre área comercial y no comercial
                        if (contador < distanciaMaxima) {
                            conexion.setAreaComercial(false); // No marcar como área comercial
                        } else {
                            conexion.setAreaComercial(true); // Marcar como área comercial
                        }
                    }
                }
            }
            contador++; // Incrementar el contador después de procesar un nivel
        }

        // Imprimir nodos seleccionados
        imprimirNodosSeleccionados();

        System.out.println("Áreas comerciales marcadas a partir de " + nodoInicial);
    }

    // Método para obtener los nodos
    public Nodo[] getNodos() {
        return nodos; // Devuelve el arreglo de nodos
    }

    private Nodo[] nodosSeleccionados = new Nodo[100]; // Tamaño máximo de nodos seleccionados
    private int contadorSeleccionados = 0; // Contador para los nodos seleccionados

    public void seleccionarNodosConSaltoDFS(String nodoInicial, int salto) {
        Nodo nodoInicio = obtenerNodoPorNombre(nodoInicial);
        if (nodoInicio == null) {
            System.out.println("El nodo inicial no existe.");
            return;
        }

        boolean[] visitados = new boolean[nodos.length]; // Arreglo para marcar nodos visitados
        contadorSeleccionados = 0; // Reiniciar el contador
        StringBuilder recorrido = new StringBuilder(); // Para almacenar el recorrido

        seleccionarNodosConSaltoDFS(nodoInicio, visitados, 0, salto, recorrido);

        // Imprimir los nodos seleccionados
        System.out.println("Nodos seleccionados:");
        for (int i = 0; i < contadorSeleccionados; i++) {
            System.out.println(nodosSeleccionados[i].getNombre());
        }

        // Almacenar el recorrido en el grafo para mostrarlo en el JTextArea
        this.recorridoNodos = recorrido.toString(); // Suponiendo que tienes un atributo para almacenar el recorrido
    }

    private void seleccionarNodosConSaltoDFS(Nodo nodo, boolean[] visitados, int distanciaActual, int salto, StringBuilder recorrido) {
        if (visitados[obtenerIndice(nodo.getNombre())]) {
            return; // Si ya se visitó el nodo, salir
        }

        visitados[obtenerIndice(nodo.getNombre())] = true; // Marcar el nodo como visitado
        nodosSeleccionados[contadorSeleccionados++] = nodo; // Agregar el nodo al arreglo
        nodo.setAreaComercial(true); // Marcar como área comercial

        // Agregar información del nodo al recorrido
        recorrido.append("Nombre: ").append(nodo.getNombre()).append("\n");
        recorrido.append("Línea: ").append(nodo.getNombre()).append("\n");
        recorrido.append("Área Comercial: ").append(nodo.isAreaComercial() ? "Sí" : "No").append("\n");
        recorrido.append("Conexiones: ");
        for (int j = 0; j < nodo.getConexionIndex(); j++) {
            recorrido.append(nodo.getConexiones()[j].getNombre());
            if (j < nodo.getConexionIndex() - 1) {
                recorrido.append(", ");
            }
        }
        recorrido.append("\n\n"); // Espacio entre nodos

        // Recorrer las conexiones
        for (Nodo conexion : nodo.getConexiones()) {
            if (conexion != null) {
                // Solo agregar el nodo si se cumple la condición de salto
                if (distanciaActual % (salto + 1) == 0) {
                    conexion.setAreaComercial(true); // Marcar como área comercial
                }
                seleccionarNodosConSaltoDFS(conexion, visitados, distanciaActual + 1, salto, recorrido);
            }
        }
    }

    public int getContadorSeleccionados() {
        return contadorSeleccionados; // Devuelve el contador de nodos seleccionados
    }

    public Nodo[] getNodosSeleccionados() {
        return nodosSeleccionados; // Devuelve el arreglo de nodos seleccionados
    }

    public void seleccionarNodosConSaltoBFS(String nodoInicial, int salto) {
        Nodo nodoInicio = obtenerNodoPorNombre(nodoInicial);
        if (nodoInicio == null) {
            System.out.println("El nodo inicial no existe.");
            return;
        }

        boolean[] visitados = new boolean[nodos.length]; // Arreglo para marcar nodos visitados
        contadorSeleccionados = 0; // Reiniciar el contador
        Nodo[] cola = new Nodo[nodos.length]; // Cola para BFS
        int frente = 0, fin = 0; // Índices para el frente y el fin de la cola

        cola[fin++] = nodoInicio; // Agregar el nodo inicial a la cola
        visitados[obtenerIndice(nodoInicio.getNombre())] = true; // Marcar como visitado

        int distanciaActual = 0;

        while (frente < fin) {
            int size = fin - frente; // Tamaño de la cola en este nivel
            for (int i = 0; i < size; i++) {
                Nodo nodoActual = cola[frente++]; // Sacar el nodo del frente de la cola

                // Agregar el nodo a la lista si se cumple la condición de salto
                if (distanciaActual % (salto + 1) == 0) {
                    if (contadorSeleccionados < nodosSeleccionados.length) {
                        nodosSeleccionados[contadorSeleccionados++] = nodoActual; // Agregar el nodo al arreglo
                    }
                }

                // Recorrer las conexiones
                for (Nodo conexion : nodoActual.getConexiones()) {
                    if (conexion != null && !visitados[obtenerIndice(conexion.getNombre())]) {
                        visitados[obtenerIndice(conexion.getNombre())] = true; // Marcar como visitado
                        cola[fin++] = conexion; // Agregar a la cola
                    }
                }
            }
            distanciaActual++;
        }

        // Marcar los nodos seleccionados como áreas comerciales
        marcarNodosComoAreaComercial();

        // Imprimir los nodos seleccionados
        System.out.println("Nodos seleccionados:");
        for (int i = 0; i < contadorSeleccionados; i++) {
            System.out.println(nodosSeleccionados[i].getNombre());
        }
    }

    private void marcarNodosComoAreaComercial() {
        for (Nodo nodo : nodos) {
            if (nodo != null && nodo.isAreaComercial()) {
                marcarComoAreaComercial(nodo, new boolean[nodos.length], 0, 0);
            }
        }
    }

    public String getRecorridoNodos() {
        return recorridoNodos; // Devuelve el recorrido de nodos
    }

    // Método para obtener la lista de adyacencia como un String
    public String obtenerListaAdyacencia() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numNodos; i++) {
            if (nodos[i] != null) {
                sb.append(nodos[i].getNombre()).append(" -> ");
                for (int j = 0; j < nodos.length; j++) {
                    if (listaAdyacencia[i][j] == 1) {
                        sb.append(nodos[j].getNombre()).append(", ");
                    }
                }
                // Eliminar la última coma y espacio
                if (sb.length() > 0) {
                    sb.setLength(sb.length() - 2);
                }
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public void eliminarNodo(Nodo nodo) {
        for (int i = 0; i < numNodos; i++) {
            if (nodos[i] != null && nodos[i].equals(nodo)) {
                // Eliminar conexiones
                for (int j = 0; j < nodo.getConexionIndex(); j++) {
                    Nodo conexion = nodo.getConexiones()[j];
                    conexion.setConexionIndex(conexion.getConexionIndex() - 1); // Reducir el índice de conexiones
                }
                // Desplazar nodos
                for (int j = i; j < numNodos - 1; j++) {
                    nodos[j] = nodos[j + 1];
                }
                nodos[numNodos - 1] = null; // Limpiar el último nodo
                numNodos--;
                break;
            }
        }
    }

    private void marcarComoAreaComercial(Nodo nodo, boolean[] visitados, int distanciaActual, int distanciaMaxima) {
        if (visitados[obtenerIndice(nodo.getNombre())]) {
            return; // Si ya se visitó el nodo, salir
        }

        visitados[obtenerIndice(nodo.getNombre())] = true; // Marcar el nodo como visitado
        nodo.setAreaComercial(true); // Marcar como área comercial

        // Recorrer la lista de adyacencia
        for (int i = 0; i < nodos.length; i++) {
            if (listaAdyacencia[obtenerIndice(nodo.getNombre())][i] == 1 && !visitados[i]) {
                Nodo conexion = nodos[i];
                if (conexion != null) {
                    marcarComoAreaComercial(conexion, visitados, distanciaActual + 1, distanciaMaxima);
                }
            }
        }
    }

    public Graph getGraphStream() {
        return graphStream;
    }

    public void setGraphStream(Graph graphStream) {
        this.graphStream = graphStream;
    }

    public void pintarGrafoConDistancia(String nombreNodoInicial, int distancia) {
        // Ya no removemos los colores anteriores
        // for (org.graphstream.graph.Node node : graphStream.getEachNode()) {
        //     node.removeAttribute("ui.class");
        // }

        Nodo nodoInicial = obtenerNodoPorNombre(nombreNodoInicial);
        if (nodoInicial == null) {
            System.out.println("Nodo inicial no encontrado");
            return;
        }

        // Marcar el nodo inicial
        org.graphstream.graph.Node nodoGraphStream = graphStream.getNode(nombreNodoInicial);
        nodoGraphStream.setAttribute("ui.class", "inicial");

        // Lista para almacenar los nodos visitados y su distancia desde el inicial
        boolean[] visitados = new boolean[nodos.length];
        int[] distancias = new int[nodos.length];
        for (int i = 0; i < distancias.length; i++) {
            distancias[i] = Integer.MAX_VALUE;
        }

        int indiceInicial = obtenerIndice(nombreNodoInicial);
        distancias[indiceInicial] = 0;

        java.util.Queue<Integer> cola = new java.util.LinkedList<>();
        cola.offer(indiceInicial);
        visitados[indiceInicial] = true;

        while (!cola.isEmpty()) {
            int actual = cola.poll();
            
            for (int i = 0; i < nodos.length; i++) {
                if (listaAdyacencia[actual][i] == 1 && !visitados[i]) {
                    distancias[i] = distancias[actual] + 1;
                    visitados[i] = true;
                    cola.offer(i);
                    
                    if (distancias[i] <= distancia) {
                        org.graphstream.graph.Node nodoAdyacente = graphStream.getNode(nodos[i].getNombre());
                        nodoAdyacente.setAttribute("ui.class", "covertura");
                    }
                }
            }
        }
    }

    public void pintarGrafoDFS(String nombreNodoInicial, int cantidadNodos) {
        // Ya no removemos los colores anteriores
        // for (org.graphstream.graph.Node node : graphStream.getEachNode()) {
        //     node.removeAttribute("ui.class");
        // }

        Nodo nodoInicial = obtenerNodoPorNombre(nombreNodoInicial);
        if (nodoInicial == null) {
            System.out.println("Nodo inicial no encontrado");
            return;
        }

        // Marcar el nodo inicial
        org.graphstream.graph.Node nodoGraphStream = graphStream.getNode(nombreNodoInicial);
        nodoGraphStream.setAttribute("ui.class", "inicial");

        boolean[] visitados = new boolean[nodos.length];
        int[] nodosPintados = new int[1];
        nodosPintados[0] = 0;
        
        dfsRecursivo(obtenerIndice(nombreNodoInicial), visitados, nodosPintados, cantidadNodos);
    }

    private void dfsRecursivo(int indiceNodo, boolean[] visitados, int[] nodosPintados, int limite) {
        if (nodosPintados[0] >= limite) {
            return;
        }

        visitados[indiceNodo] = true;
        
        for (int i = 0; i < nodos.length; i++) {
            if (listaAdyacencia[indiceNodo][i] == 1 && !visitados[i]) {
                if (nodosPintados[0] < limite) {
                    org.graphstream.graph.Node nodoAdyacente = graphStream.getNode(nodos[i].getNombre());
                    nodoAdyacente.setAttribute("ui.class", "covertura");
                    nodosPintados[0]++;
                    
                    dfsRecursivo(i, visitados, nodosPintados, limite);
                }
            }
        }
    }
}
