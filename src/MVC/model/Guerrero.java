package MVC.model;

/**
 * Clase Guerrero
 * 
 * @author Jeremi
 * @since 2026-03-01
 */
public class Guerrero extends personajeFisico implements Defendible {
    // Atributos específicos de la clase Guerrero
    private int ira;
    private boolean posturaDefensiva;

    /**
     * Constructor de la clase Guerrero
     * 
     * @param nombre   Nombre del personaje
     * @param nivel    Nivel del personaje
     * @param saludMax Salud máxima del personaje
     * @param fuerza   Fuerza del personaje
     * @param defensa  Defensa del personaje
     * @param ira      Ira del personaje
     */
    public Guerrero(String nombre, int nivel, int saludMax, int fuerza, int defensa, int ira) {
        super(nombre, nivel, saludMax, fuerza, defensa);
        this.ira = ira;
        this.posturaDefensiva = false;
    }

    /**
     * Método propio: Habilidad específica del personaje
     */
    public void gritoDeGuerra() {
        this.ira += 20;
        System.out.println(getNombre() + " lanza un grito de guerrero. ¡Su ira aumenta a " + this.ira + "!");
    }

    /**
     * Método para atacar a un personaje
     * 
     * @param objetivo Personaje objetivo
     */
    @Override
    public void atacar(Personaje objetivo) {
        // Usa el método de la clase padre y le suma un bonus por ira
        int danioFinal = calcularDanio() + (this.ira / 5);
        System.out.println(getNombre() + " ataca brutalmente con su hacha a " + objetivo.getNombre()
                + " causando " + danioFinal + " de daño.");
        objetivo.recibirDanio(danioFinal);
        // Al atacar, pierde la postura defensiva
        this.posturaDefensiva = false;
    }

    /**
     * Método para defender
     */
    @Override
    public void defender() {
        this.posturaDefensiva = true;
        System.out.println(getNombre() + " levanta su escudo y entra en postura defensiva.");
    }

    /**
     * Método para obtener la postura defensiva
     * 
     * @return true si está en postura defensiva, false en caso contrario
     */
    public boolean isPosturaDefensiva() {
        return posturaDefensiva;
    }

    // --- GETTERS ---
    /**
     * Método para obtener la ira
     * 
     * @return Ira del personaje
     */
    public int getIra() {
        return ira;
    }

    // --- SETTERS ---
    /**
     * Método para establecer la ira
     * 
     * @param ira Ira del personaje
     */
    public void setIra(int ira) {
        this.ira = ira;
    }
}