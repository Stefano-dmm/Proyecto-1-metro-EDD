/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyecto.edd1.main;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import org.graphstream.graph.Graph;

/**
 *
 * @author mainp
 */
public class EditorDeGrafo extends javax.swing.JFrame {

    private Grafo grafo; // Agregar una variable para almacenar el grafo
    private Graph graphstream;

    /**
     * Creates new form EditorDeGrafo
     */
    public EditorDeGrafo(Grafo grafo, Graph graphstream) { // Constructor que acepta un objeto Grafo
        initComponents();
        this.grafo = grafo; // Inicializar la variable grafo
        this.graphstream = graphstream;
        graphstream.setAttribute("ui.stylesheet", 
            "node { size: 15px; } " +
            "node.inicial { fill-color: #FF8C00; size: 20px; } " +
            "node.covertura { fill-color: #FF0000; size: 15px; }"
        );
        
        // Agregar el ActionListener al jComboBox2
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String nombreNodoSeleccionado = (String) jComboBox2.getSelectedItem();
                if (nombreNodoSeleccionado != null) {
                    mostrarInformacionNodo(nombreNodoSeleccionado);
                }
            }
        });
        
        llenarComboBoxNodos(); // Llenar el JComboBox con los nodos
        llenarComboBox3(); // Llenar jComboBox3 con los nodos

        // Agregar ActionListener al jButton1
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarNodoSeleccionado();
            }
        });

 
    }

    // Método para llenar el JComboBox con los nodos existentes
    private void llenarComboBoxNodos() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (Nodo nodo : grafo.getNodos()) { // Asegúrate de que getNodos() esté definido
            if (nodo != null) {
                model.addElement(nodo.getNombre());
            }
        }
        jComboBox2.setModel(model); // Establecer el modelo en jComboBox2
    }

    // Método para llenar jComboBox3 con los nodos existentes
    private void llenarComboBox3() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (Nodo nodo : grafo.getNodos()) { // Asegúrate de que getNodos() esté definido
            if (nodo != null) {
                model.addElement(nodo.getNombre());
            }
        }
        jComboBox3.setModel(model); // Establecer el modelo en jComboBox3
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DFS", "BFS" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Tipo de Generacion de areas comerciales:");

        jTextField1.setText("0");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Estaciones entre areas comerciales:");

        jButton1.setText("Editar nodo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Generar areas comerciales");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel3.setText("Informacion de Nodo");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setText("-------------------------------------------------");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setText("Nodo seleccionado");

        jButton3.setText("Agregar Linea");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setText("Recomendar Sucursal");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addGap(70, 70, 70)
                        .addComponent(jButton1)
                        .addGap(173, 173, 173))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4)
                            .addComponent(jScrollPane1))
                        .addGap(91, 91, 91))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField1)
                            .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton1))
                .addGap(61, 61, 61))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        String nombreNodo = (String) jComboBox2.getSelectedItem(); // Obtener el nodo seleccionado
        Nodo[] nodos = grafo.getNodos(); // Obtener los nodos del grafo
        for (Nodo nodo : nodos) {
            if (nodo != null && nodo.getNombre().equals(nombreNodo)) {
                new EditorNodoDialog(this, nodo, grafo, graphstream); // Abrir el diálogo de edición
                break;
            }
        }
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        String algoritmoSeleccionado = (String) jComboBox1.getSelectedItem();
        String nodoInicial = (String) jComboBox3.getSelectedItem();
        
        int cantidad;
        try {
            cantidad = Integer.parseInt(jTextField1.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese un número válido");
            return;
        }

        if ("BFS".equals(algoritmoSeleccionado)) {
            grafo.pintarGrafoConDistancia(nodoInicial, cantidad);
        } 
        else if ("DFS".equals(algoritmoSeleccionado)) {
            grafo.pintarGrafoDFS(nodoInicial, cantidad);
        }
        else {
            JOptionPane.showMessageDialog(this, "Por favor seleccione un algoritmo válido (BFS o DFS)");
        }
    }

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Nodo[] nodos = grafo.getNodos();
        String[] nombresNodos = new String[nodos.length];
        int numNodosValidos = 0;
        for (Nodo nodo : nodos) {
            if (nodo != null) {
                nombresNodos[numNodosValidos++] = nodo.getNombre();
            }
        }

        // Crear un nuevo array con solo los nodos válidos
        String[] opcionesValidas = new String[numNodosValidos];
        System.arraycopy(nombresNodos, 0, opcionesValidas, 0, numNodosValidos);

        // Mostrar diálogo para seleccionar la primera estación
        String estacion1 = (String) JOptionPane.showInputDialog(
            this,
            "Seleccione la primera estación:",
            "Agregar Línea",
            JOptionPane.QUESTION_MESSAGE,
            null,
            opcionesValidas,
            opcionesValidas[0]
        );

        if (estacion1 != null) {
            // Mostrar diálogo para seleccionar la segunda estación
            String estacion2 = (String) JOptionPane.showInputDialog(
                this,
                "Seleccione la segunda estación:",
                "Agregar Línea",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesValidas,
                opcionesValidas[0]
            );

            if (estacion2 != null && !estacion1.equals(estacion2)) {
                grafo.agregarLinea(estacion1, estacion2);
                JOptionPane.showMessageDialog(this, "Línea agregada correctamente entre " + estacion1 + " y " + estacion2);
                actualizarInterfaz();
            } else if (estacion1.equals(estacion2)) {
                JOptionPane.showMessageDialog(this, "Por favor, seleccione dos estaciones diferentes.");
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {
        if (hayNodosMarcados()) {
            recomendarNodosAdicionales();
        } else {
            recomendarSucursalesIniciales();
        }
    }

    private void recomendarNodosAdicionales() {
        String tipoRecorrido = (String) jComboBox1.getSelectedItem();
        int distancia;
        
        try {
            distancia = Integer.parseInt(jTextField1.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "Por favor, ingrese un número válido en el campo de distancia.",
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        StringBuilder recomendaciones = new StringBuilder();
        recomendaciones.append("Recomendaciones adicionales considerando nodos ya marcados:\n\n");

        String[] nodosYaMarcados = obtenerNodosMarcados();
        String[] nuevasRecomendaciones = new String[grafo.getNodos().length];
        int contadorNuevasRecomendaciones = 0;

        if (tipoRecorrido.equals("BFS")) {
            recomendarAdicionalesBFS(nodosYaMarcados, distancia, recomendaciones, nuevasRecomendaciones, contadorNuevasRecomendaciones);
        } else {
            recomendarAdicionalesDFS(nodosYaMarcados, distancia, recomendaciones, nuevasRecomendaciones, contadorNuevasRecomendaciones);
        }

        if (contadorNuevasRecomendaciones == 0) {
            recomendaciones.append("No se encontraron ubicaciones adicionales recomendadas que cumplan con la distancia especificada.\n");
        }

        JOptionPane.showMessageDialog(this, 
            recomendaciones.toString(),
            "Recomendaciones Adicionales", 
            JOptionPane.INFORMATION_MESSAGE);
    }

    private String[] obtenerNodosMarcados() {
        String[] nodosMarcados = new String[grafo.getNodos().length];
        int contador = 0;
        Nodo[] nodos = grafo.getNodos();
        
        for (Nodo nodo : nodos) {
            if (nodo != null && (nodo.isAreaComercial() || nodo.isSucursal())) {
                nodosMarcados[contador++] = nodo.getNombre();
            }
        }
        
        // Crear un nuevo array del tamaño exacto
        String[] resultado = new String[contador];
        System.arraycopy(nodosMarcados, 0, resultado, 0, contador);
        return resultado;
    }

    private void recomendarAdicionalesBFS(String[] nodosYaMarcados, int distancia, 
                                        StringBuilder recomendaciones, String[] nuevasRecomendaciones,
                                        int contadorNuevasRecomendaciones) {
        boolean[] visitadosGlobal = new boolean[grafo.getNodos().length];
        int contadorRecomendaciones = 0;

        for (String nodoMarcado : nodosYaMarcados) {
            if (nodoMarcado == null) continue;
            
            boolean[] visitados = new boolean[grafo.getNodos().length];
            int[] distancias = new int[grafo.getNodos().length];
            for (int i = 0; i < distancias.length; i++) {
                distancias[i] = Integer.MAX_VALUE;
            }
            
            int indiceInicial = grafo.obtenerIndice(nodoMarcado);
            Cola cola = new Cola();
            cola.encolar(indiceInicial);
            visitados[indiceInicial] = true;
            distancias[indiceInicial] = 0;

            while (!cola.estaVacia()) {
                int actual = cola.desencolar();
                
                for (int i = 0; i < grafo.getNodos().length; i++) {
                    if (grafo.getListaAdyacencia()[actual][i] == 1 && !visitados[i]) {
                        visitados[i] = true;
                        distancias[i] = distancias[actual] + 1;
                        cola.encolar(i);

                        Nodo nodoActual = grafo.getNodos()[i];
                        if (distancias[i] == distancia && 
                            !nodoActual.isAreaComercial() && 
                            !nodoActual.isSucursal() && 
                            !visitadosGlobal[i] &&
                            !estaEnArray(nuevasRecomendaciones, nodoActual.getNombre())) {
                            
                            contadorRecomendaciones++;
                            nuevasRecomendaciones[contadorNuevasRecomendaciones++] = nodoActual.getNombre();
                            visitadosGlobal[i] = true;
                            
                            recomendaciones.append(contadorRecomendaciones)
                                         .append(". Posible sucursal en: ")
                                         .append(nodoActual.getNombre())
                                         .append(" (a ")
                                         .append(distancia)
                                         .append(" saltos de ")
                                         .append(nodoMarcado)
                                         .append(")\n");
                        }
                    }
                }
            }
        }

        if (contadorRecomendaciones > 0) {
            recomendaciones.append("\nTotal de nuevas ubicaciones recomendadas: ")
                          .append(contadorRecomendaciones)
                          .append("\n");
        }
    }

    private boolean estaEnArray(String[] array, String valor) {
        for (String elemento : array) {
            if (elemento != null && elemento.equals(valor)) {
                return true;
            }
        }
        return false;
    }

    // Método que se llama cuando se selecciona un nodo en jComboBox2
    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {
        String nombreNodo = (String) jComboBox2.getSelectedItem(); // Obtener el nodo seleccionado
        mostrarInformacionNodo(nombreNodo); // Llamar al método para mostrar la información
    }

    // Método para mostrar la información del nodo en jTextArea1
    private void mostrarInformacionNodo(String nombreNodo) {
        Nodo nodo = grafo.obtenerNodoPorNombre(nombreNodo);
        if (nodo != null) {
            StringBuilder contenido = new StringBuilder();
            contenido.append("Nombre: ").append(nodo.getNombre()).append("\n\n");
            
            // Mostrar si es sucursal
            contenido.append("Sucursal: ").append(nodo.isSucursal() ? "Sí" : "No").append("\n\n");
            
            // Mostrar si es área comercial
            contenido.append("Área Comercial: ").append(nodo.isAreaComercial() ? "Sí" : "No").append("\n\n");
            
            // Mostrar conexiones
            contenido.append("Conexiones: \n");
            for (int i = 0; i < nodo.getConexionIndex(); i++) {
                contenido.append("- ").append(nodo.getConexiones()[i].getNombre());
                if (i < nodo.getConexionIndex() - 1) {
                    contenido.append("\n");
                }
            }
            
            // Asegurarse de que el JTextArea no sea nulo
            if (jTextArea1 != null) {
                jTextArea1.setText(contenido.toString());
                // Opcional: hacer que el texto sea visible desde el inicio
                jTextArea1.setCaretPosition(0);
            } else {
                System.out.println("Error: jTextArea1 es nulo");
            }
        } else {
            if (jTextArea1 != null) {
                jTextArea1.setText("No se encontró información del nodo seleccionado.");
            }
        }
    }

    private void jButtonMarcarAreasComercialesActionPerformed(java.awt.event.ActionEvent evt) {
        String tipoMovimiento = (String) jComboBox1.getSelectedItem(); // Obtener el tipo de movimiento
        String nodoInicial = (String) jComboBox2.getSelectedItem(); // Obtener el nodo inicial
        int distanciaMaxima = Integer.parseInt(jTextField1.getText()); // Obtener la distancia máxima

        if (tipoMovimiento.equals("DFS")) {
            grafo.marcarAreasComercialesDFS(nodoInicial, distanciaMaxima);
        } else if (tipoMovimiento.equals("BFS")) {
            grafo.marcarAreasComercialesBFS(nodoInicial, distanciaMaxima);
        }
    }

    private void jButtonGenerarAreaComercialActionPerformed(java.awt.event.ActionEvent evt) {
        // Obtener el tipo de búsqueda (DFS o BFS)
        String tipoBusqueda = (String) jComboBox1.getSelectedItem();

        // Obtener la cantidad de nodos entre áreas comerciales
        int distanciaMaxima;
        try {
            distanciaMaxima = Integer.parseInt(jTextField1.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un número válido para la distancia máxima.");
            return; // Salir si el número no es válido
        }

        // Obtener el primer nodo
        String nodoInicial = (String) jComboBox3.getSelectedItem();

        // Llamar a la función que marca los nodos como comerciales
        if (tipoBusqueda.equals("DFS")) {
            grafo.seleccionarNodosConSaltoDFS(nodoInicial, distanciaMaxima);
        } else if (tipoBusqueda.equals("BFS")) {
            grafo.seleccionarNodosConSaltoBFS(nodoInicial, distanciaMaxima);
        }

        // Mostrar el recorrido en el JTextArea
        jTextArea1.setText(grafo.getRecorridoNodos()); // Asegúrate de que este método esté definido en Grafo

        // Opcional: Mostrar un mensaje de éxito
        JOptionPane.showMessageDialog(this, "Áreas comerciales generadas correctamente.");
    }

    private void mostrarNodoViewer() {
        Nodo[] nodos = grafo.getNodos(); // Obtener los nodos del grafo
        NodoViewer nodoViewer = new NodoViewer(nodos, grafo); // Pasar la referencia de grafo
    }

    public void actualizarComboBoxNodos() { // Cambiar a public
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (Nodo nodo : grafo.getNodos()) { // Asegúrate de que getNodos() esté definido
            if (nodo != null) {
                model.addElement(nodo.getNombre());
            }
        }
        jComboBox2.setModel(model); // Establecer el modelo en jComboBox2
    }
    
     private void actualizarInterfaz() {
        // Actualizar los ComboBox y cualquier otra parte de la interfaz que muestre información del grafo
        llenarComboBoxNodos();
        llenarComboBox3();
        // Si tienes alguna visualización del grafo, actualízala aquí
    }

    // Agregar el método para reiniciar colores
    private void reiniciarColoresActionPerformed(java.awt.event.ActionEvent evt) {
        for (org.graphstream.graph.Node node : graphstream.getEachNode()) {
            node.removeAttribute("ui.class");
            Nodo nodo = grafo.obtenerNodoPorNombre(node.getId());
            if (nodo != null) {
                nodo.setSucursal(false);
                nodo.setAreaComercial(false);
            }
        }
    }

    // Agregar este nuevo método
    private void editarNodoSeleccionado() {
        String nombreNodoSeleccionado = (String) jComboBox2.getSelectedItem();
        if (nombreNodoSeleccionado != null) {
            Nodo nodoSeleccionado = grafo.obtenerNodoPorNombre(nombreNodoSeleccionado);
            if (nodoSeleccionado != null) {
                EditorNodoDialog dialog = new EditorNodoDialog(this, nodoSeleccionado, grafo, graphstream);
                dialog.setVisible(true);
                // Actualizar la información mostrada después de cerrar el diálogo
                mostrarInformacionNodo(nombreNodoSeleccionado);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un nodo para editar.");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

    private void recomendarAdicionalesDFS(String[] nodosYaMarcados, int distancia, 
                                        StringBuilder recomendaciones, String[] nuevasRecomendaciones,
                                        int contadorNuevasRecomendaciones) {
        boolean[] visitadosGlobal = new boolean[grafo.getNodos().length];
        int[] contadorRecomendaciones = new int[1];
        contadorRecomendaciones[0] = 0;

        for (String nodoMarcado : nodosYaMarcados) {
            if (nodoMarcado == null) continue;
            
            boolean[] visitados = new boolean[grafo.getNodos().length];
            dfsRecursivoAdicional(grafo.obtenerIndice(nodoMarcado), visitados, 0, distancia,
                                recomendaciones, contadorRecomendaciones, nuevasRecomendaciones,
                                visitadosGlobal, nodoMarcado, contadorNuevasRecomendaciones);
        }

        if (contadorRecomendaciones[0] > 0) {
            recomendaciones.append("\nTotal de nuevas ubicaciones recomendadas: ")
                          .append(contadorRecomendaciones[0])
                          .append("\n");
        }
    }

    private void dfsRecursivoAdicional(int nodoActual, boolean[] visitados, int distanciaActual,
                                     int distanciaObjetivo, StringBuilder recomendaciones,
                                     int[] contadorRecomendaciones, String[] nuevasRecomendaciones,
                                     boolean[] visitadosGlobal, String nodoOrigen, 
                                     int contadorNuevasRecomendaciones) {
        visitados[nodoActual] = true;
        
        for (int i = 0; i < grafo.getNodos().length; i++) {
            if (grafo.getListaAdyacencia()[nodoActual][i] == 1 && !visitados[i]) {
                Nodo nodoDestino = grafo.getNodos()[i];
                
                if (distanciaActual + 1 == distanciaObjetivo && 
                    !nodoDestino.isAreaComercial() && 
                    !nodoDestino.isSucursal() && 
                    !visitadosGlobal[i] &&
                    !estaEnArray(nuevasRecomendaciones, nodoDestino.getNombre())) {
                    
                    contadorRecomendaciones[0]++;
                    nuevasRecomendaciones[contadorNuevasRecomendaciones] = nodoDestino.getNombre();
                    visitadosGlobal[i] = true;
                    
                    recomendaciones.append(contadorRecomendaciones[0])
                                 .append(". Posible sucursal en: ")
                                 .append(nodoDestino.getNombre())
                                 .append(" (a ")
                                 .append(distanciaObjetivo)
                                 .append(" saltos de ")
                                 .append(nodoOrigen)
                                 .append(")\n");
                }
                
                dfsRecursivoAdicional(i, visitados, distanciaActual + 1, distanciaObjetivo,
                                    recomendaciones, contadorRecomendaciones, nuevasRecomendaciones,
                                    visitadosGlobal, nodoOrigen, contadorNuevasRecomendaciones + 1);
            }
        }
    }

    private boolean hayNodosMarcados() {
        Nodo[] nodos = grafo.getNodos();
        for (Nodo nodo : nodos) {
            if (nodo != null && (nodo.isAreaComercial() || nodo.isSucursal())) {
                return true;
            }
        }
        return false;
    }

    private void recomendarSucursalesIniciales() {
        String tipoRecorrido = (String) jComboBox1.getSelectedItem();
        String nodoInicial = (String) jComboBox2.getSelectedItem();
        int distancia;

        try {
            distancia = Integer.parseInt(jTextField1.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "Por favor, ingrese un número válido en el campo de distancia.",
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        StringBuilder recomendaciones = new StringBuilder();
        recomendaciones.append("Recomendaciones iniciales para ubicar sucursales:\n\n");
        recomendaciones.append("Nodo inicial recomendado: ").append(nodoInicial).append("\n\n");

        String[] nuevasRecomendaciones = new String[grafo.getNodos().length];
        int contadorNuevasRecomendaciones = 0;
        nuevasRecomendaciones[contadorNuevasRecomendaciones++] = nodoInicial;

        if (tipoRecorrido.equals("BFS")) {
            recomendarInicialesBFS(nodoInicial, distancia, recomendaciones, nuevasRecomendaciones, contadorNuevasRecomendaciones);
        } else {
            recomendarInicialesDFS(nodoInicial, distancia, recomendaciones, nuevasRecomendaciones, contadorNuevasRecomendaciones);
        }

        JOptionPane.showMessageDialog(this, 
            recomendaciones.toString(),
            "Recomendaciones Iniciales de Sucursales", 
            JOptionPane.INFORMATION_MESSAGE);
    }

    private void recomendarInicialesBFS(String nodoInicial, int distancia, StringBuilder recomendaciones,
                                      String[] nuevasRecomendaciones, int contadorNuevasRecomendaciones) {
        boolean[] visitados = new boolean[grafo.getNodos().length];
        int[] distancias = new int[grafo.getNodos().length];
        for (int i = 0; i < distancias.length; i++) {
            distancias[i] = Integer.MAX_VALUE;
        }

        int indiceInicial = grafo.obtenerIndice(nodoInicial);
        Cola cola = new Cola();
        cola.encolar(indiceInicial);
        visitados[indiceInicial] = true;
        distancias[indiceInicial] = 0;
        int contadorRecomendaciones = 0;

        while (!cola.estaVacia()) {
            int actual = cola.desencolar();
            
            for (int i = 0; i < grafo.getNodos().length; i++) {
                if (grafo.getListaAdyacencia()[actual][i] == 1 && !visitados[i]) {
                    visitados[i] = true;
                    distancias[i] = distancias[actual] + 1;
                    cola.encolar(i);

                    if (distancias[i] == distancia && !estaEnArray(nuevasRecomendaciones, grafo.getNodos()[i].getNombre())) {
                        contadorRecomendaciones++;
                        nuevasRecomendaciones[contadorNuevasRecomendaciones] = grafo.getNodos()[i].getNombre();
                        
                        recomendaciones.append(contadorRecomendaciones)
                                     .append(". Se recomienda sucursal en: ")
                                     .append(grafo.getNodos()[i].getNombre())
                                     .append(" (a ")
                                     .append(distancia)
                                     .append(" saltos del nodo inicial)\n");
                    }
                }
            }
        }

        if (contadorRecomendaciones > 0) {
            recomendaciones.append("\nTotal de nuevas ubicaciones recomendadas: ")
                          .append(contadorRecomendaciones)
                          .append("\n");
        }
    }

    private void recomendarInicialesDFS(String nodoInicial, int distancia, StringBuilder recomendaciones,
                                      String[] nuevasRecomendaciones, int contadorNuevasRecomendaciones) {
        boolean[] visitados = new boolean[grafo.getNodos().length];
        int[] contadorRecomendaciones = new int[1];
        contadorRecomendaciones[0] = 0;

        dfsRecursivoInicial(grafo.obtenerIndice(nodoInicial), visitados, 0, distancia,
                           recomendaciones, contadorRecomendaciones, nuevasRecomendaciones,
                           contadorNuevasRecomendaciones);

        if (contadorRecomendaciones[0] > 0) {
            recomendaciones.append("\nTotal de ubicaciones recomendadas: ")
                          .append(contadorRecomendaciones[0] + 1) // +1 por el nodo inicial
                          .append("\n");
        }
    }

    private void dfsRecursivoInicial(int nodoActual, boolean[] visitados, int distanciaActual,
                                   int distanciaObjetivo, StringBuilder recomendaciones,
                                   int[] contadorRecomendaciones, String[] nuevasRecomendaciones,
                                   int contadorNuevasRecomendaciones) {
        visitados[nodoActual] = true;
        
        for (int i = 0; i < grafo.getNodos().length; i++) {
            if (grafo.getListaAdyacencia()[nodoActual][i] == 1 && !visitados[i]) {
                if (distanciaActual + 1 == distanciaObjetivo && 
                    !estaEnArray(nuevasRecomendaciones, grafo.getNodos()[i].getNombre())) {
                    
                    contadorRecomendaciones[0]++;
                    nuevasRecomendaciones[contadorNuevasRecomendaciones] = grafo.getNodos()[i].getNombre();
                    
                    recomendaciones.append(contadorRecomendaciones[0])
                                 .append(". Se recomienda sucursal en: ")
                                 .append(grafo.getNodos()[i].getNombre())
                                 .append(" (a ")
                                 .append(distanciaObjetivo)
                                 .append(" saltos del nodo inicial)\n");
                }
                
                dfsRecursivoInicial(i, visitados, distanciaActual + 1, distanciaObjetivo,
                                  recomendaciones, contadorRecomendaciones, nuevasRecomendaciones,
                                  contadorNuevasRecomendaciones + 1);
            }
        }
    }
}
