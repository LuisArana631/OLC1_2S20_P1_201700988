package esctructuras;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
import practica1_201700988.Practica1_201700988;

public class classER {

    private int estados = 0;
    private String id;
    private arbol arbolExpresion;
    private ArrayList<classCadena> cadenas;
    private ArrayList<classSiguientes> tablaSiguientes;
    private ArrayList<classEstados> tablaEstados;
    private String numDocumentos;

    public classER(String id, String numDocumentos) {
        this.id = id;
        this.arbolExpresion = new arbol();
        this.cadenas = new ArrayList<>();
        this.tablaSiguientes = new ArrayList<>();
        this.tablaEstados = new ArrayList<>();
        this.arbolExpresion.inicializarArbol();
        this.numDocumentos = numDocumentos;
    }

    public int getEstados() {
        return estados;
    }

    public void setEstados(int estados) {
        this.estados = estados;
    }

    public String getNumDocumentos() {
        return numDocumentos;
    }

    public void setNumDocumentos(String numDocumentos) {
        this.numDocumentos = numDocumentos;
    }

    public ArrayList<classEstados> getTablaEstados() {
        return tablaEstados;
    }

    public void setTablaEstados(ArrayList<classEstados> tablaEstados) {
        this.tablaEstados = tablaEstados;
    }

    public void crearTablaEstados() {
        ArrayList<classEstados> tabla_Estados = new ArrayList<>();
        estados = 0;

        //Obtener el Estado S0 (Primeros del nodo raiz)        
        tabla_Estados.add(new classEstados("S0", arbolExpresion.getRaiz().getPrimeros(), estadoAceptacion(arbolExpresion.getRaiz().getPrimeros())));

        int repetirFor = 1;

        //Insertar los encabezados de estados
        while (repetirFor != 0) {
            repetirFor--;
            //Desglozar todos los estados            
            for (int i = 0; i < tabla_Estados.size(); i++) {
                classEstados actualEstado = tabla_Estados.get(i);

                String numConjunto = actualEstado.getNumContenidos();
                String[] numerosID = numConjunto.split(",");

                //Insertar los encabezados de estados
                for (String numero : numerosID) {
                    try {
                        if (this.tablaSiguientes.get(posIdTablaSiguientes(numero)).getSiguientes() != "") {
                            if (!existeEstadoNumeros(tabla_Estados, this.tablaSiguientes.get(posIdTablaSiguientes(numero)).getSiguientes())) {
                                estados++;
                                tabla_Estados.add(new classEstados("S" + estados, this.tablaSiguientes.get(posIdTablaSiguientes(numero)).getSiguientes(), estadoAceptacion(this.tablaSiguientes.get(posIdTablaSiguientes(numero)).getSiguientes())));
                                repetirFor++;
                            }
                        }

                    } catch (Exception e) {
                        //No hacer nada
                    }
                }

            }
        }

        //Insertar transiciones        
        for (int i = 0; i < tabla_Estados.size(); i++) {
            String numConjunto = tabla_Estados.get(i).getNumContenidos();
            String[] numerosID = numConjunto.split(",");
//            System.out.println(tabla_Estados.get(i).getIdEstado() + "{" + numConjunto + "}");
            for (String numero : numerosID) {
                if (!"".equals(this.tablaSiguientes.get(posIdTablaSiguientes(numero)).getSiguientes())) {
                    String siguientes = this.tablaSiguientes.get(Integer.parseInt(numero) - 1).getSiguientes();
                    int posEstado = posEstadoNumeros(tabla_Estados, siguientes);
                    if (!tabla_Estados.get(i).existeTransicion(tabla_Estados.get(posEstado).getIdEstado(), Integer.parseInt(numero))) {
                        tabla_Estados.get(i).addTransicion(tabla_Estados.get(posEstado).getIdEstado(), this.tablaSiguientes.get(Integer.parseInt(numero) - 1).getValor(), Integer.parseInt(numero));
                        //System.out.println("Sig{" + numero + "}=" + this.tablaSiguientes.get(Integer.parseInt(numero) - 1).getSiguientes() + "=" + tabla_Estados.get(posEstado).getIdEstado());
                    }
                }
            }
        }
        //printEstados();
        //Insertar las conexiones
        this.tablaEstados = tabla_Estados;

    }

