package esctructuras;

import java.util.ArrayList;
import java.util.Iterator;

public class classEstados {

    private String idEstado;
    private ArrayList<classEstadosNext> transiciones;
    private String numContenidos;
    private boolean aceptacion;

    public classEstados(String idEstado, String numContenidos, boolean aceptacion) {
        this.idEstado = idEstado;
        this.numContenidos = numContenidos;
        this.transiciones = new ArrayList<>();
        this.aceptacion = aceptacion;
    }

    public String pasoPermitido(int caracter) {
        Iterator<classEstadosNext> iteradorNext = this.transiciones.iterator();
        while (iteradorNext.hasNext()) {
            classEstadosNext actualNext = iteradorNext.next();

            if (actualNext.getIdVal() == caracter) {
                return actualNext.getEstadoNext();
            }

        }
        return "****Error****";
    }

    public boolean isAceptacion() {
        return aceptacion;
    }

    public void setAceptacion(boolean aceptacion) {
        this.aceptacion = aceptacion;
    }

    public String getNumContenidos() {
        return numContenidos;
    }

    public void setNumContenidos(String numContenidos) {
        this.numContenidos = numContenidos;
    }

    public void addTransicion(String estadoNext, String valor, int idValor) {
        this.transiciones.add(new classEstadosNext(estadoNext, valor, idValor));
    }

    public String getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(String idEstado) {
        this.idEstado = idEstado;
    }

    public ArrayList<classEstadosNext> getTransiciones() {
        return transiciones;
    }

    public void setTransiciones(ArrayList<classEstadosNext> transiciones) {
        this.transiciones = transiciones;
    }

    public boolean existeTransicion(String estado, int valor) {
        Iterator<classEstadosNext> iteradorNext = this.transiciones.iterator();
        while (iteradorNext.hasNext()) {
            classEstadosNext nextActual = iteradorNext.next();
            if (nextActual.getEstadoNext().equals(estado) && nextActual.getIdVal() == valor) {
                return true;
            }
        }
        return false;
    }

    public void printTransiciones() {
        for (int i = 0; i < this.transiciones.size(); i++) {
            classEstadosNext estadoNextActual = this.transiciones.get(i);
            System.out.println("Transicion: " + estadoNextActual.getValor() + "->" + estadoNextActual.getEstadoNext());
        }
    }

    public String getTransicionHTML(int val) {
        String salidaHTML = "";
        for (int i = 0; i < this.transiciones.size(); i++) {
            classEstadosNext estadoNextActual = this.transiciones.get(i);
            if (val == estadoNextActual.getIdVal()) {
                salidaHTML = "<td>" + estadoNextActual.getEstadoNext() + "</td>";
                break;
            } else {
                salidaHTML = "<td></td>";
            }
        }
        return salidaHTML;
    }

}
