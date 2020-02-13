package esctructuras;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import javax.swing.JOptionPane;

public class arbol {
    
    private nodoArbol raiz;
    private int hojas = 1;
    private int conteoNodos = 0;
    
    public arbol() {
        this.raiz = null;
    }
    
    public nodoArbol getRaiz() {
        return raiz;
    }
    
    public void inicializarArbol() {
        insert(".", "operacion");
        insertRight("#", "aceptacion", raiz);
    }
    
    public void insert(String valor, String tipo) {
        if (raiz != null) {
            insertNodo(valor, tipo, raiz);
        } else {
            this.raiz = new nodoArbol(valor, tipo, conteoNodos);
            conteoNodos++;
        }
    }
    
    public void idAceptacion() {
        this.raiz.getRight().setId(hojas);
    }
    
    private void insertNodo(String valor, String tipo, nodoArbol nodo) {
        //Insertar nodo inicio analisis
        if (nodo == this.raiz && this.raiz.getLeft() == null) {
            insertLeft(valor, tipo, nodo);
        } //Insertar nodo dependiendo el tipo
        else { //Tipo de nodo que esta actualmente
            switch (nodo.getTipo()) {
                //Operacion -> Operacion
                case "operacion":
                    insertInOperation(tipo, valor, nodo);
                    break;
                //Operacion -> Cerradura
                case "cerradura":
                    insertInCerradura(tipo, valor, nodo);
                    break;
                
            }
        }
    }
    
    private void insertInOperation(String tipo, String valor, nodoArbol nodo) {
        //Caso 1
        if (nodo.getLeft() != null) {
            switch (nodo.getLeft().getTipo()) {
                //Caso 4
                case "operacion":
                    //Caso 2
                    if (libreOperacion(nodo.getLeft())) {
                        insertNodo(valor, tipo, nodo.getLeft());
                    } else //Caso 3
                     if (nodo.getRight() == null) {
                            insertRight(valor, tipo, nodo);
                        } else if (nodo.getRight().getTipo().equals("operacion")) {
                            if (libreOperacion(nodo.getRight())) {
                                insertNodo(valor, tipo, nodo.getRight());
                            } else {
                                System.out.println("Aqui si no hay vuelta atras");
                            }
                        } else if (nodo.getRight().getTipo().equals("cerradura")) {
                            if (libreCerradura(nodo.getRight())) {
                                insertNodo(valor, tipo, nodo.getRight());
                            } else {
                                System.out.println("A ver si aprendes");
                            }
                        }
                    break;
                //Caso 5
                case "valor":
                    if (nodo.getRight() == null) {
                        insertRight(valor, tipo, nodo);
                    } else {
                        System.out.println("Insertando: " + valor + " tipo: " + tipo);
                        
                        System.out.println("No podes llegar acá es casi imposible a menos que este mal esa mierda");
                    }
                    break;
                case "cerradura":
                    if (libreCerradura(nodo.getLeft())) {
                        insertNodo(valor, tipo, nodo.getLeft());
                    } //Caso 6
                    else if (nodo.getLeft().getLeft().getTipo().equals("operacion")) {
                        if (libreOperacion(nodo.getLeft().getLeft())) {
                            insertNodo(valor, tipo, nodo.getLeft());
                        } else if (nodo.getRight() != null) {
                            insertNodo(valor, tipo, nodo.getRight());
                        } else {
                            insertRight(valor, tipo, nodo);
                        }
                    } else if (nodo.getRight() == null) {
                        insertRight(valor, tipo, nodo);
                    } else if (nodo.getRight().getTipo().equals("operacion")) {
                        if (libreOperacion(nodo.getRight())) {
                            insertNodo(valor, tipo, nodo.getRight());
                        } else {
                            System.out.println("Aqui si no hay vuelta atras");
                        }
                    } else if (nodo.getRight().getTipo().equals("cerradura")) {
                        if (libreCerradura(nodo.getRight())) {
                            insertNodo(valor, tipo, nodo.getRight());
                        } else {
                            System.out.println("A ver si aprendes");
                        }
                    }
                    break;
                default:
                    break;
            }
        } else {
            insertLeft(valor, tipo, nodo);
        }
    }
    
    private void insertInCerradura(String tipo, String valor, nodoArbol nodo) {
        if (nodo.getLeft() != null) {
            if (nodo.getLeft().getTipo().equals("operacion")) {
                insertNodo(valor, tipo, nodo.getLeft());
            }
        } else {
            insertLeft(valor, tipo, nodo);
        }
    }
    
