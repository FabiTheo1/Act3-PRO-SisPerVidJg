package MVC.model;

import MVC.exceptions.ManaInsuficienteException;

public abstract class personajeMagico extends Personaje  {
    protected int mana;
    protected int manaMax;
    protected int poderMagico;

    protected personajeMagico(String nombre, int nivel, int saludMax, int mana, int poderMagico) {
        super(nombre, nivel, saludMax);
        this.manaMax = mana;
        this.mana = mana;
        this.poderMagico = poderMagico;
    }

    public int getMana() {
        return mana;
    }

    // Rango de maná
    public void setMana(int mana) {
        if (mana >= 0 && mana <= this.manaMax) {
            this.mana = mana;
        } else if (mana < 0) {
            this.mana = 0; // Si el maná es menor que 0, se establece en 0
        } else {
            this.mana = this.manaMax; // Si el maná es mayor que la salud máxima, se establece en la salud máxima
        }
    }

    public int getManaMax() {
        return manaMax;
    }

    // Rango de maná máximo
    public void setManaMax(int manaMax) {
        if (manaMax > 0) {
            this.manaMax = manaMax;
        }
    }

    public int getPoderMagico() {
        return poderMagico;
    }

    public void setPoderMagico(int poderMagico) {
        if (poderMagico >= 0) {
            this.poderMagico = poderMagico;
        }
    }

    public boolean gastarMana(int cantidad) {
        if (cantidad > 0 && this.mana >= cantidad) {
            setMana(getMana() - cantidad);
            return true;
        }
        return false;
    }

    public void regenerarMana(int cantidad) {
        if (cantidad > 0) {
            setMana(this.mana + cantidad); // Utiliza el método setMana para asegurar que el maná no exceda el máximo
        }
    }

    protected void consumirMana(int cantidad) throws ManaInsuficienteException {
        if (this.mana < cantidad) {
            throw new ManaInsuficienteException(
                    getNombre() + " no tiene maná suficiente (" + mana + "/" + cantidad + ")"); // Lanza la excepción si no hay maná suficiente
        }
        this.mana -= cantidad;
    }

    public abstract void usarHabilidadEspecial();

    public abstract void atacar(Personaje objetivo);
    
}
