package MVC.model;

/**
 * Interfaz Curacion: Define las habilidades de curación de un personaje.
 */
public interface Curacion {
    /**
     * Cura a un objetivo
     * @param objetivo Objetivo al que curar
     * @return Cantidad de salud curada
     */
    public int curar(Personaje objetivo);
    /**
     * Se cura a sí mismo
     * @return Cantidad de salud curada
     */
    public int autocurar();
}
