package esctructuras;

public class classCadena {

    private String cadena;
    private Boolean valido;

    public classCadena(String cadena, Boolean valido) {
        this.cadena = cadena;
        this.valido = valido;
    }

    public String getCadena() {
        return cadena;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
    }

    public Boolean getValido() {
        return valido;
    }

    public void setValido(Boolean valido) {
        this.valido = valido;
    }

}
