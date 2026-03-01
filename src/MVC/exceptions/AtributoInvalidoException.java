package MVC.exceptions;

/**
 * Clase AtributoInvalidoException
 * 
 * @author Jeremi
 * @since 2026-03-01
 */
public class AtributoInvalidoException extends IllegalArgumentException {
    /**
     * Constructor de la clase AtributoInvalidoException
     * 
     * @param mensaje Mensaje de error
     */
    // Controla la logica de no poder crear personajes con atributos invalidos.
    public AtributoInvalidoException(String mensaje) {
        super("ERROR DE ATRIBUTO: " + mensaje);
    }
}
