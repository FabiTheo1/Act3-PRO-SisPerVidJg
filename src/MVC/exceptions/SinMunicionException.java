package MVC.exceptions;

/**
 * Clase SinMunicionException
 * * @author Jeremi
 * 
 * @since 2026-03-01
 */
public class SinMunicionException extends IllegalStateException {
    /**
     * Constructor de la clase SinMunicionException
     * * @param mensaje Mensaje de error
     */
    // Controla la logica de que un personaje no tenga municion para realizar un
    // ataque (aplicable para personajes que lanzan objetos para atacar)
    public SinMunicionException(String mensaje) {
        super("ERROR DE MUNICIÓN: " + mensaje);
    }
}