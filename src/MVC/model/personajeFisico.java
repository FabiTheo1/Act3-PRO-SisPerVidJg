package MVC.model;

import MVC.exceptions.AtributoInvalidoException;

/**
 * Clase PersonajeFisico
 * 
 * @author Jeremi
 * @since 2026-03-01
 */
public abstract class PersonajeFisico extends Personaje {
    private int fuerza;
    private int defensa;

    /**
     * Constructor de la clase PersonajeFisico
     * 
     * @param nombre   Nombre del personaje
     * @param nivel    Nivel del personaje
     * @param saludMax Salud máxima del personaje
     * @param fuerza   Fuerza del personaje
     * @param defensa  Defensa del personaje
     */
    protected PersonajeFisico(String nombre, int nivel, int saludMax, int fuerza, int defensa) {
        super(nombre, nivel, saludMax);
        setFuerza(fuerza);
        setDefensa(defensa);
    }

    // --- GETTERS ---
    /**
     * Método para obtener la fuerza
     * 
     * @return Fuerza del personaje
     */
    public int getFuerza() {
        return fuerza;
    }

    /**
     * Método para obtener la defensa
     * 
     * @return Defensa del personaje
     */
    public int getDefensa() {
        return defensa;
    }

    // --- SETTERS ---
    /**
     * Método para establecer la fuerza
     * 
     * @param fuerza Fuerza del personaje
     */
    public void setFuerza(int fuerza) {
        if (fuerza < 0) {
            throw new AtributoInvalidoException("ERROR: La fuerza no puede ser negativa. Valor recibido: " + fuerza);
        }
        this.fuerza = fuerza;
    }

    /**
     * Método para establecer la defensa
     * 
     * @param defensa Defensa del personaje
     */
    public void setDefensa(int defensa) {
        if (defensa < 0) {
            throw new AtributoInvalidoException("ERROR: La defensa no puede ser negativa. Valor recibido: " + defensa);
        }
        this.defensa = defensa;
    }

    // --- METODOS ---
    /**
     * Método para calcular el daño
     * 
     * @return Daño del personaje
     */
    public int calcularDanio() {
        return getFuerza() * getNivel();
    }
}
