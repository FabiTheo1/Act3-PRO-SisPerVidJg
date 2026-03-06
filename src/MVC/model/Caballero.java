package MVC.model;

public class Caballero extends Fisico {

    private int armadura;
    private int carga;

    public Caballero(String nombre, int nivel, int saludMax, int armadura, int carga) {
        super(nombre, nivel, saludMax);
        this.armadura = armadura;
        this.carga = carga;
    }

    public int getArmadura() {
        return armadura;
    }

    public void setArmadura(int armadura) {
        this.armadura = armadura;
    }

    public int getCarga() {
        return carga;
    }

    public void setCarga(int carga) {
        this.carga = carga;
    }

    // Método para que el caballero ataque a un objetivo, calculando el daño en función de su fuerza, nivel y armadura
    @Override
    public void atacar(Personaje objetivo) {
        int danio = calcularDanio() + (getCarga() + 10); // El daño se calcula como el daño base más la carga del caballero
        System.out.println(getNombre() + " ataca de carga con su lanza a " + objetivo.getNombre() + " causando un daño de " + danio);
        objetivo.setSalud(objetivo.getSalud() - danio); // Se reduce la salud del objetivo en función del daño calculado
    }

    // Método para que el caballero se defienda, reduciendo el daño recibido en función de su armadura y nivel
    public int defensa() {
        int defensa = getArmadura() + (getNivel() * 2); // La defensa se calcula como la armadura más el nivel del caballero multiplicado por 2
        System.out.println(getNombre() + " se defiende con su armadura, reduciendo el daño recibido en " + defensa); // Se muestra un mensaje indicando que el caballero se defiende y la cantidad de daño que se reduce
        return defensa; // Se devuelve el valor de la defensa para que pueda ser utilizado en el cálculo del daño recibido por el caballero
    }
}
