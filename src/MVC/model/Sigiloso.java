package MVC.model;

/**
<<<<<<< HEAD
 * Interfaz Sigiloso: Define las habilidades de sigilo de un personaje.
 */
public interface Sigiloso {
    /**
     * El personaje se esconde
     */
    public void esconderse();
    
    /**
     * Avisa de que el personaje se ha escondido
     */
    default void avisarSigilo() {
        System.out.println("[SISTEMA] El personaje ha entrado en modo sigilo.");
    }
}
=======
 * Interfaz Sigiloso para personajes que pueden ocultarse o usar las sombras.
 * 
 * @author Jeremi
 * @since 2026-03-01
 */
public interface Sigiloso {
    /**
     * Método para ocultarse y obtener ventajas tácticas.
     */
    void ocultarse();
}
>>>>>>> origin/PersonajesFisicos-Jeremi
