package esctructuras;

import java.util.ArrayList;
import java.util.Iterator;

public class classER {

    private String id;
    private arbol arbolExpresion;
    private ArrayList<classCadena> cadenas;

    public classER(String id) {
        this.id = id;
        this.arbolExpresion = new arbol();
        this.cadenas = new ArrayList<>();
        this.arbolExpresion.inicializarArbol();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public arbol getArbolExpresion() {
        return arbolExpresion;
    }

    public void setArbolExpresion(arbol arbolExpresion) {
        this.arbolExpresion = arbolExpresion;
    }

    public ArrayList<classCadena> getCadenas() {
        return cadenas;
    }

    public void insertCadena(String cadena) {
        this.cadenas.add(new classCadena(cadena));
    }

    public void setCadenas(ArrayList<classCadena> cadenas) {
        this.cadenas = cadenas;
    }

    public void insertNodo(String tipo, String valor) {
        this.arbolExpresion.insert(valor, tipo);
    }
    
    public void mostrarLexemas() {
        Iterator<classCadena> iteradorLexema = cadenas.iterator();
        while (iteradorLexema.hasNext()) {
            classCadena actualLexema = iteradorLexema.next();
            System.out.println("Lexema ingresado: " + actualLexema.getCadena());
        }
    }

}
