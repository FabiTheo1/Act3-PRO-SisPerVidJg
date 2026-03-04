package MVC.model;

import MVC.exceptions.AccionInvalidaException;
import MVC.exceptions.AtributoInvalidoException;
import MVC.exceptions.SinMunicionException;

public class Asesino extends personajeFisico implements Sigiloso, Lanzar {
    // Atributos específicos de la clase Asesino
    private int puntosCombo;
    private double probCritico;
    private int cuchillosArrojadizos;

    /**
     * Constructor de la clase Asesino
     * 
     * @param nombre               Nombre del personaje
     * @param nivel                Nivel del personaje
     * @param saludMax             Salud máxima del personaje
     * @param fuerza               Fuerza del personaje
     * @param defensa              Defensa del personaje
     * @param probCritico          Probabilidad de crítico del personaje
     * @param cuchillosArrojadizos Cuchillos arrojadizos del personaje
     */
    public Asesino(String nombre, int nivel, int saludMax, int fuerza, int defensa, double probCritico,
            int cuchillosArrojadizos) {
        super(nombre, nivel, saludMax, fuerza, defensa);
        this.probCritico = probCritico;
        this.puntosCombo = 0;
        this.cuchillosArrojadizos = Math.max(0, cuchillosArrojadizos);
    }

    // --- METODOS ---

    /**
     * Método para validar el ataque
     * 
     * @param objetivo Personaje objetivo
     */
    private void validarAtaque(Personaje objetivo) {
        if (objetivo == null) {
            throw new AtributoInvalidoException("ERROR: El objetivo del ataque no puede ser nulo.");
        }
        if (this.equals(objetivo)) {
            throw new AccionInvalidaException("ERROR: " + getNombre() + " no puede atacarse a sí mismo.");
        }
        if (!this.estaVivo()) {
            throw new AccionInvalidaException(
                    "ERROR: " + getNombre() + " está muerto y no puede realizar acciones.");
        }
        if (!objetivo.estaVivo()) {
            throw new AccionInvalidaException(
                    "ERROR: No puedes apuntar a " + objetivo.getNombre() + " porque ya está muerto.");
        }
    }

    /**
     * Método propio: Habilidad específica del personaje
     */
    public void emboscada() {
        this.puntosCombo += 2;
        System.out.println(getNombre() + " se oculta en las sombras y prepara una emboscada. Puntos de combo: "
                + this.puntosCombo);
    }

    @Override
    public void ocultarse() {
        System.out.println(getNombre() + " se desvanece en las sombras, volviéndose invisible temporalmente.");
    }

    /**
     * Método para atacar a un personaje
     * 
     * @param objetivo Personaje objetivo
     */
    @Override
    public void atacar(Personaje objetivo) {
        // 1. Validaciones estrictas de combate
        validarAtaque(objetivo);

        // 2. Lógica combate específica del Asesino
        int danioFinal = calcularDanio();

        // Lógica ataque crítico
        boolean esCritico = Math.random() <= this.probCritico;
        if (esCritico) {
            danioFinal *= 2;
            System.out.println("¡GOLPE CRÍTICO!");
        }

        // Daño extra si tiene puntos de combo
        if (this.puntosCombo > 0) {
            danioFinal += (this.puntosCombo * 5);
            // Gasta los puntos tras el ataque
            this.puntosCombo = 0;
        }

        System.out.println(
                getNombre() + " apuñala rápidamente a " + objetivo.getNombre() + " infligiendo " + danioFinal
                        + " de daño.");
        objetivo.recibirDanio(danioFinal);
    }

    /**
     * Método para lanzar un objeto
     * 
     * @param objetivo Personaje objetivo
     */
    @Override
    public void lanzarObjeto(Personaje objetivo) {
        validarAtaque(objetivo);

        if (this.cuchillosArrojadizos <= 0) {
            throw new SinMunicionException(getNombre() + " se ha quedado sin cuchillos para lanzar.");
        }

        this.cuchillosArrojadizos--;
        // El asesino hace menos daño base lanzando, pero suma sus puntos de combo
        int danioLanzamiento = (getFuerza() / 2) + 5 + (this.puntosCombo * 2);
        this.puntosCombo = 0; // Gastamos el combo al lanzar

        System.out.println(getNombre() + " lanza un cuchillo envenenado a " + objetivo.getNombre() + " causando "
                + danioLanzamiento + " de daño. (Cuchillos restantes: " + this.cuchillosArrojadizos + ")");
        objetivo.recibirDanio(danioLanzamiento);
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

    /**
     * Método para obtener los cuchillos arrojadizos
     * 
     * @return Cuchillos arrojadizos
     */
    public int getCuchillosArrojadizos() {
        return cuchillosArrojadizos;
    }
}