package MVC.model;

public class Guerrero extends personajeFisico implements Defendible {
    private int ira;
    private boolean posturaDefensiva;

    public Guerrero(String nombre, int nivel, int saludMax, int fuerza, int defensa, int ira) {
        super(nombre, nivel, saludMax, fuerza, defensa);
        this.ira = ira;
        this.posturaDefensiva = false;
    }

    // Método 1 propio: Atributo específico
    public void gritoDeGuerra() {
        this.ira += 20;
        System.out.println(getNombre() + " lanza un grito de guerrero. ¡Su ira aumenta a " + this.ira + "!");
    }

    // Implementación obligatoria de la clase abstracta Personaje
    @Override
    public void atacar(Personaje objetivo) {
        // Usa el método de la clase padre y le suma un bonus por ira
        int danioFinal = calcularDanio() + (this.ira / 5);
        System.out.println("⚔️ " + getNombre() + " ataca brutalmente con su hacha a " + objetivo.getNombre()
                + " causando " + danioFinal + " de daño.");
        objetivo.recibirDanio(danioFinal);
        // Al atacar, pierde la postura defensiva
        this.posturaDefensiva = false;
    }

    // Implementación de la interfaz Defendible
    @Override
    public void defender() {
        this.posturaDefensiva = true;
        System.out.println("🛡️ " + getNombre() + " levanta su escudo y entra en postura defensiva.");
    }

    public boolean isPosturaDefensiva() {
        return posturaDefensiva;
    }

    // --- GETTERS ---
    public int getIra() {
        return ira;
    }

    // --- SETTERS ---
    public void setIra(int ira) {
        this.ira = ira;
    }
}