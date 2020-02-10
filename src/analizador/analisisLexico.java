package analizador;

import java.util.ArrayList;

public class analisisLexico {

    private ArrayList<token> salida;
    private int estado;
    private String auxiliarLexico = "";
    private String valorAuxiliar = "";
    private String texto;

    public ArrayList<token> Scanner(String entrada) {
        entrada = entrada + "##";
        salida = new ArrayList<token>();
        estado = 0;

        char caracter;
        for (int i = 0; i < entrada.length(); i++) {
            caracter = entrada.charAt(i);
            switch (estado) {
                case 0:
                    if (Character.isLetter(caracter)) {
                        estado = 7;
                        auxiliarLexico += caracter;
                        break;
                    }
                    if (caracter == 47) {
                        estado = 1;
                        auxiliarLexico += caracter;
                        break;
                    }
                    if (Character.isDigit(caracter)) {
                        
                    }
                    
                case 1:
                    //Estado 1
                    break;
                case 2:
                    //Codificar estado de los simbolos
                    break;
            }
        }
        return salida;
    }

}
