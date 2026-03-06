package MVC.model;

public class Arquero extends CombatienteDistancia {
    public Arquero(String nombre, int nivel, int saludMax, int fuerza, int defensa) {
        // Forzamos a que siempre aparezca con un Arco
        super(nombre, nivel, saludMax, fuerza, defensa, new Arma.Arco());
    }
}