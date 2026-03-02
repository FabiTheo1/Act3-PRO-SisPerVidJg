package MVC.model;

/**
 * (Prueba de comentarios estilo JavaDoc)
 * CLASE COMBATIENTE DISTANCIA
 * Puede usar cualquier tipo de arma a distancia (Arcos, Ballestas, Jabalinas).
 */
public class CombatienteDistancia extends Fisico {
    private Arma armaEquipada;

    public CombatienteDistancia(String nombre, int nivel, int saludMax, int fuerza, int defensa, Arma arma) {
        super(nombre, nivel, saludMax, fuerza, defensa);
        this.armaEquipada = arma;
    }

    @Override
    public void atacar(Personaje objetivo) {
        if (armaEquipada.usar()) {
            // El daño ahora es: (Fuerza del personaje + Daño del arma) * Nivel
            int danioTotal = (getFuerza() + armaEquipada.getDanioBase()) * getNivel();
            System.out.println(getNombre() + " usa " + armaEquipada.getNombre() + 
                               " contra " + objetivo.getNombre() + " causando " + danioTotal + " ptos.");
            objetivo.recibirDanio(danioTotal);
        } else {
            System.out.println(getNombre() + " intenta atacar pero no tiene munición para su " + armaEquipada.getNombre() + "!");
        }
    }

    // Permite cambiar de arma en tiempo de ejecución (Flexibilidad total)
    public void cambiarArma(Arma nuevaArma) {
        System.out.println(getNombre() + " ha cambiado su arma a: " + nuevaArma.getNombre());
        this.armaEquipada = nuevaArma;
    }

    @Override
    public String toString() {
        return super.toString() + " | Arma: " + armaEquipada.toString();
    }
}