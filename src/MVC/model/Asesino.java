package MVC.model;

/**
 * Clase Asesino
 * 
 * @author Jeremi
 * @since 2026-03-01
 */

public class Asesino extends personajeFisico {
    // Atributos específicos de la clase Asesino
    private int puntosCombo;
    private double probCritico;

    /**
     * Constructor de la clase Asesino
     * 
     * @param nombre      Nombre del personaje
     * @param nivel       Nivel del personaje
     * @param saludMax    Salud máxima del personaje
     * @param fuerza      Fuerza del personaje
     * @param defensa     Defensa del personaje
     * @param probCritico Probabilidad de crítico del personaje
     */
    public Asesino(String nombre, int nivel, int saludMax, int fuerza, int defensa, double probCritico) {
        super(nombre, nivel, saludMax, fuerza, defensa);
        this.probCritico = probCritico;
        this.puntosCombo = 0;
    }

    /**
     * Método propio: Habilidad específica del personaje
     */
    public void emboscada() {
        this.puntosCombo += 2;
        System.out.println(getNombre() + " se oculta en las sombras y prepara una emboscada. Puntos de combo: "
                + this.puntosCombo);
    }

    /**
     * Método para atacar a un personaje
     * 
     * @param objetivo Personaje objetivo
     */
    @Override
    public void atacar(Personaje objetivo) {
        int danioFinal = calcularDanio();

        // Lógica de ataque crítico
        boolean esCritico = Math.random() <= this.probCritico;
        if (esCritico) {
            danioFinal *= 2;
            System.out.println("¡GOLPE CRÍTICO!");
        }

        // Daño extra si tiene puntos de combo
        if (this.puntosCombo > 0) {
            danioFinal += (this.puntosCombo * 5);
            // Gasta los puntos
            this.puntosCombo = 0;
        }

        System.out.println(getNombre() + " apuñala rápidamente a " + objetivo.getNombre() + " infligiendo "
                + danioFinal + " de daño.");
        objetivo.recibirDanio(danioFinal);
    }

    // --- GETTERS ---
    /**
     * Método para obtener los puntos de combo
     * 
     * @return Puntos de combo
     */
    public int getPuntosCombo() {
        return puntosCombo;
    }

    /**
     * Método para obtener la probabilidad de crítico
     * 
     * @return Probabilidad de crítico
     */
    public double getProbCritico() {
        return probCritico;
    }
}
