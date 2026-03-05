package MVC.model;

/**
 * CLASE PADRE ABSTRACTA CREADO POR FABIAN (Director de Juego)- CombatienteDistancia
 * Clase abstracta que permite que hereden clases especificas.
 */
public abstract class CombatienteDistancia extends Fisico {
    // Cambiamos a protected para que las clases hijas puedan acceder al arma
    protected Arma armaEquipada; 

    public CombatienteDistancia(String nombre, int nivel, int saludMax, int fuerza, int defensa, Arma arma) {
        super(nombre, nivel, saludMax, fuerza, defensa);
        this.armaEquipada = arma;
    }

    //Eliminación de comentarios innecesarios
    @Override
    public void atacar(Personaje objetivo) {
        if (armaEquipada.usar()) {
            int danioTotal = (getFuerza() + armaEquipada.getDanioBase()) * getNivel();
            System.out.println(getNombre() + " dispara su " + armaEquipada.getNombre() + 
                               " contra " + objetivo.getNombre() + " causando " + danioTotal + " ptos de daño físico.");
            objetivo.recibirDanio(danioTotal);
        } else {
            System.out.println(getNombre() + " intenta atacar pero no tiene munición para su " + armaEquipada.getNombre() + "!");
        }
    }

    // Permite que los personajes que posean de más armas puedan cambiar de arma.
    public void cambiarArma(Arma nuevaArma) {
        System.out.println(getNombre() + " ha cambiado su arma a: " + nuevaArma.getNombre());
        this.armaEquipada = nuevaArma;
    }

    public Arma getArmaEquipada() { return armaEquipada; }

    public void recargarArma() {
        armaEquipada.recargar(10);
        System.out.println(getNombre() + " recarga su " + armaEquipada.getNombre() + ". (Munición: " + armaEquipada.getMunicionActual() + ")");
    }

    @Override
    public String toString() {
        return super.toString() + " | Arma: " + armaEquipada.toString();
    }
}