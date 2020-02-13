package esctructuras;

public class nodoArbol {

    private boolean anulable;
    private String tipo;    
    private int id;
    private int primeros;
    private int ultimos;
    private String valor;
    private nodoArbol left;
    private nodoArbol right;

    public nodoArbol(String valor, String tipo) {
        this.valor = valor;
        this.tipo = tipo;
        this.left = null;
        this.right = null;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public nodoArbol getLeft() {
        return left;
    }

    public void setLeft(nodoArbol left) {
        this.left = left;
    }

    public nodoArbol getRight() {
        return right;
    }

    public void setRight(nodoArbol right) {
        this.right = right;
    }

    public Boolean getAnulable() {
        return anulable;
    }

    public void setAnulable(Boolean anulable) {
        this.anulable = anulable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrimeros() {
        return primeros;
    }

    public void setPrimeros(int primeros) {
        this.primeros = primeros;
    }

    public int getUltimos() {
        return ultimos;
    }

    public void setUltimos(int ultimos) {
        this.ultimos = ultimos;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

}
