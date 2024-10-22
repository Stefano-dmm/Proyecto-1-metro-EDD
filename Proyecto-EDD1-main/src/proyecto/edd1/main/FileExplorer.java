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
        String filePath = null;
        while (filePath == null) { // Bucle para seguir abriendo el explorador hasta que se seleccione un archivo
            // Ventana de aviso
            Object[] options = {"Buscar JSON"};
            javax.swing.JOptionPane.showOptionDialog(null, 
                "Por favor selecciona un JSON con la lista de estaciones y líneas a analizar", 
                "Aviso", 
                javax.swing.JOptionPane.DEFAULT_OPTION, 
                javax.swing.JOptionPane.INFORMATION_MESSAGE, 
                null, 
                options, 
                options[0]);

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Seleccione un archivo JSON");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

            FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos JSON", "json");
            fileChooser.setFileFilter(filter);
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                System.out.println("Archivo seleccionado: " + selectedFile.getAbsolutePath());
                filePath = selectedFile.getAbsolutePath();
            } else {
                // Ventana de aviso si no se seleccionó ningún archivo
                javax.swing.JOptionPane.showMessageDialog(null, "No se ha seleccionado ningún archivo");
            }
        }
        return filePath;
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

                Nodo[] nodos = new Nodo[50];  // Inicialmente el tamaño es 50
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
                            System.out.println("  Nodo encontrado (con conexión): " + nombreNodo + " -> " + conexionOtraLinea);
                        } else {
                            nombreNodo = nodoStr;
                            System.out.println("  Nodo encontrado: " + nombreNodo);
                        }

                        // Busca si el nodo ya existe, si no, lo crea
                        Nodo nodoActual = buscarNodoPorNombre(nodos, nodoIndex, nombreNodo);
                        if (nodoActual == null) {
                            nodoActual = new Nodo(nombreNodo);
                            if (nodoIndex >= nodos.length) {
                                nodos = expandirArreglo(nodos);  // Expande el arreglo si es necesario
                            }
                            nodos[nodoIndex++] = nodoActual;
                        }

                        // Crea la conexión entre el nodo anterior y el nodo actual
                        if (nodoAnterior != null) {
                            nodoAnterior.agregarConexion(nodoActual);
                            System.out.println("    Conexión creada: " + nodoAnterior.getNombre() + " -> " + nodoActual.getNombre());
                        }

                        // Si hay una conexión a otra línea
                        if (conexionOtraLinea != null) {
                            Nodo nodoConexion = buscarNodoPorNombre(nodos, nodoIndex, conexionOtraLinea);
                            if (nodoConexion == null) {
                                nodoConexion = new Nodo(conexionOtraLinea);
                                if (nodoIndex >= nodos.length) {
                                    nodos = expandirArreglo(nodos);
                                }
                                nodos[nodoIndex++] = nodoConexion;
                            }

                            // Conectar ambos nodos
                            nodoActual.agregarConexion(nodoConexion);
                            nodoConexion.agregarConexion(nodoActual);
                            System.out.println("    Conexión entre líneas creada: " + nodoActual.getNombre() + " <-> " + nodoConexion.getNombre());
                        }

                        nodoAnterior = nodoActual;
                    }
                    System.out.println(); // Línea en blanco entre líneas de metro
                }

                // Ajustar el tamaño final del arreglo para que no contenga elementos vacíos
                Nodo[] resultado = new Nodo[nodoIndex];
                System.arraycopy(nodos, 0, resultado, 0, nodoIndex);
                return resultado;
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

    // Método para buscar un nodo por nombre
    private Nodo buscarNodoPorNombre(Nodo[] nodos, int nodoIndex, String nombre) {
        for (int i = 0; i < nodoIndex; i++) {
            if (nodos[i].getNombre().equals(nombre)) {
                return nodos[i];
            }
        }
        return null;
    }

    // Método para expandir el arreglo de nodos si se supera la capacidad actual
    private Nodo[] expandirArreglo(Nodo[] nodos) {
        Nodo[] nuevoArreglo = new Nodo[nodos.length * 2]; // Duplica el tamaño
        System.arraycopy(nodos, 0, nuevoArreglo, 0, nodos.length);
        return nuevoArreglo;
    }
}
