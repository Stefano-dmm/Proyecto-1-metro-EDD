package proyecto.edd1.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NodoViewer {
    private JTextArea textArea;
    private JComboBox<String> nodoComboBox;
    private Grafo grafo; // Agregar referencia a Grafo

    public NodoViewer(Nodo[] nodos, Grafo grafo) { // Modificar el constructor
        this.grafo = grafo; // Inicializar la referencia a Grafo
        JFrame frame = new JFrame("Contenido de Nodos");
        textArea = new JTextArea(20, 40);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        
        // Crear JComboBox para seleccionar nodos
        nodoComboBox = new JComboBox<>();
        if (nodos != null) {
            for (Nodo nodo : nodos) {
                if (nodo != null) {
                    nodoComboBox.addItem(nodo.getNombre());
                }
            }
        }

        // Agregar ActionListener al JComboBox
        nodoComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarInformacionNodo(nodos, (String) nodoComboBox.getSelectedItem());
            }
        });

        // Configurar el layout
        frame.setLayout(new BorderLayout());
        frame.add(nodoComboBox, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void mostrarInformacionNodo(Nodo[] nodos, String nombreNodo) {
        for (Nodo nodo : nodos) {
            if (nodo != null && nodo.getNombre().equals(nombreNodo)) {
                StringBuilder contenido = new StringBuilder();
                contenido.append("Nombre: ").append(nodo.getNombre()).append("\n");
                contenido.append("Conexiones: ");
                for (int i = 0; i < nodo.getConexionIndex(); i++) {
                    contenido.append(nodo.getConexiones()[i].getNombre());
                    if (i < nodo.getConexionIndex() - 1) {
                        contenido.append(", ");
                    }
                }
                contenido.append("\n");
                // Mostrar si es un área comercial
                if (nodo.isAreaComercial()) {
                    contenido.append("Área Comercial: Sí\n");
                } else {
                    contenido.append("Área Comercial: No\n");
                }
                // Mostrar la lista de adyacencia
                contenido.append("Lista de Adyacencia: \n");
                contenido.append(grafo.obtenerListaAdyacencia()); // Usar la referencia a grafo
                textArea.setText(contenido.toString());
                break;
            }
        }
    }
}
