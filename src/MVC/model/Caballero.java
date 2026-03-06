package MVC.model;

public class Caballero extends PersonajeFisico {
    private int armadura;
    private int carga;
    private Arma armaPrincipal; 
    private Arma escudo;

    public Caballero(String nombre, int nivel, int saludMax, int fuerza, int defensa, int armadura, int carga, Arma armaPrincipal, Arma escudo) {
        super(nombre, nivel, saludMax, fuerza, defensa);
        this.armadura = armadura;
        this.carga = carga;
        this.armaPrincipal = armaPrincipal;
        this.escudo = escudo;
        
        // Si lleva escudo, subimos su defensa base
        if (escudo != null) {
            this.setDefensa(this.getDefensa() + 15);
        }
    }

    @Override
    public void atacar(Personaje objetivo) {
        int danio = calcularDanio() + armaPrincipal.getDanioBase() + (carga + 10); 
        System.out.println(getNombre() + " ataca con su " + armaPrincipal.getNombre() + " a " + objetivo.getNombre() + " causando un daño de " + danio);
        objetivo.recibirDanio(danio);
    }
    
    @Override
    public String toString() {
        return super.toString() + " | Arma: " + armaPrincipal.getNombre() + " | Escudo: " + (escudo != null ? escudo.getNombre() : "Ninguno");
    }
}