package practica1_201700988;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;

public class IDE_Window extends javax.swing.JFrame {

    JFileChooser fileSelector = new JFileChooser();
    File fileSelect;
    FileInputStream fileInput;
    FileOutputStream fileOutput;

    public IDE_Window() {
        fileSelector.setFileFilter(new FileNameExtensionFilter("Compi File (*.er)", "er"));
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtFile = new javax.swing.JEditorPane();
        btnLexema = new javax.swing.JButton();
        btnLexico = new javax.swing.JButton();
        btnAutomata = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtConsola = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtRegular = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        lblImg = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Archivo de Entrada"));

        jScrollPane1.setViewportView(txtFile);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 500, 360));

        btnLexema.setText("Evaluar Lexemas");
        btnLexema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLexemaActionPerformed(evt);
            }
        });
        getContentPane().add(btnLexema, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 370, 150, 30));

        btnLexico.setText("Analizador Léxico");
        btnLexico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLexicoActionPerformed(evt);
            }
        });
        getContentPane().add(btnLexico, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 150, 30));

        btnAutomata.setText("Generar Autómatas");
        btnAutomata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAutomataActionPerformed(evt);
            }
        });
        getContentPane().add(btnAutomata, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 370, 150, 30));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Consola de Salida"));

        txtConsola.setColumns(20);
        txtConsola.setRows(5);
        jScrollPane2.setViewportView(txtConsola);

        txtRegular.setColumns(20);
        txtRegular.setRows(5);
        jScrollPane3.setViewportView(txtRegular);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 402, 1070, -1));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Distribución"));

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        jTree1.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jTree1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTree1MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jTree1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 10, 240, 390));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Imágenes"));

        lblImg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImg.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblImg.setMaximumSize(new java.awt.Dimension(1000000000, 1000000000));
        jScrollPane4.setViewportView(lblImg);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 10, 300, 390));

        jMenu1.setText("Archivo");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Abrir");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Guardar");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setText("Guardar Como");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Ayuda");

        jMenuItem4.setText("Acerca De");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuItem5.setText("Ayuda");
        jMenu2.add(jMenuItem5);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        //Open File Function        
        if (fileSelector.showDialog(null, "Abrir") == JFileChooser.APPROVE_OPTION) {
            fileSelect = fileSelector.getSelectedFile();
            if (fileSelect.canRead()) {

                String documento = openFile(fileSelect);
                txtFile.setText(documento);
            }
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        //Save As File Function        
        saveAsFile();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        //Save File Function        
        if (fileSelect != null) {
            String documento = txtFile.getText();
            saveFile(fileSelect, documento);
            String mensaje = "Archivo " + fileSelect.getName() + " guardado exitosamente.";
            JOptionPane.showMessageDialog(null, mensaje, "Guardado con éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            saveAsFile();
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void btnAutomataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAutomataActionPerformed
        Practica1_201700988.listConj.clear();
        Practica1_201700988.listER.clear();
        Practica1_201700988.upDate.cargarDatos(Practica1_201700988.analizador.getSalida());
        jTree1.removeAll();
        jTree1.setModel(Practica1_201700988.upDate.treeVisual());
        jTree1.updateUI();
    }//GEN-LAST:event_btnAutomataActionPerformed

    private void btnLexicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLexicoActionPerformed
        Practica1_201700988.conteo_Expresiones = 0;
        String entrada = txtFile.getText();
        Practica1_201700988.analizador.Scanner(entrada);
        Practica1_201700988.analizador.imprimirLista(txtConsola);
        Practica1_201700988.analizador.crearPDF();
        Practica1_201700988.conteoAnalisis++;

        //Mostrar contenido cargado al sistema
     //   Practica1_201700988.mostrarConj();
     //   Practica1_201700988.mostrarER();
     //   Practica1_201700988.mostrarLexemas();
    }//GEN-LAST:event_btnLexicoActionPerformed

    private void btnLexemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLexemaActionPerformed
        txtRegular.setText("");
        Practica1_201700988.validarLexema.validarLexemas(txtRegular);

    }//GEN-LAST:event_btnLexemaActionPerformed

    private void jTree1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTree1MouseClicked
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) jTree1.getSelectionPath().getLastPathComponent();
        String path = System.getProperty("user.home");
        lblImg.removeAll();
        try {
            for (int i = 0; i < Practica1_201700988.listER.size(); i++) {
                if (Practica1_201700988.listER.get(i).getId().equals(selectedNode.toString())) {
                    String numDoc = Practica1_201700988.listER.get(i).getNumDocumentos();
                    switch (selectedNode.getParent().toString()) {
                        case "Árboles":
                            path += "\\Desktop\\Arbol" + numDoc + ".png";
                            ImageIcon icon = new ImageIcon(path);
                            lblImg.setIcon(icon);
                            break;
                        case "Tablas de Siguientes":
                            path += "\\Desktop\\TablaSiguientes" + numDoc + ".png";
                            lblImg.setIcon(new ImageIcon(path));
                            break;
                        case "Tablas de Estados":
                            path += "\\Desktop\\TablaEstados" + numDoc + ".png";
                            lblImg.setIcon(new ImageIcon(path));
                            break;
                        case "AFD":
                            path += "\\Desktop\\AFD" + numDoc + ".png";
                            lblImg.setIcon(new ImageIcon(path));
                            break;
                        default:
                            //Nada
                            break;
                    }
                }
            }
        } catch (Exception e) {

        }
    }//GEN-LAST:event_jTree1MouseClicked

    private void saveAsFile() {
        if (fileSelector.showDialog(null, "Guardar Como") == JFileChooser.APPROVE_OPTION) {
            fileSelect = fileSelector.getSelectedFile();
            String documento = txtFile.getText();
            String mensaje = saveFile(fileSelect, documento);
            if (mensaje != null) {
                JOptionPane.showMessageDialog(null, mensaje, "Guardado con éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar archivo.", "Error al guardar", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private String saveFile(File archivo, String contenido) {
        String mensaje = null;
        String fileName = "";
        try {
            String nameFile = archivo.toString();
            if (!nameFile.endsWith(".er")) {
                nameFile += ".er";
                fileOutput = new FileOutputStream(nameFile);
            } else {
                fileOutput = new FileOutputStream(archivo);
            }
            byte[] txtBytes = contenido.getBytes();
            fileOutput.write(txtBytes);
            fileName = fileSelect.getName().replace(".er", "");
            mensaje = "Archivo " + fileName + " guardado exitosamente.";
        } catch (IOException e) {

        }
        this.setTitle(fileName + " - RegexJava 0.1");
        return mensaje;
    }

    private String openFile(File archivo) {
        String contenido = "";
        try {
            fileInput = new FileInputStream(archivo);
            int ascii;
            while ((ascii = fileInput.read()) != -1) {
                char caracter = (char) ascii;
                contenido += caracter;
            }
        } catch (IOException e) {

        }
        String fileName = fileSelect.getName().replace(".er", "");
        this.setTitle(fileName + " - RegexJava 0.1");

        return contenido;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(IDE_Window.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IDE_Window.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IDE_Window.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IDE_Window.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new IDE_Window().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAutomata;
    private javax.swing.JButton btnLexema;
    private javax.swing.JButton btnLexico;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTree jTree1;
    private javax.swing.JLabel lblImg;
    private javax.swing.JTextArea txtConsola;
    private javax.swing.JEditorPane txtFile;
    private javax.swing.JTextArea txtRegular;
    // End of variables declaration//GEN-END:variables
}