    private Boolean libreOperacion(nodoArbol nodo) {
        if (nodo.getLeft() == null || nodo.getRight() == null) {
            return true;
        } else if (nodo.getLeft().getTipo().equals("cerradura") && libreCerradura(nodo.getLeft())) {
            return true;
        } else if (nodo.getRight().getTipo().equals("cerradura") && libreCerradura(nodo.getRight())) {
            return true;
        } else if (nodo.getLeft().getTipo().equals("operacion") && libreOperacion(nodo.getLeft())) {
            return true;
        } else if (nodo.getRight().getTipo().equals("operacion") && libreOperacion(nodo.getRight())) {
            return true;
        }
        return false;
    }
    
    private boolean libreCerradura(nodoArbol nodo) {
        if (nodo.getLeft() == null) {
            return true;
        } else if (nodo.getLeft().getTipo().equals("cerradura") && libreCerradura(nodo.getLeft())) {
            return true;
        } else if (nodo.getLeft().getTipo().equals("operacion") && libreCerradura(nodo.getLeft())) {
            return true;
        }
        return false;
    }
    
    private void insertLeft(String valor, String tipo, nodoArbol nodo) {
        nodoArbol nodoInsert = new nodoArbol(valor, tipo, conteoNodos);
        if (tipo.equals("valor")) {
            nodoInsert.setId(hojas);
            hojas++;
        }
        nodo.setLeft(nodoInsert);
        conteoNodos++;
    }
    
    private void insertRight(String valor, String tipo, nodoArbol nodo) {
        nodo.setRight(new nodoArbol(valor, tipo, conteoNodos));
        conteoNodos++;
    }
    
    private boolean arbolVacio() {
        return this.raiz == null;
    }
    
    public void graficarArbol() throws IOException {
        if (!arbolVacio()) {
            String path = System.getProperty("user.home");
            String Rpath = path;
            Rpath += "\\Desktop";
            path += "\\Desktop\\Arbol.dot";
            File archivo = new File(path);
            if (!archivo.exists()) {
                archivo.createNewFile();
            }
            //Escribimos dentro del archivo .dot
            try (PrintWriter write = new PrintWriter(path, "UTF-8")) {
                write.println("digraph Arbol{");
                write.println("node [shape=record, height=.1];");
                write.close();
            } catch (FileNotFoundException | UnsupportedEncodingException e) {
                JOptionPane.showMessageDialog(null, "Error al crear el reporte de archivos." + e, "Error con los archivos.", JOptionPane.ERROR_MESSAGE);
            }

            //Llamar metodo para escribir el arbol
            crearArbol(this.raiz, path);

            //Terminamos de escribir el codigo
            try (FileWriter escribir = new FileWriter(path, true); PrintWriter write = new PrintWriter(escribir)) {
                write.println("label= \"Reporte de archivos\";");
                write.println("}");
                write.close();
            }

            //Generar la imagen con el comando cmd
            String pathPng = Rpath + "\\Archivos.png";
            crearImagen(path, pathPng);
        }
    }
    
    private void crearImagen(String rutaDot, String rutaPng) {
        try {
            ProcessBuilder pbuild = new ProcessBuilder("dot", "-Tpng", "-o", rutaPng, rutaDot);
            pbuild.redirectErrorStream(true);
            pbuild.start();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al crear el reporte de bitácora." + e, "Error con la bitácora.", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void crearArbol(nodoArbol nodo, String pathDot) throws IOException {
        if (nodo != null) {
            crearArbol(nodo.getLeft(), pathDot);

            //Escribimos dentro del archivo .dot
            try (FileWriter escribir = new FileWriter(pathDot, true); PrintWriter write = new PrintWriter(escribir)) {
                write.println("\"node" + nodo.getNumNodo() + "\"[label = \"<f0> |<f1> " + nodo.getValor() + "|<f2> \"];");

                //Validar hijo izquierdo
                if (nodo.getLeft() != null) {
                    write.println("\"node" + nodo.getNumNodo() + "\":f0 -> \"node" + nodo.getLeft().getNumNodo() + "\":f1;");
                }

                //Validad hijo derecho
                if (nodo.getRight() != null) {
                    write.println("\"node" + nodo.getNumNodo() + "\":f2 -> \"node" + nodo.getRight().getNumNodo() + "\":f1;");
                }
                
                write.close();
            }
            
            crearArbol(nodo.getRight(), pathDot);
        }
    }

    //operacion | .
    //aceptacion #
    //cerradura  + * ?
    //valor id cadena
}
