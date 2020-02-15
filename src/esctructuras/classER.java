package esctructuras;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;

public class classER {

    private int estados = 0;
    private String id;
    private arbol arbolExpresion;
    private ArrayList<classCadena> cadenas;
    private ArrayList<classSiguientes> tablaSiguientes;
    private ArrayList<classEstados> tablaEstados;

    public classER(String id) {
        this.id = id;
        this.arbolExpresion = new arbol();
        this.cadenas = new ArrayList<>();
        this.tablaSiguientes = new ArrayList<>();
        this.tablaEstados = new ArrayList<>();
        this.arbolExpresion.inicializarArbol();
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
        tabla_Estados.add(new classEstados("S0", arbolExpresion.getRaiz().getPrimeros(), false));
        estados++;

        int repetirFor = 1;

        while (repetirFor != 0) {
            repetirFor--;
            //Desglozar todos los estados
            for (classEstados actualEstado : tablaEstados) {
                System.out.println("Estado actual: " + actualEstado.getIdEstado());

                String numConjunto = actualEstado.getNumContenidos();
                String[] numerosID = numConjunto.split(",");

                for (String numero : numerosID) {
                    if (!existeEstadoNumeros(tablaEstados, this.tablaSiguientes.get(posIdTablaSiguientes(numero)).getSiguientes())) {
                        System.out.println(actualEstado.getIdEstado() + "{" + numConjunto + "}");
                        estados++;
                        tablaEstados.add(new classEstados("S" + estados, this.tablaSiguientes.get(posIdTablaSiguientes(numero)).getSiguientes(), false));
                        System.out.println("Siguientes {" + numero + "}:");
                        System.out.println(this.tablaSiguientes.get(posIdTablaSiguientes(numero)).getSiguientes() + " -> S" + estados);
                        repetirFor++;
                    }
                    //tablaEstados.get(posEstadoActual(estadoActual.getIdEstado())).addTransicion("S" + estados, this.tablaSiguientes.get(posIdTablaSiguientes(numero)).getValor());
                }
            }
        }

        this.tablaEstados = tabla_Estados;
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
            path += "\\Desktop\\TablaEstados.dot";
            File archivo = new File(path);
            if (!archivo.exists()) {
                archivo.createNewFile();
            }

            int countSimbolos = tablaSiguientes.size();
            //Escribimos dentro del archivo .dot
            try ( PrintWriter write = new PrintWriter(path, "UTF-8")) {
                write.println("digraph TablaEstados{");
                write.println("tbl [");
                write.println("shape = plaintext");
                write.println("label = <");
                write.println("<table border='0' cellborder='1' color='black' cellspacing='0'>");

                //Encabezado de la tabla
                write.print("<tr><td></td>");
                for (int i = 0; i < countSimbolos; i++) {
                    write.print("<td>" + tablaSiguientes.get(i).getValor() + "</td>");
                }
                write.println("</tr>");

                //Codigo HTML Tabla
                Iterator<classEstados> iteradorEstados = tablaEstados.iterator();
                while (iteradorEstados.hasNext()) {
                    classEstados actualEstado = iteradorEstados.next();
                    String estadosHTML = "<tr><td>" + actualEstado.getIdEstado() + "</td>";

                    for (int j = 0; j < countSimbolos; j++) {
                        estadosHTML += actualEstado.getTransicionHTML(this.tablaSiguientes.get(j).getValor());
                    }

                    estadosHTML += "</tr>";
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
            String pathPng = Rpath + "\\TablaSiguientes.png";
            crearImagen(path, pathPng);
        }
    }

    public void graficarTablaSiguientes() throws IOException {
        if (!tablaSiguientes.isEmpty()) {
            String path = System.getProperty("user.home");
            String Rpath = path;
            Rpath += "\\Desktop";
            path += "\\Desktop\\TablaSiguientes.dot";
            File archivo = new File(path);
            if (!archivo.exists()) {
                archivo.createNewFile();
            }
            //Escribimos dentro del archivo .dot
            try ( PrintWriter write = new PrintWriter(path, "UTF-8")) {
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
            String pathPng = Rpath + "\\TablaSiguientes.png";
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
