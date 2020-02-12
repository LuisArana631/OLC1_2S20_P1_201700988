package esctructuras;

import analizador.token;
import java.util.ArrayList;
import java.util.Stack;

public class classER {

    private String id;
    private Stack<token> pilaER;
    private arbol arbolExpresion;
    private ArrayList<classCadena> cadenas;

    public classER(String id) {
        this.id = id;
        this.pilaER = new Stack<>();
        this.arbolExpresion = new arbol();
        this.cadenas = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Stack<token> getPilaER() {
        return pilaER;
    }

    public void setPilaER(Stack<token> pilaER) {
        this.pilaER = pilaER;
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

    public void setCadenas(ArrayList<classCadena> cadenas) {
        this.cadenas = cadenas;
    }

}
