package practica1_201700988;

import analizador.analisisLexico;
import esctructuras.arbol;
import esctructuras.classConj;
import esctructuras.classER;
import funciones.cargaDatos;
import funciones.validarLexemas;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Practica1_201700988 {

    //Instancia de clases
    public static analisisLexico analizador = new analisisLexico();
    public static cargaDatos upDate = new cargaDatos();
    public static validarLexemas validarLexema = new validarLexemas();

    //Instancia de datos
    public static int conteoAnalisis = 0;
    public static ArrayList<classER> listER = new ArrayList<>();
    public static ArrayList<classConj> listConj = new ArrayList<>();
    public static arbol tree = new arbol();

    //Conteo de ER para nombrar archivo
    public static int conteo_Expresiones = 0;

    public static void main(String[] args) throws IOException {
        IDE_Window ventana = new IDE_Window();
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);
        ventana.setResizable(false);
        ventana.setTitle("RegexJava 0.1");
    }

    //Funciones no importantes solo para mostrar contenido encerrar en comentario
    public static void mostrarConj() {
        Iterator<classConj> iteradorConj = Practica1_201700988.listConj.iterator();
        while (iteradorConj.hasNext()) {
            classConj actualConj = iteradorConj.next();
            System.out.println("CONJ ingresado: " + actualConj.getId());
        }
    }

    public static void mostrarER() {
        Iterator<classER> iteradorER = Practica1_201700988.listER.iterator();
        while (iteradorER.hasNext()) {
            classER actualER = iteradorER.next();
            System.out.println("ER ingresada: " + actualER.getId());
        }
    }

    public static void mostrarLexemas() {
        Iterator<classER> iteradorER = Practica1_201700988.listER.iterator();
        while (iteradorER.hasNext()) {
            classER actualER = iteradorER.next();
            System.out.println("-----Lexemas de " + actualER.getId() + " -----");
            actualER.mostrarLexemas();
            System.out.println("-------------------");
        }
    }

}
