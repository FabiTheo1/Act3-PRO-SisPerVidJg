package MVC.model;

/**
 * Interfaz Lanzar para personajes que pueden lanzar objetos a distancia.
 * 
 * @author Jeremi
 * @since 2026-03-01
 */
public interface Lanzar {
    /**
     * Lanza un objeto a un objetivo causando daño.
     * 
     * @param objetivo Personaje objetivo
     */
    void lanzarObjeto(Personaje objetivo);
}