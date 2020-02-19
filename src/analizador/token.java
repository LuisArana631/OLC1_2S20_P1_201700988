package analizador;

public class token {

    enum tipo {
        //Token de Palabras Reservadas
        CONJ,
        //Token de Signos
        COMA,
        DOS_PUNTOS,
        PUNTO_COMA,
        ASIGNACION,
        LLAVE_IZQ,
        LLAVE_DER,
        CAMBIO_SEGMENTO,
        CONCATENACION,
        DISYUNCION,
        CERRADURA_KLEENE,
        CERRADURA_POSITIVA,
        CERRADURA_DUDA,
        //Token de Extras
        Numero,
        Caracter,
        Simbolo,
        Identificador,
        Comentario_Lineal,
        Comentario_Multilinea,
        Cadena,
        Ultimo,
        Macro,
        //Token de Errores
        Error_Lexico
    }

    //Valores para los tokens de los archivos
    private final tipo tipoToken;
    private final String valor;
    private final int linea;

    public token(tipo tokenType, String auxLexico, int linea) {
        this.tipoToken = tokenType;
        this.valor = auxLexico;
        this.linea = linea;
    }

    public tipo getTipo() {
        return tipoToken;
    }

    public String getValor() {
        return valor;
    }

    public int getLinea() {
        return linea;
    }

    public String getTipoString() {
        switch (tipoToken) {
            case CONJ:
                return "Palabra Reservada";
            case COMA:
                return "Coma";
            case DOS_PUNTOS:
                return "Dos Puntos";
            case PUNTO_COMA:
                return "Punto y coma";
            case ASIGNACION:
                return "Asignacion";
            case LLAVE_IZQ:
                return "Llave Izquierda";
            case LLAVE_DER:
                return "Llave Derecha";
            case CAMBIO_SEGMENTO:
                return "Cambio de Segmento";
            case CONCATENACION:
                return "Concatenacion";
            case DISYUNCION:
                return "Disyuncion";
            case CERRADURA_KLEENE:
                return "Cerradura *";
            case CERRADURA_POSITIVA:
                return "Cerradura +";
            case CERRADURA_DUDA:
                return "Cerradura ?";
            case Numero:
                return "Numero";
            case Identificador:
                return "Identificador";
            case Comentario_Lineal:
                return "Comentario Lineal";
            case Comentario_Multilinea:
                return "Comentario Multilinea";
            case Cadena:
                return "Cadena";
            case Ultimo:
                return "Ultimo";
            case Error_Lexico:
                return "Error Lexico";
            case Macro:
                return "Macro";
            case Simbolo:
                return "Simbolo";
            case Caracter:
                return "Caracter";
            default:
                return "Desconocido";
        }
    }

}
