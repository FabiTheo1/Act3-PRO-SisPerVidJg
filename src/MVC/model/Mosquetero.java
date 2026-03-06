package MVC.model;

public class Mosquetero extends CombatienteDistancia {
    public Mosquetero(String nombre, int nivel, int saludMax, int fuerza, int defensa) {
        super(nombre, nivel, saludMax, fuerza, defensa, new Arma.Mosquete());
    }
}