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

    public void addTransicion(String estadoNext, String valor) {
        this.transiciones.add(new classEstadosNext(estadoNext, valor));
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

    public String getTransicionHTML(String simbolo) {
        String salidaHTML = "";
        Iterator<classEstadosNext> iteradorEstadosSiguientes = this.transiciones.iterator();
        while (iteradorEstadosSiguientes.hasNext()) {
            classEstadosNext estadoNextActual = iteradorEstadosSiguientes.next();
            System.out.println(estadoNextActual.getEstadoNext() + " -> " + estadoNextActual.getValor());
            if (simbolo.equals(estadoNextActual.getValor())) {
                salidaHTML = "<td>" + estadoNextActual.getEstadoNext() + "</td>";
            } else {
                salidaHTML = "<td></td>";
            }

        }
        return salidaHTML;
    }

}
