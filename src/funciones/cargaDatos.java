package funciones;

import analizador.token;
import esctructuras.classConj;
import esctructuras.classER;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JTextArea;
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
                    if (actualToken.getTipoString().equals("Palabra Reservada")) {
                        state = 1;
                    } else if (actualToken.getTipoString().equals("Identificador")) {
                        if (stateER == 0) {
                            state = 2;
                            idActual = actualToken.getValor();
                            if (!existeER(idActual)) {
                                Practica1_201700988.listER.add(new classER(idActual));
                            }
                        } else if (!existeER(idActual)) {
                            //No hacer nada, incluso reportar que no hay ER para evaluar
                        } else {
                            state = 2;
                            idActual = actualToken.getValor();
                        }
                    } else if (actualToken.getTipoString().equals("Cambio de Segmento")) {
                        if (stateER == 0) {
                            stateER = 1;
                        } else {
                            stateER = 0;
                        }
                    } else {
                        //Nada
                    }
                    break;
                //Insertar conjunto
                case 1:
                    if (actualToken.getTipoString().equals("Identificador")) {
                        idActual = actualToken.getValor();
                        if (!existeConj(idActual)) {
                            Practica1_201700988.listConj.add(new classConj(idActual));
                        }
                    } else if (actualToken.getTipoString().equals("Macro")) {
                        stateConj = 0;
                    } else if (actualToken.getTipoString().equals("Coma")) {
                        stateConj = 1;
                    } else if (actualToken.getTipoString().equals("Punto y coma")) {
                        state = 0;
                    } else {
                        switch (stateConj) {
                            //Cuando se debe calcular el conjunto
                            case 0:
                                if (actualToken.getTipoString().equals("Simbolo")) {
                                    if (Practica1_201700988.listConj.get(posConj(idActual)).getConjunto().size() != 0) {
                                        char inicio = Practica1_201700988.listConj.get(posConj(idActual)).getStart();
                                        char fin = actualToken.getValor().charAt(0);

                                        for (int i = (int) inicio; i < (int) fin + 1; i++) {
                                            if (!Character.isLetterOrDigit((char) i)) {
                                                Practica1_201700988.listConj.get(posConj(idActual)).getConjunto().add((char) i);
                                            }
                                        }
                                    } else {
                                        Practica1_201700988.listConj.get(posConj(idActual)).getConjunto().add(actualToken.getValor());
                                    }
                                } else if (actualToken.getTipoString().equals("Caracter")) {
                                    if (!Practica1_201700988.listConj.get(posConj(idActual)).getConjunto().isEmpty()) {
                                        char inicio = Practica1_201700988.listConj.get(posConj(idActual)).getStart();
                                        char fin = actualToken.getValor().charAt(0);

                                        for (int i = (int) inicio; i < (int) fin + 1; i++) {
                                            if (Character.isLetter((char) i)) {
                                                Practica1_201700988.listConj.get(posConj(idActual)).getConjunto().add((char) i);
                                            }
                                        }
                                    } else {
                                        Practica1_201700988.listConj.get(posConj(idActual)).getConjunto().add(actualToken.getValor());
                                    }
                                } else if (actualToken.getTipoString().equals("Numero")) {
                                    if (!Practica1_201700988.listConj.get(posConj(idActual)).getConjunto().isEmpty()) {
                                        int inicio = Integer.parseInt(Practica1_201700988.listConj.get(posConj(idActual)).getStringStart());
                                        int fin = Integer.parseInt(actualToken.getValor());

                                        for (int i = inicio; i < fin + 1; i++) {
                                            Practica1_201700988.listConj.get(posConj(idActual)).getConjunto().add(i);
                                        }
                                    } else {
                                        Practica1_201700988.listConj.get(posConj(idActual)).getConjunto().add(actualToken.getValor());
                                    }
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
                            Practica1_201700988.listER.get(posER(idActual)).getArbolExpresion().idAceptacion();
                            Practica1_201700988.listER.get(posER(idActual)).getArbolExpresion().calculos();
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

}
