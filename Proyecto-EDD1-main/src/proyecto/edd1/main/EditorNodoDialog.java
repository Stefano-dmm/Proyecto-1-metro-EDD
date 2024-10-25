package proyecto.edd1.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditorNodoDialog extends JDialog {
    private JTextField nombreField;
    private JCheckBox areaComercialCheckBox; // Agregar JCheckBox para área comercial
    private JButton guardarButton;
    private JButton eliminarButton;
    private Nodo nodo;
    private Grafo grafo;

    public EditorNodoDialog(Frame parent, Nodo nodo, Grafo grafo) {
        super(parent, "Editar Nodo", true);
        this.nodo = nodo;
        this.grafo = grafo;

        setLayout(new GridLayout(4, 2)); // Cambiar a 4 filas

        // Campo para editar el nombre del nodo
        add(new JLabel("Nombre del Nodo:"));
        nombreField = new JTextField(nodo.getNombre());
        add(nombreField);

        // Checkbox para el área comercial
        add(new JLabel("Área Comercial:"));
        areaComercialCheckBox = new JCheckBox();
        areaComercialCheckBox.setSelected(nodo.isAreaComercial()); // Establecer el estado inicial
        add(areaComercialCheckBox);

        // Botón para guardar cambios
        guardarButton = new JButton("Guardar");
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarCambios();
            }
        });
        add(guardarButton);

        // Botón para eliminar el nodo
        eliminarButton = new JButton("Eliminar");
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarNodo();
            }
        });
        add(eliminarButton);

        pack();
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    private void guardarCambios() {
        String nuevoNombre = nombreField.getText().trim();
        if (!nuevoNombre.isEmpty()) {
            nodo.setNombre(nuevoNombre);
            nodo.setAreaComercial(areaComercialCheckBox.isSelected()); // Actualizar el estado de área comercial
            JOptionPane.showMessageDialog(this, "Cambios guardados.");
            dispose(); // Cerrar el diálogo
        } else {
            JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío.");
        }
    }

    private void eliminarNodo() {
        int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que deseas eliminar este nodo?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            // Eliminar el nodo del grafo
            grafo.eliminarNodo(nodo);
            JOptionPane.showMessageDialog(this, "Nodo eliminado.");
            // Actualizar el JComboBox en la ventana principal
            ((EditorDeGrafo) getParent()).actualizarComboBoxNodos();
            dispose(); // Cerrar el diálogo
        }
    }
}
