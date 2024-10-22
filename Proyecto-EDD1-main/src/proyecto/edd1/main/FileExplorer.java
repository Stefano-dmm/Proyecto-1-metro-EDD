/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package proyecto.edd1.main;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import proyecto.edd1.main.Nodo;

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

                Nodo[] nodos = new Nodo[100];  // Adjust size as needed
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

                        // Ignorar nodos llamados "Linea 1", "Linea 2", "Linea 3", "Linea 4"
                        if (!nombreNodo.startsWith("Linea ")) {
                            Nodo nodoActual = null;
                            for (int i = 0; i < nodoIndex; i++) {
                                if (nodos[i] != null && nodos[i].nombre.equals(nombreNodo)) {
                                    nodoActual = nodos[i];
                                    break;
                                }
                            }

                            if (nodoActual == null) {
                                nodoActual = new Nodo(nombreNodo);
                                nodos[nodoIndex++] = nodoActual;
                            }

                            if (nodoAnterior != null && !nodoAnterior.nombre.startsWith("Linea ")) {
                                nodoAnterior.agregarConexion(nodoActual);
                                System.out.println("    Conexión creada: " + nodoAnterior.nombre + " -> " + nodoActual.nombre);
                            }

                            if (conexionOtraLinea != null && !conexionOtraLinea.startsWith("Linea ")) {
                                Nodo nodoConexion = null;
                                for (int i = 0; i < nodoIndex; i++) {
                                    if (nodos[i] != null && nodos[i].nombre.equals(conexionOtraLinea)) {
                                        nodoConexion = nodos[i];
                                        break;
                                    }
                                }

                                if (nodoConexion == null) {
                                    nodoConexion = new Nodo(conexionOtraLinea);
                                    nodos[nodoIndex++] = nodoConexion;
                                }

                                nodoActual.agregarConexion(nodoConexion);
                                nodoConexion.agregarConexion(nodoActual);
                                System.out.println("    Conexión entre líneas creada: " + nodoActual.nombre + " <-> " + nodoConexion.nombre);
                            }

                            nodoAnterior = nodoActual; // Actualizar nodo anterior
                        }
                    }
                    System.out.println(); // Línea en blanco entre líneas de metro
                }

                // Filtrar nodos para no incluir "Linea"
                Nodo[] resultado = new Nodo[nodoIndex];
                int resultadoIndex = 0;
                for (int i = 0; i < nodoIndex; i++) {
                    if (nodos[i] != null && !nodos[i].nombre.startsWith("Linea ")) {
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
}
