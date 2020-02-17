package analizador;

import analizador.token.tipo;
import java.io.FileOutputStream;
import java.util.ArrayList;
import javax.swing.JTextArea;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.util.Iterator;
import practica1_201700988.Practica1_201700988;

public class analisisLexico {

    private ArrayList<token> salida;
    private int estado;
    private String auxiliarLexico = "";
    private int linea = 1;

    public ArrayList<token> Scanner(String entrada) {
        entrada = entrada + "#";
        salida = new ArrayList<>();
        estado = 0;
        auxiliarLexico = "";

        char caracter;
        for (int i = 0; i < entrada.length(); i++) {
            caracter = entrada.charAt(i);
            //Omitir caracter 13
            if (caracter != 13) {
                switch (estado) {
                    case 0:
                        //Evaluar si viene una letra
                        if (Character.isLetter(caracter)) {
                            estado = 7;
                            auxiliarLexico += caracter;
                            break;
                        }//Evaluar numero
                        else if (Character.isDigit(caracter)) {
                            estado = 3;
                            auxiliarLexico += caracter;
                            break;
                        }//Evaluar Simbolos
                        else if (caracter >= 32 && caracter <= 125) {
                            try {
                                if (126 == (entrada.charAt(i + 1)) || 126 == (entrada.charAt(i - 1)) || 44 == (entrada.charAt(i + 1)) || 44 == entrada.charAt(i - 1)) {
                                    auxiliarLexico += caracter;
                                    addToken(tipo.Simbolo);
                                    break;
                                }
                            } catch (Exception e) {

                            }
                            //Evaluar si viene /
                            if (caracter == 47) {
                                estado = 1;
                                auxiliarLexico += caracter;
                                break;
                            } //Evaluar si viene <
                            else if (caracter == 60) {
                                estado = 4;
                                auxiliarLexico += caracter;
                                break;
                            } //Evaluar si viene "
                            else if (caracter == 34) {
                                estado = 5;
                                auxiliarLexico += caracter;
                                break;
                            } //Evaluar si viene %
                            else if (caracter == 37) {
                                estado = 6;
                                auxiliarLexico += caracter;
                                break;
                            } //Evaluar fin del analisis
                            else if (caracter == 35 && i == entrada.length() - 1) {
                                auxiliarLexico += caracter;
                                addToken(tipo.Ultimo);
                                System.out.println("Analisis Lexico terminado.");
                                break;
                            } //Evaluar -
                            else if (caracter == 45) {
                                estado = 14;
                                auxiliarLexico += caracter;
                                break;
                            } //Evaluar llave {
                            else if (caracter == 123) {
                                auxiliarLexico += caracter;
                                addToken(tipo.LLAVE_IZQ);
                                break;
                            } //Evaluar llave } 
                            else if (caracter == 125) {
                                auxiliarLexico += caracter;
                                addToken(tipo.LLAVE_DER);
                                break;
                            } //Evaluar Concatenacion .
                            else if (caracter == 46) {
                                auxiliarLexico += caracter;
                                addToken(tipo.CONCATENACION);
                                break;
                            } //Evaluar Disyuncion |
                            else if (caracter == 124) {
                                auxiliarLexico += caracter;
                                addToken(tipo.DISYUNCION);
                                break;
                            } //Evaluar cerradura *
                            else if (caracter == 42) {
                                auxiliarLexico += caracter;
                                addToken(tipo.CERRADURA_KLEENE);
                                break;
                            } //Evaluar cerradura +
                            else if (caracter == 43) {
                                auxiliarLexico += caracter;
                                addToken(tipo.CERRADURA_POSITIVA);
                                break;
                            } //Evaluar cerradura ?
                            else if (caracter == 63) {
                                auxiliarLexico += caracter;
                                addToken(tipo.CERRADURA_DUDA);
                                break;
                            } //Evaluar coma
                            else if (caracter == 44) {
                                auxiliarLexico += caracter;
                                addToken(tipo.COMA);
                                break;
                            } //Evaluar dos puntos
                            else if (caracter == 58) {
                                auxiliarLexico += caracter;
                                addToken(tipo.DOS_PUNTOS);
                                break;
                            } //Evaluar punto y coma
                            else if (caracter == 59) {
                                auxiliarLexico += caracter;
                                addToken(tipo.PUNTO_COMA);
                                break;
                            }//Espacio 
                            else if (caracter == 32) {
                                //No tomar en cuenta estos caracteres
                                break;
                            } else {
                                auxiliarLexico += caracter;
                                addToken(tipo.Simbolo);
                                break;
                            }
                        } //Evaluar salto de linea
                        else if (caracter == 10 || caracter == 13) {
                            linea++;
                            break;
                        } //Evaluar el macro ~
                        else if (caracter == 126) {
                            auxiliarLexico += caracter;
                            addToken(tipo.Macro);
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
                        //Evaluar la cadena de digitos
                        if (Character.isDigit(caracter)) {
                            auxiliarLexico += caracter;
                            break;
                        } else {
                            addToken(tipo.Numero);
                            i--;
                            break;
                        }

                    case 4:
                        //Evaluar el siguiente caracter !
                        if (caracter == 33) {
                            estado = 9;
                            auxiliarLexico += caracter;
                            break;
                        } else {
                            addToken(tipo.Simbolo);
                            i--;
                            break;
                        }

                    case 5:
                        //Evaluar que venga cualquier contenido hasta encontrar "
                        if (caracter == 34) {
                            auxiliarLexico += caracter;
                            addToken(tipo.Cadena);
                            break;
                        } else {
                            auxiliarLexico += caracter;
                            break;
                        }

                    case 6:
                        //Evaluar caracter %
                        if (caracter == 37) {
                            estado = 10;
                            auxiliarLexico += caracter;
                            break;
                        } else {
                            addToken(tipo.Simbolo);
                            i--;
                            break;
                        }

                    case 7:
                        //Evaluar letra o identificador
                        if (Character.isDigit(caracter)) {
                            auxiliarLexico += caracter;
                            break;
                        } else if (Character.isLetter(caracter)) {
                            auxiliarLexico += caracter;
                            break;
                        } else if (caracter == 95) {
                            auxiliarLexico += caracter;
                            break;
                        } else {
                            if (auxiliarLexico.equals("CONJ")) {
                                addToken(tipo.CONJ);
                            } else if (auxiliarLexico.length() == 1) {
                                addToken(tipo.Caracter);
                            } else {
                                addToken(tipo.Identificador);
                            }
                            i--;
                            break;
                        }

                    case 8:
                        //Evaluar salto de linea
                        if (caracter == 10) {
                            addToken(tipo.Comentario_Lineal);
                            break;
                        } else {
                            auxiliarLexico += caracter;
                            break;
                        }

                    case 9:
                        //Evaluar comentario lineal contenido !
                        if (caracter == 33) {
                            estado = 11;
                            auxiliarLexico += caracter;
                            break;
                        } else {
                            auxiliarLexico += caracter;
                            break;
                        }

                    case 10:
                        //Evaluar cambio de segmento \n
                        if (caracter == 10) {
                            estado = 12;
                            auxiliarLexico += caracter;
                            break;
                        } else {
                            addError(tipo.Error_Lexico, auxiliarLexico);
                            i--;
                            break;
                        }

                    case 11:
                        //Evaluar contenido de comentario multilineal >
                        if (caracter == 62) {
                            auxiliarLexico += caracter;
                            addToken(tipo.Comentario_Multilinea);
                            break;
                        } else {
                            estado = 9;
                            auxiliarLexico += caracter;
                            break;
                        }

                    case 12:
                        //Evaluar siguiente %
                        if (caracter == 37) {
                            estado = 13;
                            auxiliarLexico += caracter;
                            break;
                        } else {
                            addError(tipo.Error_Lexico, auxiliarLexico);
                            i--;
                            break;
                        }

                    case 13:
                        //Evaluar siguiente %
                        if (caracter == 37) {
                            auxiliarLexico += caracter;
                            addToken(tipo.CAMBIO_SEGMENTO);
                            break;
                        } else {
                            addError(tipo.Error_Lexico, auxiliarLexico);
                            break;
                        }

                    case 14:
                        //Evaluar >
                        if (caracter == 62) {
                            auxiliarLexico += caracter;
                            addToken(tipo.ASIGNACION);
                            break;
                        } else {
                            addToken(tipo.Simbolo);
                            i--;
                            break;
                        }

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

    public void imprimirLista(JTextArea consola) {
        consola.setText("--------Resultado de Análisis Lexico---------\n\n");
        salida.forEach((token lexema) -> {
            consola.setText(consola.getText() + lexema.getTipoString() + " <-> " + lexema.getValor() + "\n");
            consola.setText(consola.getText() + "---------------------------------------------------------\n");
        });
        consola.setText(consola.getText() + "-------------------Fin de Reporte------------------\n");
    }

    public ArrayList<token> getSalida() {
        return salida;
    }

    public void setSalida(ArrayList<token> salida) {
        this.salida = salida;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getAuxiliarLexico() {
        return auxiliarLexico;
    }

    public void setAuxiliarLexico(String auxiliarLexico) {
        this.auxiliarLexico = auxiliarLexico;
    }

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }

    public void crearPDF() {
        if (!salida.isEmpty()) {
            String path = System.getProperty("user.home");
            path += "\\Desktop\\ReporteLexico" + Practica1_201700988.conteoAnalisis + ".pdf";

            try {
                Document doc = new Document();
                PdfWriter.getInstance(doc, new FileOutputStream(path));
                doc.open();

                PdfPTable tabla = new PdfPTable(3);
                tabla.addCell("Numero");
                tabla.addCell("Lexema");
                tabla.addCell("Tipo");

                int conteo = 1;

                Iterator<token> iteradorTokens = this.salida.iterator();
                while (iteradorTokens.hasNext()) {
                    token tokenActual = iteradorTokens.next();

                    tabla.addCell(Integer.toString(conteo));
                    tabla.addCell(tokenActual.getValor());
                    tabla.addCell(tokenActual.getTipoString());

                    conteo++;
                }

                doc.add(tabla);
                doc.close();
            } catch (Exception e) {

            }
        }
    }

}
