package MVC.model;

import MVC.exceptions.ManaInsuficienteException;

/**
 * Clase personajeMagico: Clase abstracta que representa a un personaje mágico.
 * @author Jonay Rivero
 */
public abstract class personajeMagico extends Personaje  {
    protected int mana;
    protected int manaMax;
    protected int poderMagico;

    /**
     * Constructor de la clase personajeMagico
     * @param nombre Nombre del personaje mágico
     * @param nivel Nivel del personaje mágico
     * @param saludMax Salud máxima del personaje mágico
     * @param mana Mana del personaje mágico
     * @param poderMagico Poder mágico del personaje mágico
     */
    protected personajeMagico(String nombre, int nivel, int saludMax, int mana, int poderMagico) {
        super(nombre, nivel, saludMax);
        this.manaMax = mana;
        this.mana = mana;
        this.poderMagico = poderMagico;
    }

    /**
     * Obtiene el maná del personaje mágico
     * @return Maná del personaje mágico
     */
    public int getMana() {
        return mana;
    }

    /**
     * Establece el maná del personaje mágico
     * @param mana Maná del personaje mágico
     */
    public void setMana(int mana) {
        if (mana >= 0 && mana <= this.manaMax) {
            this.mana = mana;
        } else if (mana < 0) {
            this.mana = 0; // Si el maná es menor que 0, se establece en 0
        } else {
            this.mana = this.manaMax; // Si el maná es mayor que la salud máxima, se establece en la salud máxima
        }
    }

    /**
     * Obtiene el maná máximo del personaje mágico
     * @return Maná máximo del personaje mágico
     */
    public int getManaMax() {
        return manaMax;
    }

    /**
     * Establece el maná máximo del personaje mágico
     * @param manaMax Maná máximo del personaje mágico
     */
    public void setManaMax(int manaMax) {
        if (manaMax > 0) {
            this.manaMax = manaMax;
        }
    }

    /**
     * Obtiene el poder mágico del personaje mágico
     * @return Poder mágico del personaje mágico
     */
    public int getPoderMagico() {
        return poderMagico;
    }

    /**
     * Establece el poder mágico del personaje mágico
     * @param poderMagico Poder mágico del personaje mágico
     */
    public void setPoderMagico(int poderMagico) {
        if (poderMagico >= 0) {
            this.poderMagico = poderMagico;
        }
    }

    /**
     * Gasta maná 
     * @param cantidad Cantidad de maná a gastar
     * @return true si se gastó maná, false en caso contrario
     */
    public boolean gastarMana(int cantidad) {
        if (cantidad > 0 && this.mana >= cantidad) {
            setMana(getMana() - cantidad);
            return true;
        }
        return false;
    }

    /**
     * Regenera maná
     * @param cantidad Cantidad de maná a regenerar
     */
    public void regenerarMana(int cantidad) {
        if (cantidad > 0) {
            setMana(this.mana + cantidad); // Utiliza el método setMana para asegurar que el maná no exceda el máximo
        }
    }

    /**
     * Consume maná
     * @param cantidad Cantidad de maná a consumir
     * @throws ManaInsuficienteException Si no hay maná suficiente
     */
    protected void consumirMana(int cantidad) throws ManaInsuficienteException {
        if (this.mana < cantidad) {
            throw new ManaInsuficienteException(
                    getNombre() + " no tiene maná suficiente (" + mana + "/" + cantidad + ")"); // Lanza la excepción si no hay maná suficiente
        }
        this.mana -= cantidad;
    }

    /**
     * Usa la habilidad especial del personaje mágico
     */
    public abstract void usarHabilidadEspecial();

    /**
     * Ataca a un objetivo
     * @param objetivo Objetivo al que atacar
     */
    public abstract void atacar(Personaje objetivo);
    
}
