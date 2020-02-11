package analizador;

import analizador.token.tipo;
import java.util.ArrayList;

public class analisisLexico {

    private ArrayList<token> salida;
    private int estado;
    private String auxiliarLexico = "";
    private int linea = 1;

    public ArrayList<token> Scanner(String entrada) {
        entrada = entrada + "#";
        salida = new ArrayList<token>();
        estado = 0;

        char caracter;
        for (int i = 0; i < entrada.length(); i++) {
            caracter = entrada.charAt(i);
            switch (estado) {
                case 0:
                    //Evaluar si viene una letra
                    if (Character.isLetter(caracter)) {
                        estado = 7;
                        auxiliarLexico += caracter;
                        break;
                    }//Evaluar numero
                    else if (Character.isLetter(caracter)) {
                        estado = 3;
                        auxiliarLexico += caracter;
                    }//Evaluar Simbolos
                    else if (caracter >= 32 || caracter <= 125) {
                        //Evaluar si viene /
                        if (caracter == 47) {
                            estado = 1;
                            auxiliarLexico += caracter;
                            break;
                        } //Evaluar si viene <
                        else if (caracter == 60) {
                            estado = 10;
                            auxiliarLexico += caracter;
                            break;
                        } //Evaluar si viene "
                        else if (caracter == 34) {
                            estado = 13;
                            auxiliarLexico += caracter;
                            break;
                        } //Evaluar si viene %
                        else if (caracter == 37) {
                            estado = 14;
                            auxiliarLexico += caracter;
                            break;
                        } //Evaluar fin del analisis
                        else if (caracter == 35 && i == entrada.length() - 1) {
                            addToken(tipo.Ultimo);
                            System.out.println("Analisis Lexico terminado.");
                            break;
                        } //Evaluar -
                        else if (caracter == 45) {
                            estado = 22;
                            auxiliarLexico += caracter;
                            break;
                        } //Evaluar llave {
                        else if (caracter == 123) {
                            addToken(tipo.LLAVE_IZQ);
                            break;
                        } //Evaluar llave } 
                        else if (caracter == 125) {
                            addToken(tipo.LLAVE_DER);
                            break;
                        } //Evaluar Concatenacion .
                        else if (caracter == 46) {
                            addToken(tipo.CONCATENACION);
                            break;
                        } //Evaluar Disyuncion |
                        else if (caracter == 124) {
                            addToken(tipo.DISYUNCION);
                            break;
                        } //Evaluar cerradura *
                        else if (caracter == 42) {
                            addToken(tipo.CERRADURA_KLEENE);
                            break;
                        } //Evaluar cerradura +
                        else if (caracter == 43) {
                            addToken(tipo.CERRADURA_POSITIVA);
                            break;
                        } //Evaluar cerradura ?
                        else if (caracter == 63) {
                            addToken(tipo.CERRADURA_DUDA);
                            break;
                        } //Evaluar coma
                        else if (caracter == 44) {
                            addToken(tipo.COMA);
                            break;
                        } //Evaluar dos puntos
                        else if (caracter == 58) {
                            addToken(tipo.DOS_PUNTOS);
                            break;
                        } //Evaluar punto y coma
                        else if (caracter == 59) {
                            addToken(tipo.PUNTO_COMA);
                            break;
                        }//Espacio 
                        else if (caracter == 32) {
                            //No tomar en cuenta estos caracteres
                            break;
                        } else {
                            addToken(tipo.Simbolo);
                        }
                    } //Evaluar salto de linea
                    else if (caracter == 10) {
                        linea++;
                        break;
                    } else {
                        addError(tipo.Error_Lexico, String.valueOf(caracter));
                        break;
                    }

                case 1:
                    //Evaluar si caracter es /
                    if (caracter == 47) {
                        estado = 8;
                        auxiliarLexico += caracter;
                        break;
                    } else {
                        addToken(tipo.Simbolo);
                        i--;
                        break;
                    }

                case 2:
                    //Aceptacion
                    break;

                case 3:
                    if (Character.isDigit(caracter)) {
                        auxiliarLexico += caracter;
                        break;
                    } else {
                        addToken(tipo.Numero);
                        i--;
                        break;
                    }

                case 4:
                    if (caracter == 33) {
                        estado = 9;
                        auxiliarLexico += caracter;
                        break;
                    } else {
                        
                    }
            }
        }
        return salida;
    }

    private void addToken(tipo tipoToken) {
        salida.add(new token(tipoToken, auxiliarLexico, linea));
        limpiarValores();
    }

    private void addError(tipo tipoToken, String datoError) {
        salida.add(new token(tipoToken, datoError, linea));
        limpiarValores();
    }

    private void limpiarValores() {
        auxiliarLexico = "";
        estado = 0;
    }

}
