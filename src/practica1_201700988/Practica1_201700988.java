package practica1_201700988;

import analizador.analisisLexico;

public class Practica1_201700988 {

    public static analisisLexico analizador = new analisisLexico();

    public static void main(String[] args) {
        IDE_Window ventana = new IDE_Window();
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);
        ventana.setResizable(false);
        ventana.setTitle("RegexJava 0.1");

    }

}
