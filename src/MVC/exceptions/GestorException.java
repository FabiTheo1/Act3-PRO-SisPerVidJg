package MVC.exceptions;

/**
 * Clase GestorException
 * 
 * @author Jeremi
 * @since 2026-03-01
 */
public class GestorException extends IllegalArgumentException {
    /**
     * Constructor de la clase GestorException
     * 
     * @param mensaje Mensaje de error
     */
    // Controla la logica del gestor para evitar errores de nulos o duplicados.
    public GestorException(String mensaje) {
        super("[ERROR DEL GESTOR] " + mensaje);
    }
}
