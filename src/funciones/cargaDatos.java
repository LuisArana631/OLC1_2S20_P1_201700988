package funciones;

import analizador.token;
import esctructuras.classConj;
import esctructuras.classER;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import practica1_201700988.IDE_Window;
import practica1_201700988.Practica1_201700988;

public class cargaDatos {

    private String idActual;

    public void cargarDatos(ArrayList<token> listToken) {
        int state = 0;
        int stateConj = 0;
        int stateER = 0;

        Iterator<token> iteradorTokens = listToken.iterator();
        while (iteradorTokens.hasNext()) {
            token actualToken = iteradorTokens.next();

            switch (state) {
                //Decidir que insertar
                case 0:
                    switch (actualToken.getTipoString()) {
                        case "Palabra Reservada":
                            state = 1;
                            break;
                        case "Identificador":
                            if (stateER == 0) {
                                state = 2;
                                idActual = actualToken.getValor();
                                if (!existeER(idActual)) {
                                    Practica1_201700988.listER.add(new classER(idActual, Practica1_201700988.conteo_Expresiones));
                                    Practica1_201700988.conteo_Expresiones++;

                                }
                            } else if (!existeER(idActual)) {
                                //No hacer nada, incluso reportar que no hay ER para evaluar
                            } else {
                                state = 2;
                                idActual = actualToken.getValor();
                            }
                            break;
                        case "Cambio de Segmento":
                            if (stateER == 0) {
                                stateER = 1;
                            } else {
                                stateER = 0;
                            }
                            break;
                        //Nada
                        default:
                            break;
                    }
                    break;

                //Insertar conjunto
                case 1:
                    switch (actualToken.getTipoString()) {
                        case "Identificador":
                            idActual = actualToken.getValor();
                            if (!existeConj(idActual)) {
                                Practica1_201700988.listConj.add(new classConj(idActual));
                            }
                            break;
                        case "Macro":
                            stateConj = 0;
                            break;
                        case "Coma":
                            stateConj = 1;
                            break;
                        case "Punto y coma":
                            state = 0;
                            break;
                        default:
                            switch (stateConj) {
                                //Cuando se debe calcular el conjunto
                                case 0:
                                    switch (actualToken.getTipoString()) {
                                        case "Simbolo":
                                            if (!Practica1_201700988.listConj.get(posConj(idActual)).getConjunto().isEmpty()) {
                                                char inicio = Practica1_201700988.listConj.get(posConj(idActual)).getStart();
                                                char fin = actualToken.getValor().charAt(0);

                                                for (int i = (int) inicio + 1; i < (int) fin + 1; i++) {
                                                    if (!Character.isLetterOrDigit((char) i)) {
                                                        Practica1_201700988.listConj.get(posConj(idActual)).getConjunto().add(Character.toString((char) i));
                                                    }
                                                }
                                            } else {
                                                Practica1_201700988.listConj.get(posConj(idActual)).getConjunto().add(actualToken.getValor());
                                            }
                                            break;
                                        case "Caracter":
                                            if (!Practica1_201700988.listConj.get(posConj(idActual)).getConjunto().isEmpty()) {
                                                char inicio = Practica1_201700988.listConj.get(posConj(idActual)).getStart();
                                                char fin = actualToken.getValor().charAt(0);

                                                for (int i = (int) inicio; i < (int) fin + 1; i++) {
                                                    if (Character.isLetter((char) i)) {
                                                        Practica1_201700988.listConj.get(posConj(idActual)).getConjunto().add(Character.toString((char) i));
                                                    }
                                                }
                                            } else {
                                                Practica1_201700988.listConj.get(posConj(idActual)).getConjunto().add(actualToken.getValor());
                                            }
                                            break;
                                        case "Numero":
                                            if (!Practica1_201700988.listConj.get(posConj(idActual)).getConjunto().isEmpty()) {
                                                int inicio = Integer.parseInt(Practica1_201700988.listConj.get(posConj(idActual)).getStringStart());
                                                int fin = Integer.parseInt(actualToken.getValor());

                                                for (int i = inicio + 1; i < fin + 1; i++) {
                                                    Practica1_201700988.listConj.get(posConj(idActual)).getConjunto().add(Integer.toString(i));
                                                }
                                            } else {
                                                Practica1_201700988.listConj.get(posConj(idActual)).getConjunto().add(actualToken.getValor());
                                            }
                                            break;
                                        default:
                                            break;
                                    }
                                    break;

                                //Cuando es una coma el delimitador
                                case 1:
                                    switch (actualToken.getTipoString()) {
                                        case "Simbolo":
                                            Practica1_201700988.listConj.get(posConj(idActual)).getConjunto().add(actualToken.getValor());
                                            break;
                                        case "Caracter":
                                            Practica1_201700988.listConj.get(posConj(idActual)).getConjunto().add(actualToken.getValor());
                                            break;
                                        case "Numero":
                                            Practica1_201700988.listConj.get(posConj(idActual)).getConjunto().add(actualToken.getValor());
                                            break;
                                        default:
                                            break;
                                    }
                                    break;

                            }
                            break;
                    }
                    break;

                //Insertar er o lexema
                case 2:
                    switch (actualToken.getTipoString()) {
                        case "Identificador":
                            Practica1_201700988.listER.get(posER(idActual)).insertNodo("valor", actualToken.getValor());
                            break;
                        case "Cerradura *":
                            Practica1_201700988.listER.get(posER(idActual)).insertNodo("cerradura", actualToken.getValor());
                            break;
                        case "Cerradura +":
                            Practica1_201700988.listER.get(posER(idActual)).insertNodo("cerradura", actualToken.getValor());
                            break;
                        case "Cerradura ?":
                            Practica1_201700988.listER.get(posER(idActual)).insertNodo("cerradura", actualToken.getValor());
                            break;
                        case "Disyuncion":
                            Practica1_201700988.listER.get(posER(idActual)).insertNodo("operacion", actualToken.getValor());
                            break;
                        case "Concatenacion":
                            Practica1_201700988.listER.get(posER(idActual)).insertNodo("operacion", actualToken.getValor());
                            break;
                        case "Cadena":
                            switch (stateER) {
                                //Ingresar ER
                                case 0:
                                    Practica1_201700988.listER.get(posER(idActual)).insertNodo("valor", actualToken.getValor().substring(1, actualToken.getValor().length() - 1));
                                    break;
                                //Ingresar Lexema
                                case 1:
                                    Practica1_201700988.listER.get(posER(idActual)).insertCadena(actualToken.getValor().substring(1, actualToken.getValor().length() - 1));
                                    break;
                            }
                            break;
                        case "Punto y coma":
                            state = 0;
                            //Agregar numero a la hoja # de aceptacion
                            Practica1_201700988.listER.get(posER(idActual)).getArbolExpresion().idAceptacion();
                            //Calcular anulable, primeros y ultimos
                            Practica1_201700988.listER.get(posER(idActual)).getArbolExpresion().calculos();
                            //Crear tabla de siguientes
                            Practica1_201700988.listER.get(posER(idActual)).crearTablaSiguientes();
                            //Crear tabla de estados
                            Practica1_201700988.listER.get(posER(idActual)).crearTablaEstados();
                            //Crear archivos
                            try {
                                Practica1_201700988.listER.get(posER(idActual)).getArbolExpresion().graficarArbol();
                                Practica1_201700988.listER.get(posER(idActual)).graficarTablaSiguientes();
                                Practica1_201700988.listER.get(posER(idActual)).graficarTablaEstados();
                                Practica1_201700988.listER.get(posER(idActual)).graficarAFD();
                            } catch (IOException ex) {
                                Logger.getLogger(IDE_Window.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            break;
                        default:
                            break;
                    }
                    break;

            }

        }
    }

    private int posER(String id) {
        int pos = 0;
        Iterator<classER> iteradorER = Practica1_201700988.listER.iterator();
        while (iteradorER.hasNext()) {
            classER actualER = iteradorER.next();
            if (actualER.getId().equals(id)) {
                break;
            }
            pos++;
        }
        return pos;
    }

    public void escribirConsola(JTextArea consola, String text) {
        consola.setText(consola.getText() + text);
    }

    private boolean existeER(String id) {
        Iterator<classER> iteradorER = Practica1_201700988.listER.iterator();
        while (iteradorER.hasNext()) {
            classER actualER = iteradorER.next();
            if (actualER.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    private int posConj(String id) {
        int pos = 0;
        Iterator<classConj> iteradorConj = Practica1_201700988.listConj.iterator();
        while (iteradorConj.hasNext()) {
            classConj actualConj = iteradorConj.next();
            if (actualConj.getId().equals(id)) {
                break;
            }
            pos++;
        }
        return pos;
    }

    private boolean existeConj(String id) {
        Iterator<classConj> iteradorConj = Practica1_201700988.listConj.iterator();
        while (iteradorConj.hasNext()) {
            classConj actualConj = iteradorConj.next();
            if (actualConj.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public DefaultTreeModel treeVisual() {
        DefaultMutableTreeNode inicial = new DefaultMutableTreeNode("Autómata");
        DefaultTreeModel raiz = new DefaultTreeModel(inicial);

        DefaultMutableTreeNode arbol = new DefaultMutableTreeNode("Árboles");
        DefaultMutableTreeNode tablaSiguientes = new DefaultMutableTreeNode("Tablas de Siguientes");
        DefaultMutableTreeNode tablaEstados = new DefaultMutableTreeNode("Tablas de Estados");
        DefaultMutableTreeNode afd = new DefaultMutableTreeNode("AFD");

        inicial.add(arbol);
        inicial.add(tablaSiguientes);
        inicial.add(tablaEstados);
        inicial.add(afd);

        System.out.println("Cantidad de ER: " + Practica1_201700988.listER.size());
        for (int i = 0; i < Practica1_201700988.listER.size(); i++) {
            DefaultMutableTreeNode arbolER = new DefaultMutableTreeNode(Practica1_201700988.listER.get(i).getId());
            DefaultMutableTreeNode tablaSER = new DefaultMutableTreeNode(Practica1_201700988.listER.get(i).getId());
            DefaultMutableTreeNode tablaEER = new DefaultMutableTreeNode(Practica1_201700988.listER.get(i).getId());
            DefaultMutableTreeNode afdER = new DefaultMutableTreeNode(Practica1_201700988.listER.get(i).getId());

            arbol.add(arbolER);
            tablaSiguientes.add(tablaSER);
            tablaEstados.add(tablaEER);
            afd.add(afdER);
        }

        return raiz;
    }

}
