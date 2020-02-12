package practica1_201700988;

import analizador.analisisLexico;
import esctructuras.classER;
import java.util.ArrayList;

public class Practica1_201700988 {

    //Instancia de clases
    public static analisisLexico analizador = new analisisLexico();

    //Instancia de datos
    public static int conteoAnalisis = 0;
    public static ArrayList<classER> listER = new ArrayList<>();

    public static void main(String[] args) {
        IDE_Window ventana = new IDE_Window();
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);
        ventana.setResizable(false);
        ventana.setTitle("RegexJava 0.1");

    }

}
