package co.edu.unbosque.exceptions;

import co.edu.unbosque.exceptions.SGECException.TipoError;

/**
 * Excepción base del sistema SGEC
 */
public class SGECException extends Exception {
    private final TipoError tipo;
    private final String campo;
    
    public enum TipoError {
        VALIDACION,
        NO_ENCONTRADO,
        DUPLICADO,
        REGLA_NEGOCIO,
        PERSISTENCIA
    }
    
    public SGECException(TipoError tipo, String mensaje) {
        super(mensaje);
        this.tipo = tipo;
        this.campo = null;
    }
    
    public SGECException(TipoError tipo, String campo, String mensaje) {
        super(mensaje);
        this.tipo = tipo;
        this.campo = campo;
    }
    
    public SGECException(TipoError tipo, String mensaje, Throwable causa) {
        super(mensaje, causa);
        this.tipo = tipo;
        this.campo = null;
    }
    
    public TipoError getTipo() {
        return tipo;
    }
    
    public String getCampo() {
        return campo;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(tipo).append("]");
        if (campo != null) {
            sb.append(" Campo '").append(campo).append("'");
        }
        sb.append(": ").append(getMessage());
        return sb.toString();
    }
    
    // Métodos factory estáticos para crear excepciones comunes
    
    public static SGECException validacion(String campo, String mensaje) {
        return new SGECException(TipoError.VALIDACION, campo, mensaje);
    }
    
    public static SGECException noEncontrado(String entidad, String identificador) {
        return new SGECException(TipoError.NO_ENCONTRADO, 
            String.format("%s con código '%s' no encontrado", entidad, identificador));
    }
    
    public static SGECException duplicado(String entidad, String identificador) {
        return new SGECException(TipoError.DUPLICADO,
            String.format("%s con código '%s' ya existe", entidad, identificador));
    }
    
    public static SGECException reglaNegocio(String mensaje) {
        return new SGECException(TipoError.REGLA_NEGOCIO, mensaje);
    }
    
    public static SGECException persistencia(String mensaje) {
        return new SGECException(TipoError.PERSISTENCIA, mensaje);
    }
    
    public static SGECException persistencia(String mensaje, Throwable causa) {
        return new SGECException(TipoError.PERSISTENCIA, mensaje, causa);
    }
}