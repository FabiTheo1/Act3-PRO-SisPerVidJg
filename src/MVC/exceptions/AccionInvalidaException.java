package MVC.exceptions;

/**
 * Clase AccionInvalidaException
 * 
 * @author Jeremi
 * @since 2026-03-01
 */
public class AccionInvalidaException extends IllegalStateException {
    /**
     * Constructor de la clase AccionInvalidaException
     * 
     * @param mensaje Mensaje de error
     */
    // Controla la logica de no atacar a muertos o a uno mismo.
    public AccionInvalidaException(String mensaje) {
        super("ERROR DE ACCIÓN: " + mensaje);
    }
}
