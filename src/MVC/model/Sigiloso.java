package MVC.model;

public interface Sigiloso {
    public void esconderse();
    
    // Metodo por defecto para avisar de que el personaje se ha escondido
    default void avisarSigilo() {
        System.out.println("[SISTEMA] El personaje ha entrado en modo sigilo.");
    }
}
