package funciones;

import esctructuras.classCadena;
import esctructuras.classER;
import esctructuras.classEstados;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JTextArea;
import practica1_201700988.Practica1_201700988;

public class validarLexemas {

    public void validarLexemas(JTextArea consola) {

        int posER = 0;
        Iterator<classER> iteradorER = Practica1_201700988.listER.iterator();
        while (iteradorER.hasNext()) {
            classER actualER = iteradorER.next();

            Iterator<classCadena> iteradorLexemas = actualER.getCadenas().iterator();
            while (iteradorLexemas.hasNext()) {
                classCadena actualLexema = iteradorLexemas.next();                
                evaluarLexema(actualLexema.getCadena(), actualER.getTablaEstados(), consola, actualER.getId(), posER);
            }

            posER++;
        }

    }

    private void evaluarLexema(String lexema, ArrayList<classEstados> afd, JTextArea consola, String idER, int posER) {
        classEstados estadoActual = afd.get(0);
        String siNo = "si";
        for (int i = 0; i < lexema.length(); i++) {
            String caracter = Character.toString(lexema.charAt(i));
            System.out.println("caracter: " + caracter);
            String estadoSiguiente = estadoActual.pasoPermitido(caracter);
            if (!estadoSiguiente.equals("****Error****")) {
                estadoActual = afd.get(Practica1_201700988.listER.get(posER).posEstadoActual(estadoSiguiente));
                siNo = " si";
            } else {
                siNo = " no";
                break;
            }
        }
        consola.setText(consola.getText() + "El lexema: " + lexema + siNo + " es válida con la expresión regular: " + idER + "\n");
    }

}
