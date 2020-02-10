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
                
            }
        }

    }

}
