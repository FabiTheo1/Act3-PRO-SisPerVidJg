package MVC.model;

public abstract class Fisico extends Personaje {
    private int fuerza;
    private int defensa;

    protected Fisico(String nombre, int nivel, int saludMax, int fuerza, int defensa) {
        super(nombre, nivel, saludMax);
        this.fuerza = fuerza;
        this.defensa = defensa;
    }

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        if (fuerza >= 0) {
            this.fuerza = fuerza;
        }
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        if (defensa >= 0) {
            this.defensa = defensa;
        }
    }

    // Método para calcular el daño que un personaje físico puede infligir
    public int calcularDanio() {
        return getFuerza() * getNivel(); // El daño se calcula como la fuerza multiplicada por el nivel del personaje
    }
}