    private boolean estadoAceptacion(String numConjunto) {
        String[] numerosID = numConjunto.split(",");
        for (String numero : numerosID) {
            if (Integer.parseInt(numero) == this.tablaSiguientes.get(this.tablaSiguientes.size() - 1).getId()) {
                return true;
            }
        }
        return false;
    }

    public void printEstados() {
        System.out.println("----------------");
        for (int i = 0; i < this.tablaEstados.size(); i++) {
            classEstados estadoActual = this.tablaEstados.get(i);
            System.out.println("Estado {" + estadoActual.getIdEstado() + "}");
            estadoActual.printTransiciones();
        }
    }

    public int posEstadoActual(String idEstado) {
        int pos = 0;
        Iterator<classEstados> iteradorEstados = tablaEstados.iterator();
        while (iteradorEstados.hasNext()) {
            classEstados actualEstado = iteradorEstados.next();
            if (actualEstado.getIdEstado().equals(idEstado)) {
                break;
            }
            pos++;
        }
        return pos;
    }

    public int posIdTablaSiguientes(String numero) {
        int pos = 0;
        Iterator<classSiguientes> iteradorSiguientes = this.tablaSiguientes.iterator();
        while (iteradorSiguientes.hasNext()) {
            classSiguientes actualSiguiente = iteradorSiguientes.next();
            if (actualSiguiente.getId() == Integer.parseInt(numero)) {
                break;
            }
            pos++;
        }
        return pos;
    }

    public int posEstadoNumeros(ArrayList<classEstados> tablaEstados, String numerosConj) {
        int pos = 0;
        Iterator<classEstados> iteradorEstados = tablaEstados.iterator();
        while (iteradorEstados.hasNext()) {
            classEstados actualEstado = iteradorEstados.next();
            if (actualEstado.getNumContenidos().equals(numerosConj)) {
                break;
            }
            pos++;
        }
        return pos;
    }

    public boolean existeEstadoNumeros(ArrayList<classEstados> tablaEstados, String numerosConj) {
        Iterator<classEstados> iteradorEstados = tablaEstados.iterator();
        while (iteradorEstados.hasNext()) {
            classEstados actualEstado = iteradorEstados.next();
            if (actualEstado.getNumContenidos().equals(numerosConj)) {
                return true;
            }
        }
        return false;
    }

