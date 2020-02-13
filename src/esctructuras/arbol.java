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
    private int conteo = 0;

    public arbol() {
        this.raiz = null;
    }

    public nodoArbol getRaiz() {
        return raiz;        
    }
    
    public void inicializarArbol(){
        insert(".", "operacion");
        insertRight("#", "aceptacion", raiz);
    }

    private void setRaiz(nodoArbol raiz) {
        this.raiz = raiz;        
    }

    public void insert(String valor, String tipo) {
        if (raiz != null) {
            insertNodo(valor, tipo, raiz);
        } else {
            this.raiz = new nodoArbol(valor, tipo);
        }
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
            if (nodo.getLeft().getTipo().equals("operacion")) {
                //Caso 2
                if (libreOperacion(nodo.getLeft())) {
                    insertNodo(valor, tipo, nodo.getLeft());
                } else //Caso 3
                {
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
                }
            } //Caso 4
            else if (nodo.getLeft().getTipo().equals("valor")) {
                if (nodo.getRight() == null) {
                    insertRight(valor, tipo, nodo);
                } else {
                    System.out.println("No podes llegar acá es casi imposible a menos que este mal esa mierda");
                }
            } //Caso 5
            else if (nodo.getLeft().getTipo().equals("cerradura")) {
                if (libreCerradura(nodo.getLeft())) {
                    insertNodo(valor, tipo, nodo.getLeft());
                } //Caso 6
                else if (nodo.getRight() == null) {
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
            }
        } else {
            insertLeft(valor, tipo, nodo);
        }
    }

    private void insertInCerradura(String tipo, String valor, nodoArbol nodo) {
        if (nodo.getLeft() != null) {
            System.out.println("No puedes hacer nada jaja, vales verga cerote.");
        } else {
            insertLeft(valor, tipo, nodo);
        }
    }

    private Boolean libreOperacion(nodoArbol nodo) {
        return nodo.getLeft() == null || nodo.getRight() == null;
    }

    private boolean libreCerradura(nodoArbol nodo) {
        return nodo.getLeft() == null;
    }

    private void insertLeft(String valor, String tipo, nodoArbol nodo) {
        nodo.setLeft(new nodoArbol(valor, tipo));
    }

    private void insertRight(String valor, String tipo, nodoArbol nodo) {
        nodo.setRight(new nodoArbol(valor, tipo));
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
                write.println("\"node" + nodo.getValor() + "\"[label = \"<f0> |<f1> " + nodo.getValor() + "|<f2> \"];");

                //Validar hijo izquierdo
                if (nodo.getLeft() != null) {
                    write.println("\"node" + nodo.getValor() + "\":f0 -> \"node" + nodo.getLeft().getValor() + "\":f1;");
                }

                //Validad hijo derecho
                if (nodo.getRight() != null) {
                    write.println("\"node" + nodo.getValor() + "\":f2 -> \"node" + nodo.getRight().getValor() + "\":f1;");
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
