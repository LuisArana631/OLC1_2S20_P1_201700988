package esctructuras;

import java.util.ArrayList;

public class classConj {
    
    private String id;
    private ArrayList conjunto;
    
    public classConj(String id) {
        this.id = id;
        this.conjunto = new ArrayList();
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public ArrayList getConjunto() {
        return conjunto;
    }
    
    public void setConjunto(ArrayList conjunto) {
        this.conjunto = conjunto;
    }
    
    public void addItem(Object item) {
        this.conjunto.add(item);
    }
    
}
