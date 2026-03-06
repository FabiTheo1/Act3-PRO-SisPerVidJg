package MVC.model;

public class Ballestero extends CombatienteDistancia {
    public Ballestero(String nombre, int nivel, int saludMax, int fuerza, int defensa) {
        super(nombre, nivel, saludMax, fuerza, defensa, new Arma.Ballesta());
    }
}