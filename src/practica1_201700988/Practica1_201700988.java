package practica1_201700988;

import analizador.analisisLexico;
import esctructuras.arbol;
import esctructuras.classConj;
import esctructuras.classER;
import funciones.cargaDatos;
import java.io.IOException;
import java.util.ArrayList;

public class Practica1_201700988 {

    //Instancia de clases
    public static analisisLexico analizador = new analisisLexico();
    public static cargaDatos upDate = new cargaDatos();

    //Instancia de datos
    public static int conteoAnalisis = 0;
    public static ArrayList<classER> listER = new ArrayList<>();
    public static ArrayList<classConj> listConj = new ArrayList<>();

    public static arbol tree = new arbol();

    public static void main(String[] args) throws IOException {
        IDE_Window ventana = new IDE_Window();
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);
        ventana.setResizable(false);
        ventana.setTitle("RegexJava 0.1");
        
        tree.inicializarArbol();
        tree.insert(".", "operacion");
        tree.insert("letra", "valor");
        tree.insert("*","cerradura");
        tree.insert("/", "operacion");
        tree.insert("_", "valor");
        tree.insert("/", "operacion");
        tree.insert("letra", "valor");
        tree.insert("digito", "valor");
        tree.graficarArbol();
        
    }

}
