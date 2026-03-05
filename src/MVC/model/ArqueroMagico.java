package MVC.model;

public class ArqueroMagico extends Magico {
    private Arma.Arco arcoMagico;

    public ArqueroMagico(String nombre, int nivel, int saludMax, int manaMax, int poderMagico) {
        super(nombre, nivel, saludMax, manaMax, poderMagico);
        this.arcoMagico = new Arma.Arco(); 
    }

    @Override
    public void atacar(Personaje objetivo) {
        int costoMana = 10;
        
        if (getMana() >= costoMana && arcoMagico.usar()) {
            gastarMana(costoMana);
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

    @Override
    public String toString() {
        return super.toString() + " | Mana: " + getMana() + "/" + getManaMax() + " | Arma: " + arcoMagico.toString();
    }
}