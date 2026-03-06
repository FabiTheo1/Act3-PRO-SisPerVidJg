package MVC.model;

/**
 * Interfaz Sigiloso: Define las habilidades de sigilo de un personaje.
 */
public interface Sigiloso {
    /**
     * El personaje se esconde
     */
    void esconderse();
    
    /**
     * Avisa de que el personaje se ha escondido
     */
    default void avisarSigilo() {
        System.out.println("[SISTEMA] El personaje ha entrado en modo sigilo.");
    }
}