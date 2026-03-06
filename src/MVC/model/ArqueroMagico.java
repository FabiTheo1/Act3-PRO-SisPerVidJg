package MVC.model;

public class ArqueroMagico extends PersonajeMagico {
    private Arma.Arco arcoMagico;

    public ArqueroMagico(String nombre, int nivel, int saludMax, int manaMax, int poderMagico) {
        super(nombre, nivel, saludMax, manaMax, poderMagico);
        this.arcoMagico = new Arma.Arco(); 
    }

    @Override
    public void atacar(Personaje objetivo) {
        int costoMana = 10;
        if (gastarMana(costoMana) && arcoMagico.usar()) {
            int danio = (getPoderMagico() + arcoMagico.getDanioBase()) * getNivel();
            System.out.println(getNombre() + " envuelve su flecha en magia y dispara causando " + danio + " de daño arcano!");
            objetivo.recibirDanio(danio);
        } else {
            System.out.println(getNombre() + " intenta disparar pero no tiene maná o flechas.");
        }
    }

    public void recargarArma() {
        arcoMagico.recargar(20);
        System.out.println(getNombre() + " conjura flechas mágicas y recarga su " + arcoMagico.getNombre() + ".");
    }

    // AÑADIDO: Método obligatorio de la clase PersonajeMagico
    @Override
    public void usarHabilidadEspecial() {
        System.out.println(getNombre() + " concentra su poder en el arco, preparándose para un disparo letal.");
    }

    @Override
    public String toString() {
        return super.toString() + " | Mana: " + getMana() + "/" + getManaMax() + " | Arma: " + arcoMagico.toString();
    }
}