    public boolean existeEstado(ArrayList<classEstados> tablaEstados, String id) {
        Iterator<classEstados> iteradorEstados = tablaEstados.iterator();
        while (iteradorEstados.hasNext()) {
            classEstados actualEstado = iteradorEstados.next();
            if (actualEstado.getIdEstado().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public void crearTablaSiguientes() {
        this.tablaSiguientes = this.arbolExpresion.crearTablaSiguientes();
    }

    public ArrayList<classSiguientes> getTablaSiguientes() {
        return tablaSiguientes;
    }

    public void setTablaSiguientes(ArrayList<classSiguientes> tablaSiguientes) {
        this.tablaSiguientes = tablaSiguientes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public arbol getArbolExpresion() {
        return arbolExpresion;
    }

    public void setArbolExpresion(arbol arbolExpresion) {
        this.arbolExpresion = arbolExpresion;
    }

    public ArrayList<classCadena> getCadenas() {
        return cadenas;
    }

    public void insertCadena(String cadena) {
        this.cadenas.add(new classCadena(cadena));
    }

    public void setCadenas(ArrayList<classCadena> cadenas) {
        this.cadenas = cadenas;
    }

    public void insertNodo(String tipo, String valor) {
        this.arbolExpresion.insert(valor, tipo);
    }

    public void mostrarLexemas() {
        Iterator<classCadena> iteradorLexema = cadenas.iterator();
        while (iteradorLexema.hasNext()) {
            classCadena actualLexema = iteradorLexema.next();
            System.out.println("Lexema ingresado: " + actualLexema.getCadena());
        }
    }

    public void mostrarTablaSiguientes() {
        Iterator<classSiguientes> iteradorSiguientes = tablaSiguientes.iterator();
        System.out.println("--Valor--|--ID--|--Siguientes-- ");
        while (iteradorSiguientes.hasNext()) {
            classSiguientes actualSiguiente = iteradorSiguientes.next();
            System.out.println(actualSiguiente.getValor() + " | " + actualSiguiente.getId() + " | " + actualSiguiente.getSiguientes());
        }
    }

    public void graficarTablaEstados() throws IOException {
        if (!tablaSiguientes.isEmpty()) {
            String path = System.getProperty("user.home");
            String Rpath = path;
            Rpath += "\\Desktop";
            path += "\\Desktop\\TablaEstados" + getNumDocumentos() + ".dot";
            File archivo = new File(path);
            if (!archivo.exists()) {
                archivo.createNewFile();
            }

            int countSimbolos = tablaSiguientes.size();
            //Escribimos dentro del archivo .dot
            try (PrintWriter write = new PrintWriter(path, "UTF-8")) {
                write.println("digraph TablaEstados{");
                write.println("tbl [");
                write.println("shape = plaintext");
                write.println("label = <");
                write.println("<table border='0' cellborder='1' color='black' cellspacing='0'>");

                //Encabezado de la tabla
                write.print("<tr><td></td>");
                for (int i = 0; i < countSimbolos - 1; i++) {
                    switch (tablaSiguientes.get(i).getValor()) {
                        case "<":
                            write.print("<td>&lt;</td>");
                            break;
                        case ">":
                            write.print("<td>&gt;</td>");
                            break;
                        case "&":
                            write.print("<td>&amp;</td>");
                            break;
                        default:
                            write.print("<td>" + tablaSiguientes.get(i).getValor() + "</td>");
                    }
                }
                write.println("</tr>");

                //Codigo HTML Tabla
                Iterator<classEstados> iteradorEstados = tablaEstados.iterator();
                while (iteradorEstados.hasNext()) {
                    classEstados actualEstado = iteradorEstados.next();

                    String estadosHTML = "";

                    if (actualEstado.getTransiciones().size() != 0) {
                        estadosHTML = "<tr><td>" + actualEstado.getIdEstado() + "</td>";
                    }

                    for (int j = 0; j < countSimbolos - 1; j++) {
                        estadosHTML += actualEstado.getTransicionHTML(this.tablaSiguientes.get(j).getId());
                    }

                    if (actualEstado.getTransiciones().size() != 0) {
                        estadosHTML += "</tr>";
                    }

                    write.println(estadosHTML);
                }
                //---------------

                write.println("</table>");
                write.println(">];");
                write.println("}");
                write.close();
            } catch (FileNotFoundException | UnsupportedEncodingException e) {
                JOptionPane.showMessageDialog(null, "Error al crear el reporte de archivos." + e, "Error con los archivos.", JOptionPane.ERROR_MESSAGE);
            }

            //Generar la imagen con el comando cmd
            String pathPng = Rpath + "\\TablaEstados" + getNumDocumentos() + ".png";
            crearImagen(path, pathPng);
        }
    }

    public void graficarTablaSiguientes() throws IOException {
        if (!tablaSiguientes.isEmpty()) {
            String path = System.getProperty("user.home");
            String Rpath = path;
            Rpath += "\\Desktop";
            path += "\\Desktop\\TablaSiguientes" + getNumDocumentos() + ".dot";
            File archivo = new File(path);
            if (!archivo.exists()) {
                archivo.createNewFile();
            }
            //Escribimos dentro del archivo .dot
            try (PrintWriter write = new PrintWriter(path, "UTF-8")) {
                write.println("digraph TablaSiguientes{");
                write.println("tbl [");
                write.println("shape = plaintext");
                write.println("label = <");
                write.println("<table border='0' cellborder='1' color='black' cellspacing='0'>");
                write.println("<tr><td>Valor</td><td>Id</td><td>Siguientes</td></tr>");
                //Codigo HTML Tabla
                Iterator<classSiguientes> iteradorSiguientes = tablaSiguientes.iterator();
                while (iteradorSiguientes.hasNext()) {
                    classSiguientes actualSiguiente = iteradorSiguientes.next();
                    switch (actualSiguiente.getValor()) {
                        case "<":
                            write.println("<tr><td>&lt;</td><td>" + actualSiguiente.getId() + "</td><td>" + actualSiguiente.getSiguientes() + "</td></tr>");
                            break;
                        case ">":
                            write.println("<tr><td>&gt;</td><td>" + actualSiguiente.getId() + "</td><td>" + actualSiguiente.getSiguientes() + "</td></tr>");
                            break;
                        case "&":
                            write.println("<tr><td>&amp;</td><td>" + actualSiguiente.getId() + "</td><td>" + actualSiguiente.getSiguientes() + "</td></tr>");
                            break;
                        default:
                            write.println("<tr><td>" + actualSiguiente.getValor() + "</td><td>" + actualSiguiente.getId() + "</td><td>" + actualSiguiente.getSiguientes() + "</td></tr>");
                            break;
                    }

                }
                //---------------
                write.println("</table>");
                write.println(">];");
                write.println("}");
                write.close();
            } catch (FileNotFoundException | UnsupportedEncodingException e) {
                JOptionPane.showMessageDialog(null, "Error al crear el reporte de archivos." + e, "Error con los archivos.", JOptionPane.ERROR_MESSAGE);
            }

            //Generar la imagen con el comando cmd
            String pathPng = Rpath + "\\TablaSiguientes" + getNumDocumentos() + ".png";
            crearImagen(path, pathPng);
        }
    }

    public void graficarAFD() throws IOException {
        if (!tablaEstados.isEmpty()) {
            String path = System.getProperty("user.home");
            String Rpath = path;
            Rpath += "\\Desktop";
            path += "\\Desktop\\AFD" + getNumDocumentos() + ".dot";
            File archivo = new File(path);
            if (!archivo.exists()) {
                archivo.createNewFile();
            }
            //Escribimos dentro del archivo .dot
            try (PrintWriter write = new PrintWriter(path, "UTF-8")) {
                write.println("digraph AFD{");
                write.println("rankdir=LR;");
                write.println("size=\"13\"");

                //Crear nodo de aceptacion
                for (int i = 0; i <= this.tablaEstados.size() - 1; i++) {
                    if (this.tablaEstados.get(i).isAceptacion()) {
                        write.println(this.tablaEstados.get(i).getIdEstado() + "[peripheries = 2, shape=circle];");
                    }
                }

                //Datos de los demas nodos                
                write.println("node [shape=circle,peripheries = 1];");
                write.println("node [fontcolor=black];");
                write.println("edge [color=black];");
                write.println("secret_node [style=invis];");
                write.println("secret_node -> S0 [label=\"inicio\"];");

                //Insertar todas las conexiones
                for (int i = 0; i <= this.tablaEstados.size() - 1; i++) {
                    for (int j = 0; j <= this.tablaEstados.get(i).getTransiciones().size() - 1; j++) {
                        write.println(this.tablaEstados.get(i).getIdEstado() + " -> " + this.tablaEstados.get(i).getTransiciones().get(j).getEstadoNext() + "[label=\"" + this.tablaEstados.get(i).getTransiciones().get(j).getValor() + "\"];");
                    }
                }

                write.println("}");
                write.close();
            } catch (FileNotFoundException | UnsupportedEncodingException e) {
                JOptionPane.showMessageDialog(null, "Error al crear el reporte de archivos." + e, "Error con los archivos.", JOptionPane.ERROR_MESSAGE);
            }

            //Generar la imagen con el comando cmd
            String pathPng = Rpath + "\\AFD" + getNumDocumentos() + ".png";
            crearImagen(path, pathPng);
        }
    }

    private void crearImagen(String rutaDot, String rutaPng) {
        try {
            ProcessBuilder pbuild = new ProcessBuilder("dot", "-Tpng", "-o", rutaPng, rutaDot);
            pbuild.redirectErrorStream(true);
            pbuild.start();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al crear el reporte." + e, "Error con la tabla.", JOptionPane.ERROR_MESSAGE);
        }
    }

}
