package esctructuras;

import java.util.ArrayList;

public class classConj {

    private String id;
    private ArrayList<String> conjunto;

    public classConj(String id) {
        this.id = id;
        this.conjunto = new ArrayList<>();
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

    public void setConjunto(ArrayList<String> conjunto) {
        this.conjunto = conjunto;
    }

    public void addItem(String item) {
        this.conjunto.add(item);
    }

    public String getStringStart() {
        return this.conjunto.get(0);
    }

    public char getStart() {
        return this.conjunto.get(0).charAt(0);
    }
}
