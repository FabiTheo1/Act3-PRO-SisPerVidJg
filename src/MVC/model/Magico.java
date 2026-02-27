package MVC.model;

public abstract class Magico extends Personaje {
    private int mana;
    private int manaMax;
    private int poderMagico;

    protected Magico(String nombre, int nivel, int saludMax, int manaMax, int poderMagico) {
        super(nombre, nivel, saludMax);
        this.mana = manaMax;
        this.manaMax = manaMax;
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
            setMana(getMana() - cantidad); // Utiliza el método setMana para asegurar que el maná no sea negativo
            return true; // El gasto de maná fue exitoso
        }
        return false; // No se pudo gastar el maná
    }

     public void regenerarMana(int cantidad) {
        if (cantidad > 0) {
            setMana(this.mana + cantidad); // Utiliza el método setMana para asegurar que el maná no exceda el máximo
        }
    }
}
