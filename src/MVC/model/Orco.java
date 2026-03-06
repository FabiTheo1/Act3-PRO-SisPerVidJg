package MVC.model;

/**
 * CLASE ORCO (Enemigo NPC) CREADA POR FABIAN (Director de Juego)
 * Un enemigo para la prueba de combate.
 */
public class Orco extends PersonajeFisico {
    private Arma.EspadaCorta espada;
    private Arma.EscudoPequeno escudo;

    public Orco(String nombre, int nivel, int saludMax, int fuerza, int defensa) {

        super(nombre, nivel, saludMax, fuerza, defensa);
        this.espada = new Arma.EspadaCorta();
        this.escudo = new Arma.EscudoPequeno();
        this.setDefensa(this.getDefensa() + 10);
    }

    @Override
    public void atacar(Personaje objetivo) {
        int danio = getFuerza() + espada.getDanioBase();
        System.out.println(">:) " + getNombre() + " ruge y te golpea brutalmente con su " + espada.getNombre() + " causando " + danio + " ptos de daño.");
        objetivo.recibirDanio(danio);
    }

    @Override
    public String toString() {
        return super.toString() + " | Enemigo equipado con: " + espada.getNombre() + " y " + escudo.getNombre();
    }
}