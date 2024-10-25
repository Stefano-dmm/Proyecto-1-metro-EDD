/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package proyecto.edd1.main;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileExplorer {

    public Nodo[] openFileExplorerAndCreateAdjacencyList() {
        String filePath = openFileExplorer();
        if (filePath != null) {
            return createAdjacencyListFromJson(filePath);
        }
        System.out.println("No se seleccionó ningún archivo.");
        return null;
    }

    private String openFileExplorer() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccione un archivo JSON");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos JSON", "json");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Archivo seleccionado: " + selectedFile.getAbsolutePath());
            return selectedFile.getAbsolutePath();
        } else {
            System.out.println("No se seleccionó ningún archivo.");
            return null;
        }
    }

    private Nodo[] createAdjacencyListFromJson(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder jsonContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }
            String json = jsonContent.toString().trim();
            System.out.println("Contenido del archivo JSON:");
            System.out.println(json);

            if (json.startsWith("{") && json.endsWith("}")) {
                String contenido = json.substring(json.indexOf("["), json.lastIndexOf("]") + 1);
                String[] lineas = contenido.split("\\},\\{");

                Nodo[] nodos = new Nodo[100];  // Tamaño inicial de 100
                int nodoIndex = 0;

                for (String linea : lineas) {
                    linea = linea.replaceAll("[{}\"]", "").trim();
                    String[] partes = linea.split(":", 2);
                    String nombreLinea = partes[0].trim();
                    String[] nodosList = partes[1].replaceAll("[\\[\\]]", "").split(",");
                    System.out.println("Procesando línea: " + nombreLinea);
                    Nodo nodoAnterior = null;

                    for (String nodoStr : nodosList) {
                        nodoStr = nodoStr.trim();
                        String nombreNodo;
                        String conexionOtraLinea = null;

                        if (nodoStr.contains(":")) {
                            String[] conexion = nodoStr.split(":");
                            nombreNodo = conexion[0].trim();
                            conexionOtraLinea = conexion[1].trim();
                        } else {
                            nombreNodo = nodoStr;
                        }

                        // Ignorar nodos que comienzan con "Linea" o "Avenida"
                        if (!nombreNodo.startsWith("Linea ") && !nombreNodo.startsWith("Avenida ")) {
                            Nodo nodoActual = null;
                            for (int i = 0; i < nodoIndex; i++) {
                                if (nodos[i] != null && nodos[i].getNombre().equals(nombreNodo)) {
                                    nodoActual = nodos[i];
                                    break;
                                }
                            }

                            if (nodoActual == null) {
                                nodoActual = new Nodo(nombreNodo); // Eliminar el parámetro 'linea'
                                if (nodoIndex >= nodos.length) {
                                    // Duplicar el tamaño del arreglo si se alcanza el límite
                                    Nodo[] temp = new Nodo[nodos.length * 2];
                                    System.arraycopy(nodos, 0, temp, 0, nodos.length);
                                    nodos = temp;
                                }
                                nodos[nodoIndex++] = nodoActual;
                            }

                            // Solo agregar conexiones si están explícitamente definidas en el JSON
                            if (nodoAnterior != null) {
                                // No agregar conexiones para "Palo Verde" si no está conectado a ningún otro nodo
                                if (!nodoAnterior.getNombre().equals("Palo Verde")) {
                                    nodoAnterior.agregarConexion(nodoActual);
                                    System.out.println("    Conexión creada: " + nodoAnterior.getNombre() + " -> " + nodoActual.getNombre());
                                } else {
                                    System.out.println("    Nodo Palo Verde no tiene conexiones especificadas, no se crea conexión.");
                                }
                            }

                            if (conexionOtraLinea != null && !conexionOtraLinea.startsWith("Linea ") && !conexionOtraLinea.startsWith("Avenida ")) {
                                Nodo nodoConexion = null;
                                for (int i = 0; i < nodoIndex; i++) {
                                    if (nodos[i] != null && nodos[i].getNombre().equals(conexionOtraLinea)) {
                                        nodoConexion = nodos[i];
                                        break;
                                    }
                                }

                                if (nodoConexion == null) {
                                    nodoConexion = new Nodo(conexionOtraLinea); // Eliminar el parámetro 'linea'
                                    if (nodoIndex >= nodos.length) {
                                        // Duplicar el tamaño del arreglo si se alcanza el límite
                                        Nodo[] temp = new Nodo[nodos.length * 2];
                                        System.arraycopy(nodos, 0, temp, 0, nodos.length);
                                        nodos = temp;
                                    }
                                    nodos[nodoIndex++] = nodoConexion;
                                }

                                nodoActual.agregarConexion(nodoConexion);
                                nodoConexion.agregarConexion(nodoActual);
                                System.out.println("    Conexión entre líneas creada: " + nodoActual.getNombre() + " <-> " + nodoConexion.getNombre());
                            }

                            nodoAnterior = nodoActual; // Actualizar nodo anterior
                        }
                    }
                    System.out.println(); // Línea en blanco entre líneas de metro
                }

                // Establecer la conexión entre "Capuchinos" y "El Silencio"
                Nodo capuchinosNode = null;
                Nodo silencioNode = null;

                // Buscar los nodos de "Capuchinos" y "El Silencio"
                for (int i = 0; i < nodoIndex; i++) {
                    if (nodos[i] != null) {
                        if (nodos[i].getNombre().equals("Capuchinos")) {
                            capuchinosNode = nodos[i];
                        }
                        if (nodos[i].getNombre().equals("El Silencio")) {
                            silencioNode = nodos[i];
                        }
                    }
                }

                // Si ambos nodos existen, agregar la conexión
                if (capuchinosNode != null && silencioNode != null) {
                    capuchinosNode.agregarConexion(silencioNode);
                    silencioNode.agregarConexion(capuchinosNode);
                    System.out.println("Conexión creada: Capuchinos <-> El Silencio");
                } else {
                    System.out.println("No se pudo establecer la conexión entre Capuchinos y El Silencio, uno de los nodos no se encontró.");
                }

                // Filtrar nodos para no incluir "Linea" ni "Avenida"
                Nodo[] resultado = new Nodo[nodoIndex];
                int resultadoIndex = 0;
                for (int i = 0; i < nodoIndex; i++) {
                    if (nodos[i] != null && !nodos[i].getNombre().startsWith("Linea ") && !nodos[i].getNombre().startsWith("Avenida ")) {
                        resultado[resultadoIndex++] = nodos[i];
                    }
                }

                // Ajustar el tamaño del array de resultado
                Nodo[] nodosFinales = new Nodo[resultadoIndex];
                System.arraycopy(resultado, 0, nodosFinales, 0, resultadoIndex);

                return nodosFinales;
            } else {
                System.out.println("El JSON no tiene el formato esperado.");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error al leer o procesar el archivo:");
            e.printStackTrace();
            return null;
        }
    }

    // Nueva función para guardar la lista de adyacencia en un String (variable)
    public String saveAdjacencyListToString(Nodo[] adjacencyList) {
        if (adjacencyList == null || adjacencyList.length == 0) {
            System.out.println("La lista de adyacencia está vacía o es nula.");
            return null;
        }

        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("[\n");
        for (int i = 0; i < adjacencyList.length; i++) {
            Nodo nodo = adjacencyList[i];
            jsonBuilder.append("  {\n");
            jsonBuilder.append("    \"nombre\": \"").append(nodo.getNombre()).append("\",\n");
            jsonBuilder.append("    \"conexiones\": [\n"); // Eliminar la línea
            Nodo[] conexiones = nodo.getConexiones();
            for (int j = 0; j < nodo.getConexionIndex(); j++) {
                jsonBuilder.append("      \"").append(conexiones[j].getNombre()).append("\"");
                if (j < nodo.getConexionIndex() - 1) {
                    jsonBuilder.append(",");
                }
                jsonBuilder.append("\n");
            }
            jsonBuilder.append("    ]\n");
            jsonBuilder.append("  }");
            if (i < adjacencyList.length - 1) {
                jsonBuilder.append(",");
            }
            jsonBuilder.append("\n");
        }
        jsonBuilder.append("]\n");

        // Devolver la representación JSON como un String
        return jsonBuilder.toString();
    }
}
