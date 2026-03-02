package MVC.model;

/**
 * CLASE COMBATIENTE DISTANCIA (Clase Concreta 6/6) - Creada por Fabian
 * Hereda de Fisico. Destaca por usar el patrón Composición para equipar
 * objetos de tipo Arma, permitiendo cambiar el estilo de ataque dinámicamente.
 */
public class CombatienteDistancia extends Fisico {
    private Arma armaEquipada; // Atributo de composición

    public CombatienteDistancia(String nombre, int nivel, int saludMax, int fuerza, int defensa, Arma arma) {
        super(nombre, nivel, saludMax, fuerza, defensa);
        this.armaEquipada = arma; // Inyección de dependencias a través del constructor
    }

    @Override
    public void atacar(Personaje objetivo) {
        // Delega la responsabilidad de chequear la munición al objeto Arma
        if (armaEquipada.usar()) {
            // El daño se calcula combinando los stats del personaje y los del arma
            int danioTotal = (getFuerza() + armaEquipada.getDanioBase()) * getNivel();
            System.out.println(getNombre() + " usa " + armaEquipada.getNombre() + 
                               " contra " + objetivo.getNombre() + " causando " + danioTotal + " ptos.");
            objetivo.recibirDanio(danioTotal);
        } else {
            System.out.println(getNombre() + " intenta atacar pero no tiene munición para su " + armaEquipada.getNombre() + "!");
        }
    }

    // Setter especial que permite cambiar de arma en tiempo de ejecución
    public void cambiarArma(Arma nuevaArma) {
        System.out.println(getNombre() + " ha cambiado su arma a: " + nuevaArma.getNombre());
        this.armaEquipada = nuevaArma;
    }

    @Override
    public String toString() {
        return super.toString() + " | Arma: " + armaEquipada.toString();
    }
}