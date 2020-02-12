package esctructuras;

public class arbol {

    private nodoArbol raiz;

    public arbol() {
        this.raiz = null;
    }

    public nodoArbol getRaiz() {
        return raiz;
    }

    public void setRaiz(nodoArbol raiz) {
        this.raiz = raiz;
    }

    public void insert(String valor, String tipo) {
        if (raiz != null) {
            insertNodo(valor, tipo, raiz);
        } else {
            this.raiz = new nodoArbol(valor, tipo);
        }
    }

    public void insertNodo(String valor, String tipo, nodoArbol nodo) {
        if (tipo.equals("")) {

        }else{
            
        }
    }

}
