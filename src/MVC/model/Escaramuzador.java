package MVC.model;

public class Escaramuzador extends CombatienteDistancia {
    private Arma armaSecundaria; // Daga o Espada corta
    private Arma escudo; // Escudo pequeño


    // Constructor que recibe las dos armas
    public Escaramuzador(String nombre, int nivel, int saludMax, int fuerza, int defensa, Arma armaPrincipal, Arma armaSecundaria) {
        super(nombre, nivel, saludMax, fuerza, defensa, armaPrincipal);
        this.armaSecundaria = armaSecundaria;
        
        // Siempre le equipamos el escudo pequeño y le subimos la defensa base
        this.escudo = new Arma.EscudoPequeno();
        this.setDefensa(this.getDefensa()+10); // Bonus por el escudo

        // Validamos que el arma secundaria sea estrictamente Daga o Espada Corta
        if (armaSecundaria instanceof Arma.Daga || armaSecundaria instanceof Arma.EspadaCorta) {
            this.armaSecundaria = armaSecundaria;
        } else {
            // Si el usuario intenta pasarle otra cosa (ej. un arco), le ponemos una Daga por defecto
            System.out.println("⚠️ Error: El Escaramuzador solo puede usar Daga o Espada Corta como secundaria. Equipando Daga por defecto.");
            this.armaSecundaria = new Arma.Daga();
        }
    }

    @Override
    public void atacar(Personaje objetivo) {
        // Primero ataca a distancia
        super.atacar(objetivo);
        
        // Luego hace un ataque cuerpo a cuerpo con arma secundaria (teniendo el escudo equipado)
        int danioExtra = getFuerza() + armaSecundaria.getDanioBase();

        System.out.println(
            getNombre() + " se cubre con su " + escudo.getNombre() + " y ataca ágilmente con su " + 
            armaSecundaria.getNombre() + " causando " + 
            danioExtra + " ptos de daño extra a" + objetivo.getNombre() + ".");

        objetivo.recibirDanio(danioExtra);

    }
    
    @Override
    public String toString() {
        return super.toString() + " | Secundaria: " + armaSecundaria.getNombre() + " | Protegido con: " + escudo.getNombre();
    }
}