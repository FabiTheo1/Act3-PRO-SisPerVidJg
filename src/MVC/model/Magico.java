package MVC.model;

public abstract class Magico extends Personaje {
    private int mana;
    private int manaMax;
    private int poderMagico;

    Magico(String nombre, int nivel, int salud, int saludMax) {
        super(nombre, nivel, salud, saludMax);
    }
}
